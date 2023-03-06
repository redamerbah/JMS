import javax.jms.* ; 
import javax.ejb.* ; 

@MessageDriven(mappedName="jms/QRecepteur")
public class Recepteur implements MessageListener {
	
	public void onMessage(Message msg) {
		TextMessage textMsg = null;
		try {
			if ( msg instanceof TextMessage ){
				textMsg = (TextMessage) msg;
				System.out.println ("Le Message reçu est : " + textMsg.getText( ));
			}  
			else{ 
				System.out.println ("pas de message text hehehe");
			} 
		}
		catch (JMSException e) {
			e.printStackTrace( );
			
		}

	}
}
