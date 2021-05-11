package pl.test.websocketapp.common;

import pl.test.websocketapp.message.Response;
import pl.test.websocketapp.obj.CurrentInstrument;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Mapper implements CurrentInstrumentMapper{

    @Override
    public CurrentInstrument convertToCurrentInstrument(Response response) {
        if (response == null) {
            return null;
        }

        var currentInstrument = new CurrentInstrument();

        currentInstrument.setInstrument(Product.whtchOne(response.getProduct_id()));
        currentInstrument.setPrice(response.getPrice());
        currentInstrument.setAsk(response.getBest_ask());
        currentInstrument.setBid(response.getBest_bid());

        LocalTime time = LocalTime.ofInstant(response.getTime().toInstant(), ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);
        currentInstrument.setTime(time);

        return currentInstrument;
    }
}
