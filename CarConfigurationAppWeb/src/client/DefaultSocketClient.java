/*
 * Default socket client class for opening the connection and receiving/ sending data from/to server
 * Author: Vignan Uppugandla
 */
package client;

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
	/*Opens the connection with server*/
	public boolean openConnection(){
		try{
			sock = new Socket(strHost, iPort);
			System.out.println("Client started.......");
		}catch(IOException socketError){
			if(DEBUG) System.err.println("Unable to connect to "+ strHost);
			return false;
		}

		try {
		     pw = new PrintWriter (sock.getOutputStream(), true);
		     br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
	       }
	       catch (IOException e){
	         if (DEBUG) System.err.println("Error closing socket to " + strHost);
	       }       
	    }
	/*Method to send protocols to server*/
	public void sendOutput(String strOutput){
		  pw.println(strOutput);
	}
	/*Method to handle the input from user*/
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
	/*getter for socket details*/
	public Socket  getSocket(){
		return sock;
	}

}
