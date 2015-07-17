/*
 * Default socket client class for opening the connection and receiving/ sending data from/to client
 * Author: Vignan Uppugandla
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {
	
	protected Socket sock;
	private String strHost;
	private int iPort;
	
	private PrintWriter pw;
	protected BufferedReader br;
	/*parameterized  constructor with accepting socket object*/
	public DefaultSocketClient(Socket sock){
		this.sock=sock;
	}
	/*parameterized  constructor with accepting host and port details*/
	public DefaultSocketClient(String strHost, int iPort){
		setHost(strHost);
		setPort(iPort);
	}
	/*run method to start executing thread*/
	public void run(){
		if(openConnection()){
			handleSession();
			closeSession();
		}
	}
	/*Opens the connection with Client*/
	public boolean openConnection(){
		try {
		     pw = new PrintWriter (sock.getOutputStream(), true);
		     InputStreamReader isr = new InputStreamReader(sock.getInputStream());
		     br = new BufferedReader(isr);
		   }
		  catch (Exception e){
		     if (DEBUG) System.err.println
		       ("Unable to obtain stream to/from " + strHost);
		     return false;
		  }
	return true;
	}
	/*Handles the session with reading lines from user*/
	public void handleSession(){
		String strInput = "";
		  if (DEBUG) System.out.println ("Handling session with "+ strHost + ":" + iPort);
		  try {
		    while ( (strInput = br.readLine()) != null)
		    handleInput (strInput);
		  }
		  catch (IOException e){
		  if (DEBUG) System.out.println ("Handling session with "+ strHost + ":" + iPort);
		  }
	}
	/*Closing the session of the thread*/
	public void closeSession(){
       try {
          pw = null;
          br = null;
          sock.close();
		 System.out.println("Client Session End");
       }
       catch (IOException e){
         if (DEBUG) System.err.println("Error closing socket to " + strHost);
       }       
    }	/*Method to send protocols to client*/
	
	public void sendOutput(String strOutput){
		  pw.println(strOutput);
	}
	/*Method to handle the input from client*/
	public void handleInput(String strInput){
		System.out.println(strInput);
	}
	/*setter for host string*/
	public void setHost(String strHost){
		this.strHost = strHost;
	}
	/*setter for port*/
	public void setPort(int iPort){
		this.iPort = iPort;
	}
	/*getter for Host*/
	public String getHost() {
		return strHost;
	}

}
