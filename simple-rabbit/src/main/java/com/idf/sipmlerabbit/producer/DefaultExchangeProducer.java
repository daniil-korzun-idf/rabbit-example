package com.idf.sipmlerabbit.producer;

import static com.idf.sipmlerabbit.config.QueueConfig.BASE_CREDIT_QUEUE;

import com.idf.sipmlerabbit.domain.Credit;
import com.idf.sipmlerabbit.util.MessageLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("default-exchange")
@RequiredArgsConstructor
public class DefaultExchangeProducer {

  private final RabbitTemplate rabbitTemplate;

  @PostMapping
  public void sendCredit() {
    Credit credit = new Credit();
    rabbitTemplate.convertAndSend(BASE_CREDIT_QUEUE, credit);
    MessageLogger.logSendMessage("", BASE_CREDIT_QUEUE, credit);
  }
}
