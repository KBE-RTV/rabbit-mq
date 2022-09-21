package rabbitmq.client;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitMqConfig;

@Component
class Receiver {

    @RabbitListener(queues = RabbitMqConfig.CURRENCY_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveConvertedAmount(String convertedAmountsAsJson) {

        System.out.println("RECEIVED convertedAmounts: " + convertedAmountsAsJson + "\n");
    }

    @RabbitListener(queues = RabbitMqConfig.PRICE_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveCalculatedPrice(String calculatedPriceAsJson) {

        System.out.println("RECEIVED calculatedPrice: " + calculatedPriceAsJson + "\n");

    }

    @RabbitListener(queues = RabbitMqConfig.PRODUCT_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveProducts(String productsAsJson) {

        System.out.println("RECEIVED products: " + productsAsJson + "\n");

    }

}
