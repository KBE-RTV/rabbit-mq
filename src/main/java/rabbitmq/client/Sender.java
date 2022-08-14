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
    public static RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        Sender.rabbitTemplate = rabbitTemplate;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendConversion(CurrencyConversion conversion) {
        try {
            String conversionAsJson = objectMapper.writeValueAsString(conversion);

            rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.CURRENCY_SERVICE_CALL_ROUTING_KEY, conversionAsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void sendProduct(String productAsJson) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange.getName(), RabbitMqConfig.PRICE_SERVICE_CALL_ROUTING_KEY, productAsJson);
    }
}
