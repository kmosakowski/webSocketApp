package pl.test.websocketapp.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.test.websocketapp.common.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    private String name;
    private List<String> product_ids;

    public Channel(String name, Product... products){
        this.name = name;
        product_ids = new ArrayList<>();

        //Bo zwykła pętla jest szybsza od strumienia ;)
        for(Product product : products){ //O(1)
            product_ids.add(product.getProductId()); //O(1)
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        } else if (o == null){
            return false;
        } else if (o instanceof Channel channel){
            return Objects.equals(name, channel.name) && Objects.equals(product_ids, channel.product_ids);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, product_ids);
    }
}
