package com.idf.sipmlerabbit.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

  public static final String TOPIC_CREDIT_EXCHANGE = "exchange.topic.credit";

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_CREDIT_EXCHANGE);
  }

  @Bean
  public Declarables topicExchangeBindings(
      TopicExchange topicExchange,
      Queue baseCreditQueue,
      Queue plusCreditQueue,
      Queue premiumCreditQueue) {
    return new Declarables(
        BindingBuilder.bind(baseCreditQueue).to(topicExchange).with("base.*"),
        BindingBuilder.bind(plusCreditQueue).to(topicExchange).with("plus.*"),
        BindingBuilder.bind(premiumCreditQueue).to(topicExchange).with("#")
    );
  }
}
