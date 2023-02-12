import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;


public class Server extends UnicastRemoteObject implements RMIInterface {

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public String testString(String string) throws RemoteException {
        System.out.println("Connection received with string: " + string);
        if (Objects.isNull(string) || string.trim().isEmpty()) {
            return "Incorrect string. Please, enter a valid string";
        }
        System.out.println("Modifying....");

        StringBuilder builder = new StringBuilder();
        String[] strings = string.split(" ");

        builder.append(strings[0]).append(" ");
        if (strings.length == 1) {
            return "Modified string: " + builder.toString().trim();
        }
        for (int i = 1; i < strings.length; i++) {
            char c = (char) strings[i].getBytes(StandardCharsets.UTF_8)[0];
            builder.append(c).append(strings[i]).append(" ");
        }
        return "Modified string: " + builder.toString().trim();
    }

    public static void main(String[] args) throws RemoteException {
        try {
            Naming.rebind("//localhost/MyServer", new Server());
            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
