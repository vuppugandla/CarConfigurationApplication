/*This is the heart of the server.
 * Server is started here.
 * enables the socket and starts the threads
 * Author: Vignan Uppugandla
 */
package server;

import java.io.IOException;
import java.net.*;

public class CarConfigServer {
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	
	/*Default constructor to instantiate the socket to 4444*/
	public CarConfigServer(){
		try{
			serverSocket = new ServerSocket(4444);
		}catch(IOException e){
			System.err.println("Could not listen on port: 4444");
            System.exit(1);
		}
	}
	/*Run the server, start the thread*/
	public void runCarConfigureServer(){
		try {
	        while(true){
		        clientSocket = serverSocket.accept();
		        CarConfigServerSocket carSocket = new CarConfigServerSocket(clientSocket);
		        carSocket.start();
		        System.out.println("Server Started........'");
	        }
	    } catch (IOException e) {
	        System.err.println("Accept failed.");
	        System.exit(1);
	    }
	}

}
