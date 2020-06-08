package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class JmsComsumerTX
{
    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String QUEUE_NAME = "peifeng_queue01";
    public static void main( String[] args ) throws JMSException, IOException {
        System.out.println("*****消费者1号");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

//        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        /*
         * 同步阻塞方式 receive()
         * 订阅者或者接收者调用 MessageConsumer 的 receive() 方法来接收消息，
         * receive 方法在能够接收到消息之前（或超时之前）将一直阻塞。
         */
        while(true){
            TextMessage message = (TextMessage)consumer.receive(3000L);
            if(message != null){
                System.out.println("*********消费者接收到消息："+message.getText());
//                message.acknowledge();
            }else{
                break;
            }
        }

        consumer.close();
        // 开启事务之后，消费者也需手动提交事务，否则会重复消费，无法将消息从队列中消费掉
        // 开启事务，将自动签收消息，不需要手动签收
        session.commit();

        session.close();
        connection.close();
    }
}
