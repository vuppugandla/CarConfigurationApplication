/*This class implements the methods declared in Auto Sever interface
 * Author: Vignan Uppugandla
 */
package server;

import java.net.Socket;
import java.util.Properties;

import adapter.BuildAuto;

public class BuildCarModelOptions implements AutoServer {
		private AutoServer aServer;
	
		/*instantiating auto server*/
	public BuildCarModelOptions(){
		aServer = new BuildAuto();
	}
	/*building automobile object*/
	public void parseProperties(Properties props){
		aServer.parseProperties(props);
	}
	/*getting models*/
	public String  getAllModels(){
		return aServer.getAllModels();
	}
	/*getting a specific model*/
	public void getModel(String modelName, Socket sock){
		 aServer.getModel(modelName, sock);
	}
	
	/*serializing the auto details for use with the servlets and jsp*/
	public void serializeAuto() {
		aServer.serializeAuto();
	}
	
	/*deserializing the auto details for use with the servlets and jsp*/
	public void deserializeAuto() {
		aServer.deserializeAuto();
	}
}
