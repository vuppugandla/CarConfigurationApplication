/*
  * This is class which implements the communication between client and server. Have different protocols to implement different actions. 
 * This extends default socket client
 * Author: Vignan Uppugandla
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Properties;

public class CarConfigServerSocket extends DefaultSocketClient{
	/*Static final variables to implement various protocols*/
    private static final int WAITING = 0;
    private static final int RECEIVINGFILE = 1;
    private static final int GETTINGALLAUTODETAILS = 2;
    private static final int SELECTING = 3;
    private static final int END = 4;

    private int state = WAITING;
    
    /*Parameterized  constructors for readinf socket*/
	public CarConfigServerSocket(Socket sock) {
		super(sock);
	}
	/*Run method where thread starts
	 * over rides defaultsocketclient run method*/
	public void run(){
		if(openConnection()){
			handleSession();
			closeSession();
		}
	}
	/*Handles the thread session, and implements various protocols for actions*/
	public void handleSession(){
		BuildCarModelOptions auto = new BuildCarModelOptions();
		String clientInput = null;
		try{
			while(true){
				if(state == WAITING){//when waiting for protocol from client
					System.out.println("State: Waiting");
					clientInput = br.readLine();
					processInput(clientInput);
				}
				else if(state == RECEIVINGFILE){//when receiving file from client
					System.out.println("State: Receiving File");
							readFile(sock);
							sendOutput("Received the file and Automobile object created successfully");
					System.out.println("State: Received File. Automobile created");
					state = WAITING;
				}
				else if(state == GETTINGALLAUTODETAILS){//when sending all models in server to client
					System.out.println("State: Getting Models");
					sendOutput(auto.getAllModels());
					System.out.println("State: Transferred models to Client");
					state = WAITING;
				}
				else if(state == SELECTING){//when selecting a particular model and send to client
					System.out.println("State: Selecting Model");
					clientInput = br.readLine();
					System.out.println(clientInput);
					auto.getModel(clientInput, sock);
					System.out.println("State: Model Object Sent to Client");
					state = WAITING;
				}
				else if(state ==END){
					break;
				}
			}
		}catch(IOException e){
			 if (DEBUG) System.err.println("Unable to obtain stream to/from " + super.getHost());
		}
	}
	/* processes input from user and passes to specific state*/
	public void processInput(String clientInput){
		if(clientInput.equals("Upload")){
			state = RECEIVINGFILE;
		}
		else if(clientInput.equals("Choose")){
			state = GETTINGALLAUTODETAILS;
		}
		else if(clientInput.equals("Select")){
			state = SELECTING;
		}
		else if(clientInput.equalsIgnoreCase("Client Quit")){
			state = END;
		}
		else{
			sendOutput("Invalid Input");
			state = WAITING;
		}
	}
	/*reading the properties file from socket and parsing it to proprties object to lead details*/
	public void  readFile(Socket clientSock){
		ObjectInputStream objInput = null;
		try {
			objInput = new ObjectInputStream(clientSock.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Properties props =null;
		try {
			props = (Properties) objInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BuildCarModelOptions auto = new BuildCarModelOptions();
		auto.parseProperties(props);
	}

}
