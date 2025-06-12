package ir.moke.example;

import ir.moke.microfox.api.ibmmq.AcknowledgeType;
import ir.moke.microfox.ibmmq.IbmMQFactory;
import jakarta.jms.*;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static ir.moke.microfox.MicroFox.ibmMQConsumeQueue;
import static ir.moke.microfox.MicroFox.ibmMQProducerQueue;

public class MainClass {
    public static void main(String[] args) {
        IbmMQFactory.createConnectionFactory("test","127.0.0.1",1414,"QM1","DEV.APP.SVRCONN","admin","adminpass");
        ibmMQConsumeQueue("test", "sample", AcknowledgeType.AUTO_ACKNOWLEDGE, new SampleListener());

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(MainClass::produceMessage, 0, 2, TimeUnit.SECONDS);
    }

    private static void produceMessage() {
        ibmMQProducerQueue("test", AcknowledgeType.AUTO_ACKNOWLEDGE, session -> {
            try {
                Queue queue = session.createQueue("sample");
                MessageProducer messageProducer = session.createProducer(queue);
                TextMessage textMessage = session.createTextMessage("Hello " + LocalTime.now());
                messageProducer.send(textMessage);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class SampleListener implements MessageListener {

        @Override
        public void onMessage(Message message) {
            try {
                System.out.println(message.getJMSMessageID());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
