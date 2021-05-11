package pl.test.websocketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.test.websocketapp.client.WebSocketCl;
import pl.test.websocketapp.obj.Channel;
import pl.test.websocketapp.message.Subscription;

import java.util.List;

import static pl.test.websocketapp.common.Product.*;

/**
 * @Author Kamil Mosakowski
 * Założenia:
 * Prosta aplikacja która nawiązuje połączenie, przechowuje ostatnie najświeższe informacje na temat cen intrumentów i udostępnia jest poprzez GET REST.
 * Nie tworzyłem dodatkowych zapytań REST by wyciągać pojedyńcze instrumenty. Instrumenty są w przechowywane w mapie w kolejności losowej.
 * Komentarze do niektórych metod pisałem w języku polskim wyjaśniające niektóre kwestie.
 * Jeśli nie miałem pomysłu gdzie wstawić daną klasę wstawiałem ją do pakietu common
 * Logowałem różne ścieżki pójścia programu, jak i odbierane z 'coinbase.com' odpowiedzi
 * Nie tworzyłem testów jednostkowych, gdyż uruchomienie aplikacji dokładnie to realizuje
 */

@SpringBootApplication
public class WebSocketAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketAppApplication.class, args);

        Channel channel = new Channel("ticker", BTCUSD, BTCEUR, ETHUSD, ETHEUR);
        Subscription subscriptionMessage = new Subscription("subscribe", List.of(channel));

        new WebSocketCl().connectAndSubscribe("wss://ws-feed.pro.coinbase.com", subscriptionMessage);
    }

}
