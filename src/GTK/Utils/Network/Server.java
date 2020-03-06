package GTK.Utils.Network;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This class represent a server that can communicate with multiple clients and execute a given strategy
 **/
public class Server
{
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService threadPoolExecutor;
    private int poolSize;

    /**
     * Constructor
     * @param port - port to listen for clients
     * @param poolSize - number of threads handling the server
     * @param listeningInterval - interval before TimeOut
     * @param serverStrategy - strategy to execute
     */
    public Server(int port,int poolSize , int listeningInterval, IServerStrategy serverStrategy)
    {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        this.poolSize = poolSize;
    }

    /**
     * Boot server in a different thread and start to listen for clients
     */
    public void start()
    {
        new Thread(() -> {
            runServer();
        }).start();
    }

    /**
     * main server loop
     */
    private void runServer()
    {
        try
        {
            threadPoolExecutor = Executors.newFixedThreadPool(poolSize);

            // init
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);

            while (!stop)
            {
                try
                {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    threadPoolExecutor.execute(() -> handleClient(clientSocket));

                } catch (SocketTimeoutException e)
                {

                }
            }

            threadPoolExecutor.shutdown();
            threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
            serverSocket.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Execute strategy
     * @param clientSocket - client socket
     */
    private void handleClient(Socket clientSocket)
    {
        try
        {
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Stop the server form running and shutdown
     */
    public void stop()
    {
        stop = true;
    }
}
