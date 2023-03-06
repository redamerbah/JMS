
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Recepteur {
   public static void main (String [] args){

	QueueConnection connection =null;
	QueueReceiver receiver;
	
	try{
			InitialContext messaging = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) messaging.lookup("jms/CFPremierEssai");
            Queue queue = (Queue) messaging.lookup("jms/QRecepteur");
            connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();

      
            receiver = session.createReceiver(queue, "recepteur = 'Reda' ");
            TextMessage msg = (TextMessage) receiver.receive();
            TextMessage msg2 = (TextMessage) receiver.receive();
            //TextMessage msg = (TextMessage) receiver.receive();
           // msg.acknowledge();
         	//if(args.length >= 2 && args[1].equals("1")){

         	QueueSender sender = session.createSender((Queue) msg.getJMSReplyTo());
            TextMessage msgresp = session.createTextMessage();
            msgresp.setText("Replay to :" + msg.getJMSCorrelationID());
            sender.send(msgresp);

            System.out.println("Message recu : " + msg.getText());
         	//}else if(args.length >= 2 && args[1].equals("2")){

         	QueueSender sender2 = session.createSender((Queue) msg2.getJMSReplyTo());
            TextMessage msgresp2 = session.createTextMessage();
            msgresp2.setText("Replay to 2eme message :" + msg2.getJMSCorrelationID());
            sender2.send(msgresp2);

            System.out.println("Message 2 recu : " + msg2.getText());

         	//}
         	//else{
         		//System.out.println("pas de message avec ce ID hhhhh");
         	//}
      
	} catch (JMSException e){ 
		e.printStackTrace( ); 
	}
	catch(NamingException e){
		e.printStackTrace();
	}
	finally{
		try{
			if (connection!=null) connection.close();
		} catch (JMSException e){ 
			e.printStackTrace( ); 
		}
	}
	System.exit(0);
	}


}
