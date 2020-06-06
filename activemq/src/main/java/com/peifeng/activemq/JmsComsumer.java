package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class JmsComsumer
{
    public static final String ACTIVEMQ_URL = "tcp://xxxx:61616";
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
        // 5、创建消息的消费者
        MessageConsumer consumer = session.createConsumer(queue);
        /*
         * 同步阻塞方式 receive()
         * 订阅者或者接收者调用 MessageConsumer 的 receive() 方法来接收消息，
         * receive 方法在能够接收到消息之前（或超时之前）将一直阻塞。
         */
        while(true){
            TextMessage message = (TextMessage)consumer.receive();
            if(message != null){
                System.out.println("*********消费者接收到消息："+message.getText());
            }else{
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();

    }
}
