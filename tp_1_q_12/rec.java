
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 

public class rec implements MessageListener{
	
	public void onMessage(Message msg){
			if( msg instanceof TextMessage ){
				try 
			{
				TextMessage message = (TextMessage) msg; 
				System.out.println (message.getText());			
			}
			catch (JMSException e) 
			{
				e.printStackTrace();
			}
				
			}
	}
	

}


