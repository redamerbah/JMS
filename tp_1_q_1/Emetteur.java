
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
		// Création et démarrage de la connexion
		QueueConnection connection = connectionFactory.createQueueConnection();
		 // Création de la session
		QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		connection.start();
		//creation d'un objet QueueSender
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
        // on definit le message en utilisanat la methode setText
        msg.setText("Hello Reda how are you ! ....");
        // envoie de message
        sender.send(msg);
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
