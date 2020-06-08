package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class JmsProducerTopicPersist
{
    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String TOPIC_NAME = "peifeng_topic01";
    public static void main( String[] args ) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("TopicPersistMsg---" + i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

        System.out.println("*********TOPIC 消息发送到 MQ 完成");
    }
}
