package rabbitmq.client;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitMqConfig;

@Component
class Receiver {

    @RabbitListener(queues = RabbitMqConfig.CURRENCY_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveConvertedAmount(String convertedAmounts) {
        System.out.println("RECEIVED convertedAmounts: " + convertedAmounts);
    }

    @RabbitListener(queues = RabbitMqConfig.PRICE_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveCalculatedPrice(double calculatedPrice) {
        System.out.println("RECEIVED calculatedPrice: " + calculatedPrice);
    }

}
