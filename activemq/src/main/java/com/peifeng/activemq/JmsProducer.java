package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class JmsProducer
{
//    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "peifeng_queue01";
    public static void main( String[] args ) throws JMSException {
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
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5、创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        // 6、通过使用 producer 生产 3 条消息发送到 MQ 的队列里面
        for (int i = 1; i <= 6; i++) {
            // 7、创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            // 8、通过 producer 发送给 MQ
            producer.send(textMessage);
        }
        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("*********消息发送到 MQ 完成");
    }
}
