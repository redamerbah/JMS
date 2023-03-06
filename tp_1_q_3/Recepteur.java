
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
            //receive() est appelée pour recevoir un message de la file d'attente.
			TextMessage msg = (TextMessage) receiver.receive();
			
			// nom de recepteur qui passe en param/args
			System.out.println("Le Nom Récepteur est  : "+args[1]);
			System.out.println("le Message envoyé par l'emetteur est : "+msg.getText());
			Thread.sleep(Integer.parseInt(args[0]));

        }catch(NamingException ne){
			ne.printStackTrace();
		}
		catch(JMSException jmse){
			jmse.printStackTrace();
		}
		catch(InterruptedException intExc){
			intExc.printStackTrace();
		}
        // System.exit(0) est appelée pour terminer le programme avec un code de sortie 0
		 System.exit(0);
    }  

}
