package rabbitmq.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitMqConfig;
import rabbitmq.model.CurrencyConversion;
import rabbitmq.model.PlanetarySystem;
import rabbitmq.model.ProductPrice;

@Component
class Receiver {

    @RabbitListener(queues = RabbitMqConfig.CURRENCY_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveConvertedAmount(String convertedAmountsAsJson) {

        System.out.println("RECEIVED convertedAmounts: " + convertedAmountsAsJson);
    }

    @RabbitListener(queues = RabbitMqConfig.PRICE_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveCalculatedPrice(String calculatedPriceAsJson) {

        System.out.println("RECEIVED calculatedPrice: " + calculatedPriceAsJson + "\n");

    }

}
