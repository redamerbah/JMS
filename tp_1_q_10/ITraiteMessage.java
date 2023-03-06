import javax.ejb.Remote;

@Remote
public interface ITraiteMessage
{
	public String traitement(String msg) ;
}