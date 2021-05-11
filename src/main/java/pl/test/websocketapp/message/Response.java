package pl.test.websocketapp.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String product_id;
    private double price;
    private double best_bid;
    private double best_ask;
    private Date time;

    /**
     * Gdy łączę się poraz pierwszy z coinbase.com zwracany jest obiekt subskrypcji, który potwierdza ją,
     * dlatego sprawdzam czy odpowiedź jest typu Response, jeśli nie, to pewnie jest to odpowiedź Subskrypcji
     * @return Jeśli odpowiedź jest Response
     */
    public boolean isNotEmpty(){
        return product_id != null && time != null;
    }
}
