
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class Emetteur{

    public static void main(String[] args) {
        try{
        InitialContext context = new InitialContext();
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/CFPremierEssai");
        Queue queue = (Queue) context.lookup("jms/QRecepteur");
        QueueConnection connection = connectionFactory.createQueueConnection();
        QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
        connection.start();
            
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
       
        for(int i=0; i<5; i++){
                if(args.length >0 && i<args.length){
                    msg = session.createTextMessage();
                    msg.setText("message from Emetteur avec recepteur"); 
                    msg.setStringProperty("recepteur",args[i]);
                    sender.send(msg);
                }else
                {
                    msg = session.createTextMessage();
                    msg.setText("message from Emetteur sans recepteur"); 
                    sender.send(msg);
                }
            }

     
        }catch(NamingException ne){
            ne.printStackTrace();
        }
        catch(JMSException jmse){
            jmse.printStackTrace();
        }
         System.exit(0);
    }   

}
