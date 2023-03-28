package com.idf.sipmlerabbit.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {

  public static final String FANOUT_CREDIT_EXCHANGE = "exchange.fanout.credit";

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_CREDIT_EXCHANGE);
  }

  @Bean
  public Declarables fanoutExchangeBindings(
      FanoutExchange fanoutExchange,
      Queue baseCreditQueue,
      Queue plusCreditQueue,
      Queue premiumCreditQueue,
      Queue superCreditQueue
  ) {
    return new Declarables(
        BindingBuilder.bind(baseCreditQueue).to(fanoutExchange),
        BindingBuilder.bind(plusCreditQueue).to(fanoutExchange),
        BindingBuilder.bind(premiumCreditQueue).to(fanoutExchange),
        BindingBuilder.bind(superCreditQueue).to(fanoutExchange)
    );
  }
}
