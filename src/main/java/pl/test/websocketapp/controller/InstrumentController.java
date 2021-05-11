package pl.test.websocketapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.test.websocketapp.common.Instrument;
import pl.test.websocketapp.service.InstrumentService;

import java.util.List;

/**
 * localhost:8080/instrument/
 * Nie tworzyłem obslugi błędów, ze względu na prostotę rozwiązania.
 * W przypadgu gdy połączenie po WebSocket nie wykonałoby się to zostanie zwrócony JSON w postaci
 * [
 *     {
 *         "instrument": null,
 *         "price": 0.0,
 *         "bid": 0.0,
 *         "ask": 0.0,
 *         "time": null
 *     },
 *     ...
 * ]
 * Controller zawsze odpowie, czy to ostatnią wartością czy to powyższym przykładem
 */
@RestController
@RequestMapping("/instrument")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Instrument>> getInstrument(){
        return ResponseEntity.ok(instrumentService.getInstrument());
    }
}
