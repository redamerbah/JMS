
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Emetteur{

    public static void main(String[] args) {
    	try{
        InitialContext context = new InitialContext();
        // objet context pour rechercher une QueueConnectionFactory  et une Queue.
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/CFPremierEssai");
		Queue queue = (Queue) context.lookup("jms/QRecepteur");
		QueueConnection connection = connectionFactory.createQueueConnection();
		QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		//creation d'un objet QueueSender
		connection.start();
			
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
       // boucle pr envoyer le message 5 fois
            for(int i=0; i<5; i++){
				// on definit le message en utilisanat la methode setText
                msg.setText("Hello Reda how are you ! ....  " +i); 
                // envoie de message
                sender.send(msg);
            
        }

     // Si une exception est levée lors de l'exécution du code, elle sera capturée par l'un des deux blocs catch qui affichent les messages d'erreur
        }catch(NamingException ne){
			ne.printStackTrace();
		}
		catch(JMSException jmse){
			jmse.printStackTrace();
		}
		// fin programme
		 System.exit(0);
    }   

}
