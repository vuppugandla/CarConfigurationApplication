/*
 * This is class which implements the communication between client and server. Have different protocols to implement different actions. 
 * This extends default socket client
 * Client is started from here.
 * Author: Vignan Uppugandla
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarConfigClientSocket extends DefaultSocketClient{
	/*Static final variables to implement various protocols*/
	private static final int WAITING = 0;
	private static final int SENTOPTIONS =1;
    private static final int UPLOADINGFILE = 2;
    private static final int SELECTING = 3;
    private static final int CONFIGURING = 4;
    private static final int END = 5;

    private int state = WAITING;

    /*Default constructors*/
	public CarConfigClientSocket(String host, int port) {
		super(host, port);//listens from localhost at port 4444
	}
	
	/*Run method where thread starts
	 * over rides defaultsocketclient run method*/
	public void run(){
		if(openConnection()){
			handleSession();
			System.out.println("Closing session Client");
			closeSession();
		}
	}
	
	/*Handles the thread session, and implements various protocols for actions*/
	public void handleSession(){
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		
		CarModelOptionsIO carIO = new CarModelOptionsIO();
		ConfigureAutomobile ca = new ConfigureAutomobile();
		
		while(true){
			if(state == WAITING){//comes here to request initial input from user
				System.out.println("Please select an option: Upload Details or Choose Options or Q to quit");
				state = SENTOPTIONS;
			}
			else if(state == SENTOPTIONS){//comes here to read options from user
				try {
					fromUser = stdIn.readLine();
					processInput(fromUser);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(state == UPLOADINGFILE){//when uploading file to server
				System.out.println("Please provide Properties file of the automobile");
				try {
					fromUser = stdIn.readLine();
					sendOutput("Upload");
					carIO.loadProperties(fromUser, super.getSocket());
						if(carIO.response(br.readLine())){//receives response on upload from server
							System.out.println("Successfully uploaded the Automobile details");
							state = WAITING;
						}else{
							System.out.println("Automobile details cannot be uploaded");
							state = WAITING;
						}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(state == SELECTING){//when selecting model name from user
				sendOutput("Choose");
				try {
					fromServer = br.readLine();
					if(fromServer.equals("Empty HashSet")){
						System.out.println("No Automobiles available. Try again later");
						state = END;
					}
					else{
						System.out.println("Available Models: \n"+ fromServer+"\nSelect one model from above:");
						state = CONFIGURING;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(state == CONFIGURING){//configuring the selected model
				try {
					fromUser= stdIn.readLine();
					sendOutput("Select");
					sendOutput(fromUser);
					ca.importAutomobile(sock);
					if(ca.autoIsNotNull()){
						ca.printModelDetails();
						ca.configureModels();
						ca.finalModelDetails();
						state = WAITING;
					}else{
						System.out.println("Model Name invalid");
						state = SELECTING;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			else if(state ==END){
				sendOutput("Client Quit");
				System.out.println("Client exiting \n Bye!!!!!");
				System.exit(0);
			}
		}
	}
	
	/* processes input from user and passes to specific state*/
	public void processInput(String fromUser){
		if(fromUser.equalsIgnoreCase("Upload Details") || fromUser.equalsIgnoreCase("Upload")){
			state = UPLOADINGFILE;
		}
		else if(fromUser.equalsIgnoreCase("Choose Options") || fromUser.equalsIgnoreCase("Choose")){
			state = SELECTING;
		}
		else if(fromUser.equalsIgnoreCase("Quit") || fromUser.equalsIgnoreCase("Q")){
			state = END;
		}
		else if(fromUser.equalsIgnoreCase("Server bought down!")){
			System.out.println("Server Down!!!!!!!!!!");
			state = END;
		}
		else{
			System.out.println("Invalid Input");
			state = WAITING;
		}
	}
	
	/*main method to start client*/
//	public static void main(String[] args){
//		CarConfigClientSocket clientSocket = new CarConfigClientSocket();
//		clientSocket.start();
//	}
	
}
