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
			QueueSession session = connection.createQueueSession(false,Session.CLIENT_ACKNOWLEDGE);
			connection.start();
				
			QueueReceiver receiver = session.createReceiver(queue);
			TextMessage msg = (TextMessage) receiver.receive();
				System.out.println("Message est : "+msg.getText());
			Thread.sleep(5000);
			msg.acknowledge(); 
			System.out.println("le message est supprimé");
        }catch(NamingException ne){
			ne.printStackTrace();
		}
		catch(JMSException jmse){
			jmse.printStackTrace();
		}catch(InterruptedException intExc){
			intExc.printStackTrace();
		}
		 System.exit(0);
    }  

}
