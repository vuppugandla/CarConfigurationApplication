/*
 * Fleet class which stores the details of all Automobiles in linkedHashMap
 * Accessed by ProxyAutomobile class in adapter package to implement any operations on Automobile, OperationSet or Options
 * Author: Vignan Uppugandla
 */
package model;

import java.util.Iterator;
import java.util.LinkedHashMap;

import util.FileIO;

public class Fleet {

	private Automobile auto;
	/*LinkedHashMap with key as model name and value as Automobile object for the model name*/
	private LinkedHashMap<String, Automobile> autoHash = new LinkedHashMap<String, Automobile>();
	
	/*Default Constructor*/
	public Fleet(){}
	
	/*Method to add the automobile model name and object pair to linkedhashMap*/
	public void addAutomobile(Automobile a)
	{
			autoHash.put(a.getModel(), a);
	}
	
	/*Method which return Automobile object for the model*/
	public Automobile getAutomobile(String model)
	{
		return autoHash.get(model);
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
	
	/*Print details of all the automobiles*/
	public void printAllAutoModels(){
		Iterator<Automobile> it = autoHash.values().iterator();
		while(it.hasNext())
		{
			it.next().printAutoDetails();
			System.out.println("\n");
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
