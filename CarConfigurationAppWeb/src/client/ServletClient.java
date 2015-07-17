/* Wrapper class over client socket to create one static instance for use of client socket.
 * Author: Vignan Uppugandla
 */

package client;

import java.io.IOException;
import java.util.ArrayList;

import model.Automobile;

public class ServletClient extends CarConfigClientSocket{
	private static ServletClient instance;
	
	private ServletClient(String host, int port) {
		super(host,port);
	}
	
	public static ServletClient getInstance() {
		if(instance ==null) {
			instance = new ServletClient("localhost",4444);
			instance.start();
		}
		return instance;
	}
	
	@Override
	public void run() {
		openConnection();
		super.handleSession();
	}
	
	/*Method to get details of the models*/
	public ArrayList<String> getModels(){
		ArrayList<String> models = new ArrayList<String>();
		sendOutput("Choose");
		try{
			String fromServer = br.readLine();
			String[] data = fromServer.split(",");
			for(int i=0; i<data.length; i++) {
				models.add(data[i]);
			}
		}catch (IOException e) {
		e.printStackTrace();
	}
		return models;
	}
	
	/*Method to get automobile object of the selected model*/
	public Automobile getModelObject(String modelName) {
		ConfigureAutomobile ca = new ConfigureAutomobile();
		if (modelName !=null) {
			sendOutput("Select");
			sendOutput(modelName);
			ca.importAutomobile(sock);
		}
		return ca.getAuto();
	}

}
