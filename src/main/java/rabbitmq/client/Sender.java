package rabbitmq.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitMqConfig;

@Component
public class Sender {
    private static RabbitTemplate rabbitTemplate;

    private static ObjectMapper objectMapper;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        Sender.rabbitTemplate = rabbitTemplate;
    }

    public static void sendProductsToCurrencyService(String message)
    {
        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.CURRENCY_SERVICE_CALL_ROUTING_KEY, message);

        System.out.println("SENT product to currency-service \n");
    }

    public static void sendProductsToPriceService(String message) {

        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.PRICE_SERVICE_CALL_ROUTING_KEY, message);

        System.out.println("SENT product to price-service \n");
    }

    public static void sendRequestToProductService(String request)
    {
        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.PRODUCT_SERVICE_CALL_ROUTING_KEY, request);

        System.out.println("SENT request to product-service \n");
    }
}
