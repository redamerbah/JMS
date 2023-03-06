import javax.naming.*;
import javax.jms.*;
import javax.ejb.*;
import java.lang.* ; 


@MessageDriven(mappedName="jms/QRecepteur")
public class Recepteur implements MessageListener {
		
	public void onMessage(Message msg) {
		TextMessage tmsg = null;
		try {
			if ( msg instanceof TextMessage ){
				tmsg = (TextMessage) msg;
				InitialContext context = new InitialContext();
				ITraiteMessage obj = (ITraiteMessage) context.lookup("ITraiteMessage");
			
				String str = obj.traitement(tmsg.getText());
				System.out.println (str);
			}  
			else{ 
				System.out.println ("erreur d'envoie heheheh");
			}
		}
		catch (NamingException e) {
			e.printStackTrace( );
		}catch (JMSException e) {
			e.printStackTrace( );
			
		}

	}
}

