package com.idf.sipmlerabbit.listener;

import static com.idf.sipmlerabbit.config.QueueConfig.BASE_CREDIT_QUEUE;
import static com.idf.sipmlerabbit.config.QueueConfig.PLUS_CREDIT_QUEUE;
import static com.idf.sipmlerabbit.config.QueueConfig.PREMIUM_CREDIT_QUEUE;
import static com.idf.sipmlerabbit.config.QueueConfig.SUPER_CREDIT_QUEUE;
import static com.idf.sipmlerabbit.util.MessageLogger.logReceiveMessage;

import com.idf.sipmlerabbit.domain.Credit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CreditListener {

  @RabbitListener(queues = BASE_CREDIT_QUEUE)
  public void baseCreditListener(Credit credit) {
    logReceiveMessage(BASE_CREDIT_QUEUE, credit);
  }

  @RabbitListener(queues = PLUS_CREDIT_QUEUE)
  public void plusCreditListener(Credit credit) {
    logReceiveMessage(PLUS_CREDIT_QUEUE, credit);
  }

  @RabbitListener(queues = PREMIUM_CREDIT_QUEUE)
  public void premiumCreditListener(Credit credit) {
    logReceiveMessage(PREMIUM_CREDIT_QUEUE, credit);
  }

  @RabbitListener(queues = SUPER_CREDIT_QUEUE)
  public void superCreditListener(Credit credit) {
    logReceiveMessage(SUPER_CREDIT_QUEUE, credit);
  }
}
