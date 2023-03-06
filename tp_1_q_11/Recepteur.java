import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Recepteur{

	

    public static void main(String[] args) 
    {
	try {
			InitialContext context = new InitialContext();
			TopicConnectionFactory connectionFactory =  (TopicConnectionFactory) context.lookup("jms/CFPremierEssai");
			Topic topic = (Topic) context.lookup("jms/topic");
		   
		    TopicConnection connection = connectionFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			connection.start();
		
			TopicSubscriber subscriber = session.createSubscriber(topic);
			TextMessage message = (TextMessage) subscriber.receive();
			System.out.println("message recu est : " + message.getText());
			connection.close();

        }catch (JMSException e){ 
		e.printStackTrace( ); 
	}
	catch(NamingException e){
		e.printStackTrace();
	}
	System.exit(0);

	    }}  


