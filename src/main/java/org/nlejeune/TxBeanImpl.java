package org.nlejeune;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Nicolas Lejeune
 */
@Component
public class TxBeanImpl implements TxBean {

    private static final Logger logger = LoggerFactory.getLogger(TxBeanImpl.class);

    @Transactional
    public void txMethod(){
        logger.info("isActualTransactionActive in transactional bean : " + TransactionSynchronizationManager.isActualTransactionActive());
    }
}
