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
    private UUID requestID;

    private ArrayList<PlanetarySystem> planetarySystems;

    private String currencyToConvertFrom;

    private String currencyToConvertTo;


    public CurrencyMessageDTO(@JsonProperty("requestID") UUID callId,
                      @JsonProperty("planetarySystems") ArrayList<PlanetarySystem> products,
                      @JsonProperty("currencyToConvertFrom") String currencyToConvertFrom,
                      @JsonProperty("currencyToConvertTo") String currencyToConvertTo) {
        this.requestID = callId;
        this.planetarySystems = products;
        this.currencyToConvertFrom = currencyToConvertFrom;
        this.currencyToConvertTo = currencyToConvertTo;
    }
}
