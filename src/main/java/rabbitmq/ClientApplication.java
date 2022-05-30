package rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import rabbitmq.currency.client.RpcClient;
import rabbitmq.currency.model.CurrencyConversion;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    public static final String DIRECT_EXCHANGE_NAME = "rabbitmq-exchange";


    public RpcClient rpcClient;

    @Autowired
    public void setRpcClient(@Lazy RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ClientApplication.class, args).close();
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

    @Override
    public void run(String... args) throws Exception {
    //   rpcClient.sendConversionAndReceiveConvertedAmount(new CurrencyConversion(80.0, "Euro", "Dollar"));
    //    rpcClient.sendProductAndReceiveCalculatedPrice("");
    }


}

