package org.nlejeune;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Nicolas Lejeune
 */
@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Autowired
    TxBean txBean;

    @RabbitListener(queues = TxIssueApplication.FORECAST_DEFINITION_CHANGED_QUEUE)
    @Transactional
    void handleMessage(Message o) {
        logger.info("isActualTransactionActive in listener : " + TransactionSynchronizationManager.isActualTransactionActive());

        txBean.txMethod();
    }

}
