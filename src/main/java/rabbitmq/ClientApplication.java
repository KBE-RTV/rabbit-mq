package rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rabbitmq.client.Sender;
import rabbitmq.model.CelestialBody;
import rabbitmq.model.DTO.CurrencyMessageDTO;
import rabbitmq.model.DTO.PriceMessageDTO;
import rabbitmq.model.DTO.ProductServiceRequestDTO;
import rabbitmq.model.PlanetarySystem;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @SneakyThrows
    @Override
    public void run(String... args) {

        CelestialBody celestialBody1 = new CelestialBody(UUID.randomUUID(), "Sun", 1, 3.50f, "sun", 0, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
        CelestialBody celestialBody2 = new CelestialBody(UUID.randomUUID(), "Earth", 1, 3.50f, "planet", 0, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);

        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(celestialBody1);
        celestialBodies.add(celestialBody2);

        PlanetarySystem planetarySystem1 = new PlanetarySystem(UUID.randomUUID(), "MyPlanetarySystem", "Ricky", celestialBodies, 0f);
        PlanetarySystem planetarySystem2 = new PlanetarySystem(UUID.randomUUID(), "MyPlanetarySystem2", "Ricky", celestialBodies, 0f);

        ArrayList<PlanetarySystem> planetarySystems = new ArrayList<PlanetarySystem>();
        planetarySystems.add(planetarySystem1);
        planetarySystems.add(planetarySystem2);

        PriceMessageDTO priceMessageDTO = new PriceMessageDTO(UUID.randomUUID(), planetarySystems);

        try {
            Sender.sendProductsToPriceService(objectMapper.writeValueAsString(priceMessageDTO));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        planetarySystem1.setPrice(70.0f);
        planetarySystem2.setPrice(80.0f);

        CurrencyMessageDTO currencyMessageDTO = new CurrencyMessageDTO(UUID.randomUUID(), planetarySystems,"Euro", "Dollar");

        try {
            Sender.sendProductsToCurrencyService(objectMapper.writeValueAsString(currencyMessageDTO));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        ProductServiceRequestDTO productServiceRequestDTO = new ProductServiceRequestDTO(UUID.randomUUID(), null, "product");

        Sender.sendRequestToProductService(objectMapper.writeValueAsString(productServiceRequestDTO));


    }

}

