package GTK.Utils.Network;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created By:      Assaf, on 06/03/2020.
 * Description:     This class represents an interface for client strategy when communicating with a server
 **/
public interface IClientStrategy
{
    /**
     * Strategy to execute when communicating with a server
     * @param inFromServer - InputStream of a server socket
     * @param outToServer - OutputStream of a server socket
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
