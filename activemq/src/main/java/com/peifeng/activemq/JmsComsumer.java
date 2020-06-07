package com.peifeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class JmsComsumer
{
    public static final String ACTIVEMQ_URL = "tcp://120.77.244.17:61616";
    public static final String QUEUE_NAME = "peifeng_queue01";
    public static void main( String[] args ) throws JMSException, IOException {
        System.out.println("*****消费者1号");
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
/*        while(true){
            TextMessage message = (TextMessage)consumer.receive();
            if(message != null){
                System.out.println("*********消费者接收到消息："+message.getText());
            }else{
                break;
            }
        }*/

        /*
         * 通过监听的方式来消费消息
         * 异步非阻塞方式（监听器 onMessage()）
         * 订阅者或接收者通过 MessageConsumer 的 setMessageListener 注册一个消息监听器，
         * 当消息到达之后，系统自动调用监听器 MessageListener 的 onMessage 方法
         *
         */
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(null != message && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("*********消费者接收到消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read(); // press any key to exit  保证控制台不关闭，才能够接收到消息

        consumer.close();
        session.close();
        connection.close();

        /**
         * 1、先生产，只启动 1 号消费者，1 号消费者能消费消息吗？ Y
         * 2、先生产，先启动 1 号消费者再启动 2 号消费者，问题：2 号消费者还能消费消息吗？
         *  2.1  1 号可以消费吗？ Y
         *  2.2  2 号可以消费吗？ N
         *
         * 3、先启动 2 个消费者，再生产 6 条消息，请问，消费情况如何？
         *  3.1  2 个消费者都有 6 条
         *  3.2  先到先得，6 条全给一个
         *  3.3  一人一半  Y
         */

    }
}
