package rabbitmq.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitMqConfig;
import rabbitmq.model.CurrencyConversion;
import rabbitmq.model.ProductPrice;

@Component
class Receiver {

    ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMqConfig.CURRENCY_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveConvertedAmount(String convertedAmountsAsJson) {
        objectMapper = new ObjectMapper();

        CurrencyConversion currencyConversion;
        try {
            currencyConversion = objectMapper.readValue(convertedAmountsAsJson, CurrencyConversion.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("RECEIVED convertedAmounts: " + convertedAmountsAsJson);


        System.out.println("--> CallId: " + currencyConversion.getId());
        System.out.println("--> Amount list: " + currencyConversion.getAmountList() +"\n");
    }

    @RabbitListener(queues = RabbitMqConfig.PRICE_SERVICE_RESPONSE_QUEUE_NAME)
    public void receiveCalculatedPrice(String calculatedPriceAsJson) {
        objectMapper = new ObjectMapper();

        ProductPrice productPrice;

        try {
            productPrice = objectMapper.readValue(calculatedPriceAsJson, ProductPrice.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("RECEIVED calculatedPrice: " + calculatedPriceAsJson);

        System.out.println("--> Id: " + productPrice.getId());
        System.out.println("--> Price: " + productPrice.getPrice() +"\n");
    }

}
