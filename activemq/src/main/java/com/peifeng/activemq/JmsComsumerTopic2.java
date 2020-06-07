package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class JmsComsumerTopic2
{
    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String TOPIC_NAME = "peifeng_topic01";
    public static void main( String[] args ) throws JMSException, IOException {
        System.out.println("*****消费者2号");
        // 1、创建连接工厂，按照给定的 url 地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2、通过连接工厂，获得连接 connection
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 3、创建会话 session
        // transacted 事务
        // acknowledgeMode 签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地（队列还是主题）
        Topic topic = session.createTopic(TOPIC_NAME);
        // 5、创建消息的消费者
        MessageConsumer consumer = session.createConsumer(topic);

        // 通过监听的方式来消费消息
        consumer.setMessageListener((message) -> {
            if(null != message && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("*********消费者接收到消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read(); // press any key to exit  保证控制台不关闭，才能够接收到消息

        consumer.close();
        session.close();
        connection.close();

    }
}
