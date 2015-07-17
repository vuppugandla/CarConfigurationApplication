/*
 * This class implements methods which for loading the parsing the properties file and sending it to server then receiving response
 * Author: Vignan Uppugandla
 */
package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

public class CarModelOptionsIO {
	/*default constructor*/
	public CarModelOptionsIO(){}
	
	/*method to parse properties file and send to server*/
	public void  loadProperties(String fileName, Socket sock){
			Properties props = new Properties();
			FileInputStream inFile = null;
				
			try {
					inFile = new FileInputStream(fileName);
				} catch (FileNotFoundException e) {
					BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
					String s = null;
					System.out.println("Invalid File Name. Try with correct File Name:");
				    try {
						 s = bufferRead.readLine();
						inFile = new FileInputStream(s);
					} catch ( IOException e1) {
						e1.printStackTrace();
					}
				    
			}
			
			try {
				props.load(inFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				ObjectOutputStream	outObj = new ObjectOutputStream(sock.getOutputStream());
				outObj.writeObject(props);
				outObj.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	/*reads and validates response from server on uploading model details*/
	public boolean response(String response){
		if(response.equals("Received the file and Automobile object created successfully")) return true;
		
		return false;
	}

	
}
