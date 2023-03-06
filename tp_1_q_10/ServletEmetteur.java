
import javax.jms.* ; 
import javax.naming.* ; 
import java.lang.* ; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.* ;
import java.lang.* ; 

public class ServletEmetteur extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
	{
		  PrintWriter out = res.getWriter();
		  String message = req.getParameter("message") ; 
		  
		  
		  QueueConnection connection = null;
		try{
			InitialContext context = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/CFPremierEssai");
			Queue queue = (Queue) context.lookup("jms/QRecepteur");
			connection = connectionFactory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			connection.start();
			
			QueueSender sender    = session.createSender(queue);
			TextMessage msgToSend = session.createTextMessage();
			
			msgToSend.setText(message); 
			msgToSend.setStringProperty("receiver","Reda");
			sender.send(msgToSend);
		
		}
		catch(NamingException nEx){
			out.println("exception java naming");
		}
		catch(JMSException jmsEx){
			out.println("exception jms");
		}
		finally{
			try{
				connection.close();
			}
			catch(JMSException jmsex){
				out.println("fermeture de connexion échouée");
			}
		}
		out.println("Le message a été envoyé"); 
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
	{
		  doGet(req,res); 
	}
}

