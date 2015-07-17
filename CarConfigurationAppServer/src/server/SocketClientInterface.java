/*
 * Interface for opening connection, closing and handling the session
 * Author: Vignan Uppugandla
 */
package server;

public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
