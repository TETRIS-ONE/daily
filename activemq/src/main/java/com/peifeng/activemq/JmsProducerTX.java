package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class JmsProducerTX
{
    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String QUEUE_NAME = "peifeng_queue01";
    public static void main( String[] args ) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

//        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("TXmsg---" + i);
            producer.send(textMessage);
        }

/*
        try{
            // ok session.commit();
        }catch (Exception e){
            e.printStackTrace();
            // error
            session.rollback();
        }finally {
            if(null != session){
                session.close();
            }
        }
*/

        producer.close();

        // 开启事务之后，需要手动提交事务
        session.commit();

        session.close();
        connection.close();

        System.out.println("*********消息发送到 MQ 完成");
    }
}
