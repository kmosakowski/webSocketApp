package pl.test.websocketapp.service;

import org.springframework.stereotype.Service;
import pl.test.websocketapp.common.Instrument;
import pl.test.websocketapp.common.State;

import java.util.List;

@Service
public class InstrumentService {

    public List<Instrument> getInstrument(){
        return new State().getInstruments();
    }
}
