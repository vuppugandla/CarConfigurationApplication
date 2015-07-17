/*
 * This is an abstract class which is extended by BuildAuto class. it implements the methods declared by the interfaces.
 * All the methods in this class can be executed through BuildAuto object.
 * Author: Vignan Uppugandla
 */
package adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

import exceptions.AutoException;
import util.FileIO;
import model.*;

public abstract class ProxyAutomobile {

	private Automobile auto;
	private static Fleet fleet = new Fleet();
	
	/*Default Constructor*/
	public ProxyAutomobile(){}
	
	/*Build an automobile from a file and add to fleet*/
	public void buildAuto(String fileName, String fileType){
		FileIO fio = new FileIO();
			if(fileType.equalsIgnoreCase("text")){
				try {
					auto = fio.readFile(fileName);
				} catch (AutoException e) {
					System.out.println(e.getFixMessage());
					String newFile = fix(e.getErrorCode());
					try {
						auto = fio.readFile(newFile);
					} catch (AutoException e1) {
						e1.printStackTrace();
					}	
				}
			}	
			else if(fileType.equalsIgnoreCase("properties")){
				try {
					auto = fio.parsePropertiesTxt(fileName);	
				}catch(AutoException e) {
					System.out.println(e.getFixMessage());
					String newFile = fix(e.getErrorCode());
					try {
						auto = fio.parsePropertiesTxt(newFile);
					} catch (AutoException e1) {
						e1.printStackTrace();
					}	
				}
			}
			fleet.addAutomobile(auto);
	}
	/*Call the parse properties method to load the received properties object*/
	public void parseProperties(Properties props){
		FileIO fio = new FileIO();
		auto = fio.parseProperties(props);
		fleet.addAutomobile(auto);
	}
	
	/*Returns Automobile object for the model*/
	public Automobile getAutomobile(String modelName){
		return fleet.getAutomobile(modelName);
	}
	
	/*update automobile base details*/
	public void updateAuto(String modelName, String newName, float newPrice) {
		fleet.updateAuto(modelName, newName, newPrice);
	}
	
	/*update option Set name*/
	public void updateOptionSetName(String modelName, String optionSetName, String newName){
		fleet.updateOptionSetName(modelName, optionSetName, newName);
	}
	
	/*update option name*/
	public void updateOptionName(String modelName, String optionSetName, String optionName, String newName){
		fleet.updateOptionName(modelName, optionSetName, optionName, newName);
	}
	
	/*update option price*/
	public void updateOptionPrice(String modelName, String optionName, String option, float newPrice){
		fleet.updateOptionPrice(modelName, optionName, option, newPrice);
	}
	
	/*update option name and price*/
	public void updateOption(String modelName, String optionSetName, String oldOption,String newOption, float newPrice){
		fleet.updateOption(modelName, optionSetName, oldOption,newOption, newPrice);
	}
	
	/*edit option name*/
	public void editOptionName(String modelName, String optionSetName, String optionName, String newName){
		fleet.updateOptionName(modelName, optionSetName, optionName, newName);
	}
	
	/*edit option price*/
	public void editOptionPrice(String modelName, String optionName, String option, float newPrice){
		fleet.updateOptionPrice(modelName, optionName, option, newPrice);
	}
	
	/*delete automobile*/
	public void deleteAuto(String modelName) {
		fleet.deleteAutomobile(modelName);
	}
	
	/*delete option Set*/
	public void deleteOptionSet(String model, String optionSet) {
		fleet.deleteOptionSet(model, optionSet);
	}
	
	/*delete option*/
	public void deleteOption(String model, String optionSet, String option) {
		fleet.deleteOption(model, optionSet, option);
	}
	
	/*Print details of automobile for a model*/
		public void printAuto(String modelName){
			fleet.printAutoModel(modelName);
		}
		/*Method to send the details of the model to client*/
		public void getModel(String model, Socket sock){
			fleet.getModel(model, sock);
		}
		/*Method to send the details of the all model to client*/
		public String getAllModels(){
			return fleet.getModels();
		}
		
	/*Print details of all the automobiles in fleet*/
		public void printAllAuto(){
			fleet.printAllAutoModels();
		}
	/*set user choice*/
		public void setChoice(String modelName, String optionSetName, String optionName){
			fleet.setChoice(modelName, optionSetName, optionName);
		}
	/*get total price of model for the chosen options by user */
		public void totalModelPrice(String modelName){
			fleet.totalModelPrice(modelName);
		}
		
		/*Serialize the fleet*/
		public void serializeAuto(){
			fleet.serializeAutoHash();
		}
		
		/*DeSerialize the fleet*/
		public void deserializeAuto(){
			fleet.deSerializeAutoHash();
		}
		
		/*Fix method for file not found exception*/
		public String fix(int errorCode) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			if(errorCode == 601351) {
					System.out.print("Please input the filename:");
					try {
						return br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return null;	
		}
		
}
