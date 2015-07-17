/*
 * this is the driver class for Starting the server.
 * Author: Vignan Uppugandla
 */
package driver;

import server.CarConfigServer;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		/*Starting the server from here*/
		CarConfigServer server = new CarConfigServer();
		server.runCarConfigureServer();
	}
}
