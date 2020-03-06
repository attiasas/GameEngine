package GTK.Utils.Network;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This class represents a client that can communicates with a server and execute a given strategy
 **/
public class Client
{
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    /**
     * Constructor
     * @param serverIP - IP address of the server
     * @param serverPort - the port in the server to connect
     * @param clientStrategy - strategy to execute
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy)
    {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    /**
     * Execute strategy
     */
    public void communicateWithServer()
    {
        try
        {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
