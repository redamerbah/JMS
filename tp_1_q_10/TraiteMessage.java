import javax.ejb.*;
import javax.persistence.* ;

@Stateless
public class TraiteMessage implements ITraiteMessage
{
	public String traitement(String msg) {
			return "Le traitement du message " +msg+ "est en cours patientez svp " ;
	}
}