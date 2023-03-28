package com.idf.sipmlerabbit.config;

import java.util.Map;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadersExchangeConfig {

  public static final String HEADERS_CREDIT_EXCHANGE = "exchange.headers.credit";

  @Bean
  public HeadersExchange headersExchange() {
    return new HeadersExchange(HEADERS_CREDIT_EXCHANGE);
  }

  @Bean
  public Declarables headersExchangeBindings(
      HeadersExchange headersExchange,
      Queue superCreditQueue,
      Queue baseCreditQueue
  ) {
    return new Declarables(
        BindingBuilder.bind(superCreditQueue).to(headersExchange)
            .whereAny(Map.of("product", "super", "owner", "idf")).match(),
        BindingBuilder.bind(baseCreditQueue).to(headersExchange)
            .whereAll(Map.of("product", "base", "owner", "third-party")).match()
    );
  }
}
