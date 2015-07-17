/*
 * Interface for opening connection, closing and handling the session
 * Author: Vignan Uppugandla
 */
package client;

public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
