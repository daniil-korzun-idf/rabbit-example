package com.idf.sipmlerabbit.util;

import com.idf.sipmlerabbit.domain.Credit;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class MessageLogger {

  public static void logReceiveMessage(String queue, Credit credit) {
    log.info("Listener on queue [{}] received message [{}]", queue, credit);
  }

  public static void logSendMessage(String exchange, String routingKey, Credit credit) {
    log.info("Message [{}] send to exchange [{}] with routing key [{}]", credit.getId(), exchange, routingKey);
  }

  public static void logSendMessage(String exchange, String routingKey, Credit credit, Map<String, Object> headers) {
    log.info("Message [{}] send to exchange [{}] with routing key [{}] and headers [{}]", credit.getId(), exchange,
        routingKey, headers);
  }
}
