package com.idf.sipmlerabbit.producer;

import static com.idf.sipmlerabbit.config.DirectExchangeConfig.BASE_CREDIT_ROUTING_KEY;
import static com.idf.sipmlerabbit.config.FanoutExchangeConfig.FANOUT_CREDIT_EXCHANGE;

import com.idf.sipmlerabbit.domain.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fanout-exchange")
@RequiredArgsConstructor
public class FanoutExchangeProducer {

  private final RabbitTemplate rabbitTemplate;

  @PostMapping
  public void sendCredits() {
    rabbitTemplate.convertAndSend(FANOUT_CREDIT_EXCHANGE, "", new Credit());
    rabbitTemplate.convertAndSend(FANOUT_CREDIT_EXCHANGE, BASE_CREDIT_ROUTING_KEY, new Credit());
  }
}
