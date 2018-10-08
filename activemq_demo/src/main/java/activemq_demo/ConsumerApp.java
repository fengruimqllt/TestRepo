package activemq_demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class ConsumerApp implements MessageListener {
	
	 private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	 private static final String SUBJECT = "test-activemq-queue";
	   
	   
	public static void main(String[] args) throws JMSException {
		ConnectionFactory  connectionFactory  = new ActiveMQConnectionFactory(BROKER_URL);
		Connection conn = connectionFactory.createConnection();
		conn.start();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		 //通过会话创建目标
        Destination dest = session.createQueue(SUBJECT);
        //创建mq消息的消费者
        MessageConsumer consumer = session.createConsumer(dest);

        //初始化MessageListener
        ConsumerApp me = new ConsumerApp();

        //给消费者设定监听对象
        consumer.setMessageListener(me);
	}

	@Override
	public void onMessage(Message arg0) {
		TextMessage txtMessage = (TextMessage)arg0;
		try {
			System.out.println(txtMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
