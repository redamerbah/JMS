
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Recepteur{

    public static void main(String[] args) {
    	try{
	        InitialContext context = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/CFPremierEssai");
			Queue queue = (Queue) context.lookup("jms/QRecepteur");
			QueueConnection connection = connectionFactory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			connection.start();
				
			QueueReceiver receiver = session.createReceiver(queue);
			TextMessage msg = (TextMessage) receiver.receive();
			System.out.println("le Message envoyé par l'emetteur est : "+msg.getText());
        }catch(NamingException ne){
			ne.printStackTrace();
		}
		catch(JMSException jmse){
			jmse.printStackTrace();
		}
		 System.exit(0);
    }  

}
