package pl.test.websocketapp.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.test.websocketapp.obj.Channel;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    private String type;
    private List<Channel> channels;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        } else if (o == null){
            return false;
        } else if (o instanceof Subscription that){
            return Objects.equals(type, that.type) && Objects.equals(channels, that.channels);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, channels);
    }
}
