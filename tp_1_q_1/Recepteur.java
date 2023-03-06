
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
			TextMessage msgToReceive = (TextMessage) receiver.receive();
			// affichage de message envoyé par l'emetteur
			System.out.println("le Message envoyé par l'emetteur est : "+msgToReceive.getText());
			// Si une exception est levée lors de l'exécution du code, elle sera capturée par l'un des deux blocs catch qui affichent les messages d'erreur
        }catch(NamingException ne){
			ne.printStackTrace();
		}
		catch(JMSException jmse){
			jmse.printStackTrace();
		}
		// la méthode main se termine par l'appel de System.exit(0)
		 System.exit(0);
    }  

}
