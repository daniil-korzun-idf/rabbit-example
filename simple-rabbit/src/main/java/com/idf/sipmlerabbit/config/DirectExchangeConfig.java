package com.idf.sipmlerabbit.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

  public static final String DIRECT_EXCHANGE = "exchange.direct.credit";
  public static final String BASE_CREDIT_ROUTING_KEY = "base.credit";
  public static final String PLUS_CREDIT_ROUTING_KEY = "plus.credit";
  public static final String PREMIUM_CREDIT_ROUTING_KEY = "premium.credit";
  public static final String SUPER_CREDIT_ROUTING_KEY = "super.credit";

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(DIRECT_EXCHANGE);
  }

  @Bean
  public Declarables directExchangeBindings(
      DirectExchange directExchange,
      Queue baseCreditQueue,
      Queue plusCreditQueue
  ) {
    return new Declarables(
        BindingBuilder.bind(baseCreditQueue).to(directExchange).with(BASE_CREDIT_ROUTING_KEY),
        BindingBuilder.bind(plusCreditQueue).to(directExchange).with(PLUS_CREDIT_ROUTING_KEY)
    );
  }

}
