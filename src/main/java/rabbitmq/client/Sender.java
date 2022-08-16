package rabbitmq.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitMqConfig;
import rabbitmq.model.CurrencyConversion;

@Component
public class Sender {
    private static RabbitTemplate rabbitTemplate;

    private static ObjectMapper objectMapper;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        Sender.rabbitTemplate = rabbitTemplate;
    }


    public static void sendConversion(CurrencyConversion conversion) {

        objectMapper = new ObjectMapper();

        try {
            String conversionAsJson = objectMapper.writeValueAsString(conversion);

            rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.CURRENCY_SERVICE_CALL_ROUTING_KEY, conversionAsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("SENT conversion \n");
    }

    public static void sendProduct(String productAsJson) {

        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.PRICE_SERVICE_CALL_ROUTING_KEY, productAsJson);

        System.out.println("SENT product \n");
    }
}
