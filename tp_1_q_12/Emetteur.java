
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Emetteur{

    public static void main(String[] args) {
    
			try{
			InitialContext context = new InitialContext();
			TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/CFPremierEssai");
			Topic topic = (Topic) context.lookup("jms/topic");
			TopicConnection connection = connectionFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			connection.start();
			TopicPublisher publisher = session.createPublisher(topic) ;
			
			TextMessage msgToPublishd = session.createTextMessage();
			


			for(int i=0; i<5; i++){
				// on definit le message en utilisanat la methode setText
                msgToPublishd.setText("Hello Reda how are you ! ....  " +i); 
                
                
                // envoie de message
				publisher.publish(msgToPublishd);
            
        }
			
			System.out.println("Message envoyé"); 
		}
		catch(NamingException nEx){
			System.out.println("exception java naming");
		}
		catch(JMSException jmsEx){
			System.out.println("exception jms");
		}
		finally{
			//	connection.close();
		}
    }   

	}
