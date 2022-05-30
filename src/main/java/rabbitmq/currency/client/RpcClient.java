package rabbitmq.currency.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.ClientApplication;
import rabbitmq.currency.model.CurrencyConversion;

@Component
public
class RpcClient {
    public RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public DirectExchange exchange;

    @Autowired
    public void setExchange(DirectExchange exchange) {
        this.exchange = exchange;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

}
