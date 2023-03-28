package com.idf.sipmlerabbit.producer;

import static com.idf.sipmlerabbit.config.DirectExchangeConfig.BASE_CREDIT_ROUTING_KEY;
import static com.idf.sipmlerabbit.config.DirectExchangeConfig.PLUS_CREDIT_ROUTING_KEY;
import static com.idf.sipmlerabbit.config.DirectExchangeConfig.SUPER_CREDIT_ROUTING_KEY;
import static com.idf.sipmlerabbit.config.TopicExchangeConfig.TOPIC_CREDIT_EXCHANGE;

import com.idf.sipmlerabbit.domain.Credit;
import com.idf.sipmlerabbit.util.MessageLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topic-exchange")
@RequiredArgsConstructor
public class TopicExchangeProducer {

  private final RabbitTemplate rabbitTemplate;

  @PostMapping
  public void sendCredits() {
    sendCreditAndLog(BASE_CREDIT_ROUTING_KEY, new Credit());
    sendCreditAndLog(PLUS_CREDIT_ROUTING_KEY, new Credit());
    sendCreditAndLog(SUPER_CREDIT_ROUTING_KEY, new Credit());
  }

  private void sendCreditAndLog(String routingKey, Credit credit) {
    rabbitTemplate.convertAndSend(TOPIC_CREDIT_EXCHANGE, routingKey, credit);
    MessageLogger.logSendMessage(TOPIC_CREDIT_EXCHANGE, routingKey, credit);
  }
}
