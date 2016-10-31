package org.nlejeune;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Nicolas Lejeune
 */
@Component
public class Sender implements ApplicationRunner {
private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    RabbitTemplate template;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("Sending event");

        template.send(TxIssueApplication.NOTIFICATION_EXCHANGE, TxIssueApplication.FORECAST_DEFINITION_CHANGED,
                MessageBuilder.withBody("brol".getBytes()).build());
    }
}
