import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {

     String testString(String string) throws RemoteException;

}
