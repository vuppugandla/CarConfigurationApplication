/*
 * this is the driver class for starting the client
 * Author: Vignan Uppugandla
 */
package driver;

import client.CarConfigClientSocket;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		/*Starting the client from here*/
		CarConfigClientSocket clientSocket = new CarConfigClientSocket("localhost", 4444);
		clientSocket.start();
	}
}
