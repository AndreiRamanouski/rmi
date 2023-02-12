import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Client {

    private static RMIInterface rmiInterface;

    public static void main(String[] args) {

        try {
            rmiInterface = (RMIInterface) Naming.lookup("//localhost/MyServer");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        String txt = JOptionPane.showInputDialog("Please, enter the string to modify");

        String response = null;
        try {
            response = rmiInterface.testString(txt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, response);

    }

}
