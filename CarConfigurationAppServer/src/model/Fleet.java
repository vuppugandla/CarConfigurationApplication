/*
 * Fleet class which stores the details of all Automobiles in linkedHashMap
 * Accessed by ProxyAutomobile class in adapter package to implement any operations on Automobile, OperationSet or Options
 * Author: Vignan Uppugandla
 */
package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedHashMap;

import database.AutomobileImpl;
import database.OptionSetImpl;
import database.OptionsImpl;
import util.FileIO;

public class Fleet {

	private Automobile auto;
	/*LinkedHashMap with key as model name and value as Automobile object for the model name*/
	private LinkedHashMap<String, Automobile> autoHash = new LinkedHashMap<String, Automobile>();
	AutomobileImpl autoImpl = new AutomobileImpl();
	
	/*Default Constructor*/
	public Fleet(){}
	
	/*Method to add the automobile model name and object pair to linkedhashMap*/
	public void addAutomobile(Automobile a)
	{
		autoHash.put(a.getModel(), a);
		/*adding the new automobile object to database*/
		autoImpl.insertNewAuto(a);
	}
	
	/*Method which return Automobile object for the model*/
	public Automobile getAutomobile(String model)
	{
		return autoHash.get(model);
	}
	
	/*Update model name and price of an automobile for that make*/
	public void updateAuto(String oldModelName, String newModelName, float price) {
		auto = autoHash.remove(oldModelName);
		autoHash.put(newModelName, auto);
		auto.setModel(newModelName);
		auto.setBasePrice(price);
		autoImpl.updateAuto(auto.getMake(), oldModelName, newModelName, price);
	}
	
	/*update option Set name*/
	public void updateOptionSetName(String modelName, String optionSetName, String newName){
		auto = autoHash.get(modelName);
		auto.updateOptionSet(optionSetName, newName);
	}
	
	/*update option name*/
	public void updateOptionName(String modelName, String optionSetName, String optionName, String newName){
		auto = autoHash.get(modelName);
		auto.updateOption(optionSetName, optionName, newName);
	}
	
	/*update option price*/
	public void updateOptionPrice(String modelName, String optionName, String option, float newPrice){
		auto = autoHash.get(modelName);
		auto.updateOption(optionName, option, newPrice);
	}
	
	/*update option name and price*/
	public void updateOption(String modelName, String optionSetName, String oldOption,String newOption, float newPrice){
		auto = autoHash.get(modelName);
		auto.updateOption(optionSetName, oldOption,newOption, newPrice);
	}
	
	/*delete automobile*/
	public void deleteAutomobile(String modelName) {
		//deleting from database
		auto = autoHash.get(modelName);
		AutomobileImpl autoImpl = new AutomobileImpl();
		OptionSetImpl setImpl = new OptionSetImpl();
		OptionsImpl optionImpl = new OptionsImpl();
		
		for(OptionSet os : auto.getOptionSet()) {
			optionImpl.deleteAllOptions(modelName, os.getOptionSetName());	
		}
		setImpl.deleteAllOptionSet(modelName);	
		autoImpl.deleteAuto(autoHash.get(modelName).getMake(), modelName);
		autoHash.remove(modelName);
	}
	
	/*delete option Set*/
	public void deleteOptionSet(String model, String optionSet) {
		auto = autoHash.get(model);
		auto.deleteOptionSet(optionSet);
	}
	
	/*delete option*/
	public void deleteOption(String model, String optionSet, String option) {
		auto = autoHash.get(model);
		auto.deleteOption(optionSet, option);
	}
	
	/*set user choice*/
	public void setChoice(String modelName, String optionSetName, String optionName){
		auto = autoHash.get(modelName);
		auto.setOptionChoice(optionSetName, optionName);
	}
// get total price of model with user choice
	public void totalModelPrice(String modelName){
		auto = autoHash.get(modelName);
		System.out.println("Total Price of the model \""+modelName+"\" for selected choices: "+auto.getTotalPrice());
	}
	
	/*Print details of automobile for a model*/
	public void printAutoModel(String model){
		auto = autoHash.get(model);
		auto.printAutoDetails();
	}
	
	/*Get the automobile object for the model and pass the object to client*/
	public void getModel(String modelName, Socket sock){
		Automobile auto = autoHash.get(modelName);
			try {
				ObjectOutputStream outObj = new ObjectOutputStream(sock.getOutputStream());
				outObj.writeObject(auto);
				outObj.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/*Print details of all the automobiles*/
	public void printAllAutoModels(){
		Iterator<Automobile> it = autoHash.values().iterator();
		while(it.hasNext())
		{
			it.next().printAutoDetails();
			System.out.println("\n");
		}
	}
	/*Print the Names of all the automobile models*/
	public String  getModels(){
		if(autoHash.size()!=0){
			Iterator<Automobile> it = autoHash.values().iterator();
			StringBuffer sb = new StringBuffer();
			Automobile auto = null;
			auto = it.next();
			sb.append(auto.getMake());
			sb.append(" ");
//			sb.append("Model: ");
			sb.append(auto.getModel());
			while(it.hasNext())
			{
				auto = it.next();
				sb.append(",");
				sb.append(auto.getMake());
				sb.append(" ");
//				sb.append("Model: ");
				sb.append(auto.getModel());
			}
			return sb.toString();
		}
		else{
			return "Empty HashSet";
		}
	}
	
	/*Serialize the automobile linkedHashMap and write it to file*/
	public void serializeAutoHash(){
		FileIO.serializeAuto(autoHash);
	}
	
	/*DeSerialize the automobile linkedHashMap and assign it to automobile linkedhashMap*/
	public void deSerializeAutoHash(){
		autoHash = FileIO.deserializeAuto();
	}	
	
}
