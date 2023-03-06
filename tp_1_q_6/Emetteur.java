
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Emetteur {
   
    public static void main(String[] args) {
	
	try {	
		InitialContext messaging;
		QueueConnection connection = null;
		messaging = new InitialContext();
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) messaging.lookup("jms/CFPremierEssai");
		Queue queue = (Queue) messaging.lookup("jms/QRecepteur");
		connection = connectionFactory.createQueueConnection();
		QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		connection.start();  
		
		QueueSender emetteur =session.createSender(queue);
		

		// Création d'une queue temporaire
		TemporaryQueue tempQueue = session.createTemporaryQueue();
		QueueReceiver receiver = session.createReceiver(tempQueue);

		TemporaryQueue tempQueue2 = session.createTemporaryQueue();
		QueueReceiver receiver2 = session.createReceiver(tempQueue2);


		String correlationId1 = "1";
		String correlationId2 = "2";
		TextMessage msg = session.createTextMessage();
		msg.setStringProperty("recepteur","Reda");
		msg.setText("Bonjour Reda");
		msg.setJMSReplyTo(tempQueue);
		msg.setJMSCorrelationID(correlationId1);
		emetteur.send(msg);

		TextMessage msg2 = session.createTextMessage();
		msg2.setStringProperty("recepteur","Reda");
		msg2.setText("Bonjour Reda c'est le 2eme msg");
		msg2.setJMSReplyTo(tempQueue);
		msg2.setJMSCorrelationID(correlationId2);
		emetteur.send(msg2);
	    
		//Message d'accusé
		TextMessage msgQ = (TextMessage) receiver.receive();
		TextMessage msgQ2 = (TextMessage) receiver.receive();
		System.out.println(msgQ.getText());
		System.out.println(msgQ2.getText());
 		connection.close();

 		
 
	} catch (JMSException e){ 
		e.printStackTrace( ); 
	}
	catch(NamingException e){
		e.printStackTrace();
	}

	System.exit(0);
	}

    
}
