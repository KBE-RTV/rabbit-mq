package rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ProductPrice implements Serializable {

    int id;
    double price;

    public ProductPrice(@JsonProperty("id") int id,
                        @JsonProperty("price") double price) {
        this.id = id;
        this.price = price;
    }
}
