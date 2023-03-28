package com.idf.simplerabbitclone;

import static org.springframework.amqp.core.MessageDeliveryMode.NON_PERSISTENT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class SimpleRabbitCloneApplication {

  private static final String QUEUE_NAME = "queue.trash";
  private static final String EXCHANGE_NAME = "exchange.trash";
  private static final String ROUTING_KEY = "trash.key";
  private static Long NUMBER_COUNTER = 0L;

  private final RabbitTemplate rabbitTemplate;

  public static void main(String[] args) {
    SpringApplication.run(SimpleRabbitCloneApplication.class, args);
  }

  @Bean
  public Queue simpleQueue() {
    return new Queue(QUEUE_NAME);
  }

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(EXCHANGE_NAME);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(simpleQueue()).to(exchange()).with(ROUTING_KEY);
  }

  @RabbitListener(queues = QUEUE_NAME)
  public void listener(String num) throws InterruptedException {
    /*Thread.sleep(10000);
    System.exit(0);*/
    log.info("Got message number: " + num);
  }

  @Scheduled(fixedDelay = 1000L)
  public void trashProducer() {
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setDeliveryMode(NON_PERSISTENT);
    rabbitTemplate.convertAndSend(
        EXCHANGE_NAME,
        ROUTING_KEY,
        new SimpleMessageConverter().toMessage(String.valueOf(++NUMBER_COUNTER), messageProperties)
        //String.valueOf(++NUMBER_COUNTER)
    );
  }
}
