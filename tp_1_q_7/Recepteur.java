import javax.jms.* ; 
import javax.ejb.* ; 

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/QRecepteur"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class Recepteur implements MessageListener {
	
	public void onMessage(Message msg) {
		TextMessage textMsg = null;
		try {
			if ( msg instanceof TextMessage ){
				textMsg = (TextMessage) msg;
				System.out.println ("le Message reçu est : " + textMsg.getText( ));
			}  
			else{ 
				System.out.println ("pas de message hehehe");
			} 
		}
		catch (JMSException e) {
			e.printStackTrace( );
			
		}

	}
}
