package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class JmsComsumerTopicPersist
{
    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String TOPIC_NAME = "peifeng_topic01";
    public static void main( String[] args ) throws JMSException, IOException {
        System.out.println("*****TopicPersist1号");

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("peifeng");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);

        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark...");

        connection.start();

        Message message = topicSubscriber.receive();
        while(null != message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("收到的TopicPersist：" + textMessage.getText());
            message = topicSubscriber.receive(1000L);
        }

        session.close();
        connection.close();

        /**
         * 一定要先运行一次消费者，等于向 MQ 注册，类似我订阅了这个主题
         * 然后再运行生产者发送消息，此时，
         * 无论消费者是否在线，都会接收到，不在线的话，下次连接的时候，会把没有收过的消息都接收下来。
         */
    }
}
