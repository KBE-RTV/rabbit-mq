 package rabbitmq.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rabbitmq.model.PlanetarySystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;


 @Getter
 @Setter
 @ToString
 public class PriceMessageDTO implements Serializable {

     private UUID requestID;
     private ArrayList<PlanetarySystem> planetarySystems;

     public PriceMessageDTO(@JsonProperty("requestID") UUID requestID,
                       @JsonProperty("planetarySystems") ArrayList<PlanetarySystem> planetarySystems) {
         this.requestID = requestID;
         this.planetarySystems = planetarySystems;
     }
 }