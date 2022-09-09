package rabbitmq.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rabbitmq.model.PlanetarySystem;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CurrencyMessageDTO {
    private UUID callId;

    private ArrayList<PlanetarySystem> products;

    private String currencyToConvertFrom;

    private String currencyToConvertTo;


    public CurrencyMessageDTO(@JsonProperty("callId") UUID callId,
                              @JsonProperty("products") ArrayList<PlanetarySystem> products,
                              @JsonProperty("currencyToConvertFrom") String currencyToConvertFrom,
                              @JsonProperty("currencyToConvertTo") String currencyToConvertTo) {
        this.callId = callId;
        this.products = products;
        this.currencyToConvertFrom = currencyToConvertFrom;
        this.currencyToConvertTo = currencyToConvertTo;
    }
}
