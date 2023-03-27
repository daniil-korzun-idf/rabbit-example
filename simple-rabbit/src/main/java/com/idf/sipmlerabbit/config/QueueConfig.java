package com.idf.sipmlerabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

  public static final String BASE_CREDIT_QUEUE = "queue.base.credit";
  public static final String PLUS_CREDIT_QUEUE = "queue.plus.credit";
  public static final String PREMIUM_CREDIT_QUEUE = "queue.premium.credit";
  public static final String SUPER_CREDIT_QUEUE = "queue.super.credit";

  @Bean
  public Queue baseCreditQueue() {
    return new Queue(BASE_CREDIT_QUEUE);
  }

  @Bean
  public Queue plusCreditQueue() {
    return new Queue(PLUS_CREDIT_QUEUE);
  }

  @Bean
  public Queue premiumCreditQueue() {
    return new Queue(PREMIUM_CREDIT_QUEUE);
  }

  @Bean
  public Queue superCreditQueue() {
    return new Queue(SUPER_CREDIT_QUEUE);
  }
}
