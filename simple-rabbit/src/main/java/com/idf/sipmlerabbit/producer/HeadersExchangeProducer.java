package com.idf.sipmlerabbit.producer;

import static com.idf.sipmlerabbit.config.HeadersExchangeConfig.HEADERS_CREDIT_EXCHANGE;
import static com.idf.sipmlerabbit.util.MessageLogger.logSendMessage;

import com.idf.sipmlerabbit.domain.Credit;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("headers-exchange")
@RequiredArgsConstructor
public class HeadersExchangeProducer {

  private final RabbitTemplate rabbitTemplate;

  @PostMapping
  public void sendCredits() {
    sendCreditAndLog("super", "third-party");
    sendCreditAndLog("super", "idf");

    sendCreditAndLog("base", "third-party");
    sendCreditAndLog("base", "null");
  }

  private void sendCreditAndLog(String productHeader, String ownerHeader) {
    Credit credit = new Credit();
    Message message = setHeadersToMessage(credit, productHeader, ownerHeader);
    rabbitTemplate.convertAndSend(HEADERS_CREDIT_EXCHANGE, "", message);
    logSendMessage(HEADERS_CREDIT_EXCHANGE, "", credit,
        Map.of("product", productHeader, "owner", ownerHeader));
  }

  private Message setHeadersToMessage(Credit credit, String productHeader, String ownerHeader) {
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setHeader("product", productHeader);
    messageProperties.setHeader("owner", ownerHeader);
    return new SimpleMessageConverter().toMessage(credit, messageProperties);
  }
}
