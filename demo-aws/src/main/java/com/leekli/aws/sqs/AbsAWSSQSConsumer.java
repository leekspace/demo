package com.leekli.aws.sqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.management.modelmbean.DescriptorSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazon.sqs.javamessaging.SQSSession;
import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.amazonaws.services.sqs.AmazonSQS;

/**
 * 
 * @author media-liwei
 *
 */
public abstract class AbsAWSSQSConsumer {
    private Logger LOGGER = LoggerFactory.getLogger(AbsAWSSQSConsumer.class);
    private static int MAX_BATCH = 10;

    private List<MessageConsumer> consumerList;
    private SQSConnection connection;
    private Session session;
    private ExecutorService es;

    private volatile ConsumerState state = new ConsumerState();
    private final Object stateLock = new Object();

    public abstract ConsumerConfig getConsumerConfig();

    public abstract AmazonSQS getAmazonSQS();

    public abstract void receiveMessage(MessageConsumer consumer, boolean acknowledge) throws JMSException;

    private void init() throws Exception {
        LOGGER.info("init");
        if (state.isInited()) {
            return;
        }

        String queueName = getConsumerConfig().getQueueName();
        LOGGER.info("queue name:" + queueName);
        consumerList = new ArrayList<>();
        ProviderConfiguration providerConfiguration = new ProviderConfiguration()
                .withNumberOfMessagesToPrefetch(MAX_BATCH);
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(providerConfiguration, getAmazonSQS());
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, SQSSession.CLIENT_ACKNOWLEDGE);
        connection.start();

        Destination desc =  null;
        MessageProducer producer = session.createProducer(desc );
        Message message = new SQSTextMessage();
        message.setJMSTimestamp(System.currentTimeMillis());
        producer.send(message);
        
        int num = getConsumerConfig().getConsumerNum();
        es = Executors.newFixedThreadPool(num);
        for (int i = 0; i < num; i++) {
            MessageConsumer consumer = session.createConsumer(session.createQueue(queueName));
            consumerList.add(consumer);
        }

    }

    public void startReceive() throws Exception {
        synchronized (stateLock) {
            if (state.isRunning()) {
                LOGGER.info("consumer is running...");
                return;
            }
            checkClosing();
            try {
                init();
                state.inited();
            } catch (Exception e1) {
                LOGGER.error(e1.getMessage(), e1);
                return;
            }
            state.running();
        }

        try {
            run();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void stopReceive() throws Exception {
        synchronized (stateLock) {
            checkClosed();
            checkClosing();
            try {
                state.stopping();
                stop();
            } finally {
                state.stoped();
            }
        }
    }

    private void run() {
        for (MessageConsumer messageConsumer : consumerList) {
            es.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    while (state.isRunning()) {
                        LOGGER.info("receive Message...");
                        try {
                            receiveMessage(messageConsumer, true);
                            if (state.isStopping()) {
                                break;
                            }
                        } catch (Exception e) {
                            LOGGER.error(e.getMessage(), e);
                        }
                    }
                    return null;
                }
            });
        }
    }

    private void stop() {
        for (MessageConsumer messageConsumer : consumerList) {
            try {
                messageConsumer.close();
            } catch (JMSException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        try {
            session.close();
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }

        try {
            connection.close();
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
        es.shutdownNow();
    }

    public void checkClosed() throws Exception {
        if (state.isStoped()) {
            throw new Exception("Connection is closed");
        }
    }

    public void checkClosing() throws Exception {
        if (state.isStopping()) {
            throw new Exception("Connection is closed or closing");
        }
    }
}