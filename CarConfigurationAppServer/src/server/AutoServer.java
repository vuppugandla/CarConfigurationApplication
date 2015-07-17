/*Server Interface to implement methods for parsing, gettingmodels 
 * Author: Vignan Uppugandla
 */
package server;

import java.net.Socket;
import java.util.Properties;

public interface AutoServer {
	
	public void parseProperties(Properties props);
	
	public String getAllModels();
	
	public void getModel(String modelName, Socket sock);
	
	public void serializeAuto();
	
	public void deserializeAuto();

}
