package pl.test.websocketapp.obj;


import lombok.*;
import pl.test.websocketapp.common.Instrument;
import pl.test.websocketapp.common.Product;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentInstrument implements Instrument {

    private Product instrument;
    private double price;
    private double bid;
    private double ask;
    private LocalTime time;

    @Override
    public Product getInstrument() {
        return instrument;
    }

}
