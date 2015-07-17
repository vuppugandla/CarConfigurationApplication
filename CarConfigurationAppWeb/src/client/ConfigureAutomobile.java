/*
 * This class is used to configure the received automobile object on selection. Takes user input for configuration and calculates the final price
 * Author: Vignan Uppugandla
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.Automobile;

public class ConfigureAutomobile {
	private Automobile auto =null;
	
	/*Default constructor*/
	public ConfigureAutomobile(){}
	
	/* to read the automobile from server*/
	public void importAutomobile(Socket sock) {
		ObjectInputStream objInput = null;
		
		try {
			objInput = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			auto = (Automobile)objInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/*Checks if the no automobile object is received*/
	public boolean autoIsNotNull(){
		if(auto!=null) return true;
		else return false;
	}
	/*Print the received auto details*/
	public void printModelDetails(){
		auto.printAutoDetails();
	}
	/*Configuring the model with various options*/
	public void configureModels() {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String choice = null;
		String optSetName = null;
		for(int i=0; i<auto.getOptionSet().size(); i++){
			optSetName = auto.getOptSetName(i);
			System.out.println("Choose an option in: "+optSetName);
			try {
				choice = stdIn.readLine();
				auto.setOptionChoice(optSetName, choice);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*get the final total price and print the details with selected options*/
	public void finalModelDetails() {
		String optSetName = null;
		for(int i=0; i<auto.getOptionSet().size(); i++){
			optSetName = auto.getOptSetName(i);
			System.out.println(optSetName+": "+auto.getOptionChoice(optSetName));
		}
		System.out.println("Final Price: "+auto.getTotalPrice());
	}

	public Automobile getAuto() {
		return auto;
	}
}