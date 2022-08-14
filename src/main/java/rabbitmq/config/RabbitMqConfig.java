package rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class RabbitMqConfig {
    public static final String TOPIC_EXCHANGE_NAME = "kbe_topic_exchange";
    public static final String CURRENCY_SERVICE_RESPONSE_QUEUE_NAME = "currency_response_queue";
    public static final String PRICE_SERVICE_RESPONSE_QUEUE_NAME = "price_response_queue";
    public static final String CURRENCY_SERVICE_CALL_ROUTING_KEY = "currencyService.call";
    public static final String CURRENCY_SERVICE_RESPONSE_ROUTING_KEY = "currencyService.response";
    public static final String PRICE_SERVICE_CALL_ROUTING_KEY = "priceService.call";
    public static final String PRICE_SERVICE_RESPONSE_ROUTING_KEY = "priceService.response";

    public static TopicExchange exchange;

    @Autowired
    public void setExchange(@Lazy TopicExchange exchange) {
        RabbitMqConfig.exchange = exchange;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Queue currencyQueue() {
        return new Queue(CURRENCY_SERVICE_RESPONSE_QUEUE_NAME, false);
    }

    @Bean
    Binding currencyBinding() {
        return BindingBuilder.bind(currencyQueue()).to(exchange).with(CURRENCY_SERVICE_RESPONSE_ROUTING_KEY);
    }

    @Bean
    Queue priceQueue() {
        return new Queue(PRICE_SERVICE_RESPONSE_QUEUE_NAME, false);
    }

    @Bean
    Binding priceBinding() {
        return BindingBuilder.bind(priceQueue()).to(exchange).with(PRICE_SERVICE_RESPONSE_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter());
        return rabbitTemplate;
    }
}
