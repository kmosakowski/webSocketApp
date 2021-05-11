package pl.test.websocketapp.common;

import lombok.Getter;
import lombok.Setter;
import pl.test.websocketapp.message.Subscription;
import pl.test.websocketapp.obj.CurrentInstrument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Przechowuje stan aplikacji
 */
public class State {

    /**
     * Dzięki temu zabiegowi, gdy klasa jest zaczytywana do pamięci jest tworzona mapa która zapobiegnie
     * nieprawidłowemu działaniu nietórych metod, jak np updateState()
     */
    static {
        instruments = new HashMap<>();
        State.instruments.put(Product.BTCEUR, new CurrentInstrument());
        State.instruments.put(Product.BTCUSD, new CurrentInstrument());
        State.instruments.put(Product.ETHEUR, new CurrentInstrument());
        State.instruments.put(Product.ETHUSD, new CurrentInstrument());
    }

    @Getter
    @Setter
    private Subscription subscription;
    private static final Map<Product, Instrument> instruments;

    public void updateState(Instrument instrument){
        instruments.replace(instrument.getInstrument(), instrument);
    }

    public List<Instrument> getInstruments(){
        return new ArrayList<>(State.instruments.values());
    }

}
