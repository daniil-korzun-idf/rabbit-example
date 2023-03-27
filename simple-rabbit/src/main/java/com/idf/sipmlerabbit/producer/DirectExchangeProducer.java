package com.idf.sipmlerabbit.producer;

import static com.idf.sipmlerabbit.config.DirectExchangeConfig.BASE_CREDIT_ROUTING_KEY;
import static com.idf.sipmlerabbit.config.DirectExchangeConfig.DIRECT_EXCHANGE;
import static com.idf.sipmlerabbit.config.DirectExchangeConfig.PLUS_CREDIT_ROUTING_KEY;
import static com.idf.sipmlerabbit.util.MessageLogger.logSendMessage;

import com.idf.sipmlerabbit.domain.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("direct-exchange")
@RequiredArgsConstructor
public class DirectExchangeProducer {

  private final RabbitTemplate rabbitTemplate;

  @PostMapping
  public void sendCredits() {
    sendCreditByRoutingKey(BASE_CREDIT_ROUTING_KEY);
    sendCreditByRoutingKey(PLUS_CREDIT_ROUTING_KEY);
    sendCreditByRoutingKey("non-existent");
  }

  private void sendCreditByRoutingKey(String routingKey) {
    Credit credit = new Credit();
    rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, routingKey, credit);
    logSendMessage(DIRECT_EXCHANGE, routingKey, credit);
  }
}
