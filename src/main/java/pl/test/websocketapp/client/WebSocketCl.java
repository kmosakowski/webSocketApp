package pl.test.websocketapp.client;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pl.test.websocketapp.common.Mapper;
import pl.test.websocketapp.common.State;
import pl.test.websocketapp.message.Response;
import pl.test.websocketapp.message.Subscription;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Slf4j
public class WebSocketCl extends TextWebSocketHandler {

    private final StandardWebSocketClient webSocketClient;
    private final Mapper mapper;
    private final State state;
    private final Gson gson;
    private boolean isSubscribed;
    private Subscription subscription;

    /**
     * Zapisuje obiekty które używam kilkukrotnie by nie tworzyć ich nowej instancji za każdym razem gdy przechwycę wiadomość
     */
    public WebSocketCl (){
        this.webSocketClient = new StandardWebSocketClient();
        this.mapper = new Mapper();
        this.state = new State();
        this.gson = new Gson();
    }

    /**
     * Metoda nawiązująca połączenie, oraz subskrybujaca instrumenty.
     * Mogłem wstawić pętlę, scheduler czasowy, lub inne rozwiązanie, lecz dla postorty
     * wstawiłem new Scanner(), by w łatwy sposób można było sprawdzić działanie programu po zamknięciu połączenia
     * @param webSocketFeed Adres
     * @param subscriptionMessage Obiekt subskrypcji
     */
    public void connectAndSubscribe(String webSocketFeed, Subscription subscriptionMessage){
        subscription = subscriptionMessage;
        try(var clientSession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(), new URI(webSocketFeed)).get()){

            var json = new JSONObject(subscription);
            clientSession.sendMessage(new TextMessage(json.toString()));

            new Scanner(System.in).nextLine(); //By subskrypcja się kręciła

        } catch(ExecutionException | InterruptedException | URISyntaxException e){
            log.error("Something went wrong with connection");
        } catch (IOException e){
            log.error("Something went wrong with subscription");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        if(!isSubscribed){
            validateSubscription(message);
        } else {
            var resp = processMessage(message);
            log.info("Response message: {}", resp);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(status.getCode() == 1000){
            log.info("Connection closed currectly");
            log.info("Last reposne instruments: {}", state.getInstruments());
        } else{
            log.warn("Connsection closed because {} with code {}", status.getReason(), status.getCode());
        }
    }


    private Response processMessage(TextMessage message){
        var resp = gson.fromJson(message.getPayload(), Response.class);
        if(resp.isNotEmpty()){
            var currentInstrument = mapper.convertToCurrentInstrument(resp);
            state.updateState(currentInstrument);
        }
        return resp;
    }

    /**
     * Sprawdza czy subskrypcja jest dalej ważna, lub czy subskrycpja została zaakceptowana prawidłowo
     * @param message Wiadomość zwrotna z serwisu
     */
    private void validateSubscription(TextMessage message){
        var sub = gson.fromJson(message.getPayload(), Subscription.class);
        if(subscription.getChannels().equals(sub.getChannels())){
            state.setSubscription(sub);
            isSubscribed = true;
            log.info("Subscription completed. Subscriped channels: {}", state.getSubscription().getChannels());
        } else {
            var resp = processMessage(message);
            isSubscribed = true;
            log.info("Subscription is still valid. Response message: {}", resp);
        }
    }
}
