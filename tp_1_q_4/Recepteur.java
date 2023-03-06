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
				
			QueueReceiver receiver ;
			TextMessage msg ;
			
			if(args.length>0)
			{
				for (int i=0;i<args.length ;i++ ) {
						receiver = session.createReceiver(queue,"recepteur ='"+args[i]+"'");
						msg = (TextMessage) receiver.receive();
						System.out.println("Le Nom de Récepteur : "+args[i]);
						System.out.println("Le Message envoyé par l'emetteur est : "+msg.getText());
						Thread.sleep(4000);
				}

			}
			if(args.length==0)
			{
				receiver = session.createReceiver(queue);
				msg = (TextMessage) receiver.receive();
				System.out.println("Nom Récepteur : Inconnu");
				System.out.println("Le Message envoyé par l'emetteur est : "+msg.getText());
				Thread.sleep(Integer.parseInt("3000"));

			}
			

        }catch(NamingException ne){
			ne.printStackTrace();
		}
		catch(JMSException jmse){
			jmse.printStackTrace();
		}
		catch(InterruptedException intExc){
			intExc.printStackTrace();
		}
		 System.exit(0);
    }  

}
