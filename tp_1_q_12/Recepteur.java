
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Recepteur {
	
	
	public static void main(String[] args) 
	{
		try{
			InitialContext context = new InitialContext();
			TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/CFPremierEssai");
			Topic topic = (Topic) context.lookup("jms/topic");
			
			TopicConnection connection = connectionFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			connection.start();

			TopicSubscriber subscriber = session.createSubscriber(topic);
				rec asyncSubscriber = new rec();
			subscriber.setMessageListener(asyncSubscriber);
			Thread.sleep(30000);
			System.out.println("the end ...");
		

		}
		catch(NamingException nEx){
			System.out.println("exception JNDI");
		}
		catch(JMSException jmsEx){
			System.out.println("exception JMS");
		}
		catch(InterruptedException interExc){
			System.out.println("Thread exception");
		}
		finally{
			//	connection.close();
		}
	}
}


