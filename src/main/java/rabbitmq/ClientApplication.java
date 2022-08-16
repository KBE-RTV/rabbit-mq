package rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rabbitmq.client.Sender;
import rabbitmq.model.CurrencyConversion;

import java.util.ArrayList;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) {

        ArrayList<Double> amountList = new ArrayList<>();
        amountList.add(80.00);
        amountList.add(70.00);

        Sender.sendConversion(new CurrencyConversion(1, amountList, "Euro", "Dollar"));

        Sender.sendProduct("{ \"id\" : 42, \"name\" : \"Dummy\", \"celestialBodies\" : [ { \"id\" : 1, \"name\" : \"Sun\", \"amount\" : 1, \"price\" : 3.50, \"type\" : \"sun\", \"orbital\" : 0, \"radius\" : 1.0, \"volume\" : 1.0, \"mass\" : 1.0, \"gravity\" : 1.0, \"rotationVelocity\" : 1.0, \"orbitalVelocity\" : 1.0, \"surfaceTemperature\" : 1.0 }, { \"id\" : 1, \"name\" : \"Sun\", \"amount\" : 1, \"price\" : 4.50, \"type\" : \"sun\", \"orbital\" : 0, \"radius\" : 1.0, \"volume\" : 1.0, \"mass\" : 1.0, \"gravity\" : 1.0, \"rotationVelocity\" : 1.0, \"orbitalVelocity\" : 1.0, \"surfaceTemperature\" : 1.0 } ] }");
    }

}

