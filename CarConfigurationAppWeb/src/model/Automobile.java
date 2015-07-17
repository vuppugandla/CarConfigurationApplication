/*
 * This is the automobile class which stores the details of the automobile such as model, make and base price. 
 * It has option set array list of objects which has details of the option sets
 * Author: Vignan Uppugandla
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;;

public class Automobile implements Serializable {

	private static final long serialVersionUID = 1L;
	private String model;
	private String make;
	private float basePrice;
	
	private ArrayList<OptionSet> optionSet = new ArrayList<OptionSet>();

	/*default constructor*/
	public Automobile() {}
	
	/*parameterized constructor*/
	public Automobile(String model, int basePrice, int optSetSize){
		synchronized(this){
			this.model = model;
			this.basePrice = basePrice;
			setOptionSet(optSetSize);
		}
	}
	
	/*getter and setter for Automobile make*/
	public String getMake(){ return make;}
	public void setMake(String make){this.make=make;}
		
	/*getter & setter for Automobile Name*/
	public String getModel(){ return model;}
	public void setModel(String model){this.model = model;}
	
	/*getter & setter for Automobile Base Price*/
	public float getBasePrice(){return basePrice;}
	public void setBasePrice(int basePrice){this.basePrice = basePrice;}
	
	/*Creating OptionSet objects and adding them to Array list*/
	public synchronized void setOptionSet(int optSetSize){
		for (int i=0; i<optSetSize; i++){
			OptionSet optSet = new OptionSet();
			optionSet.add(optSet);
		}
	}
	
	/*Getter for Option Set*/
	public synchronized ArrayList<OptionSet> getOptionSet(){
		return optionSet;
	}
	
	public synchronized void addOptionSet(int index, String optionSetName){
		OptionSet optSet = new OptionSet();
		optionSet.add(optSet);
		optionSet.get(index).setOptionSetName(optionSetName);
	}
	
	/*Set and get the name of the option set for the Automobile using position Index*/
	public synchronized void setOptSetName(int index, String optSetName){
		optionSet.get(index).setOptionSetName(optSetName);
	}
	public synchronized String getOptSetName(int index){
		return optionSet.get(index).getOptionSetName();
	}
	
	
	/*Creating options under the option set*/
	public synchronized void setOption(int index, int size){
		optionSet.get(index).setOption(size);
	}
	/*getting the option list  object*/
	public synchronized ArrayList<Option> getOptionList(int index){
		return optionSet.get(index).getOptionList();
	}
	
	/*setter and getter of  the name of the option under the Option set*/
	public synchronized void setOptionName(int optSetIndex, int optIndex, String optName){
		optionSet.get(optSetIndex).getOption(optIndex).setOptionName(optName);	
	}
	public synchronized String getOptionName(int optSetIndex, int optIndex){
		return optionSet.get(optSetIndex).getOption(optIndex).getOptionName();
	}
	
	/*setter and getter of  the price of the option under the option set*/
	public synchronized void setOptionPrice(int optSetIndex, int optIndex, float optPrice){
		optionSet.get(optSetIndex).getOption(optIndex).setOptionPrice(optPrice);	
	}
	public synchronized float getOptionPrice(int optSetIndex, int optIndex){
		return optionSet.get(optSetIndex).getOption(optIndex).getOptionPrice();
	}
	
	/*find Option Set through name*/
	public synchronized OptionSet findOptionSet(String optSetName){
		for (int i=0; i<optionSet.size() ; i++){
			if(optionSet.get(i).getOptionSetName().equals(optSetName))  return optionSet.get(i);
		}
		return null;
	}
	
	/*find Option through option set name and option name*/
	public synchronized Option findOption(String optSetName, String optName){
		for (int i=0; i<optionSet.size() ; i++){
			if(optionSet.get(i).getOptionSetName().equals(optSetName)){
				Option opt = optionSet.get(i).findOption(optName);
				if(opt != null) {return opt;}
			}
		}
		return null;
	}
	
	/*delete Option Set though name*/
	public synchronized void deleteOptionSet(String name){	
		for(int i=0;i<optionSet.size();i++){
			if(optionSet.get(i).getOptionSetName().equals(name)) {
				optionSet.remove(i);
				return;	
			}
		}
	}
		
	/*delete Option though option set name and option name*/
	public synchronized void deleteOption(String optSetName, String optName){
		for(int i=0; i<optionSet.size(); i++){
			if(optionSet.get(i).getOptionSetName().equals(optSetName)) {
				optionSet.get(i).deleteOption(optName);
				return;
			}
		}
	}
	
	/*Update Option Set name*/
	public synchronized void updateOptionSet(String oldOptSetName, String newOptSetName){
		try{
			OptionSet ops = findOptionSet(oldOptSetName);
			ops.setOptionSetName(newOptSetName);
		}catch(NullPointerException e){
			System.out.println("\nCannot find the model, optionset, option combination\n");
		}
	}
	
	/*Update Option Name*/
	public synchronized void updateOption(String optSetName, String oldOptName, String newOptName){
		try{
			Option ops = findOption(optSetName,oldOptName);
			ops.setOptionName(newOptName);
		}catch(NullPointerException e){
			System.out.println("\nCannot find the model, optionset, option combination\n");
		}
	}
	
	/*Update Option Price*/
	public synchronized void updateOption(String optSetName, String optName, float newPrice){
		try{
			Option ops = findOption(optSetName,optName);
			ops.setOptionPrice(newPrice);
		}catch(NullPointerException e){
			System.out.println("\nCannot find the model, optionset, option combination\n");
		}
	}
	
	/*getters and setters User option choice*/
	public synchronized String getOptionChoice(String setName){
		return findOptionSet(setName).getOptionChoice().getOptionName();
	}
	
	public synchronized int getOptionChoicePrice(String setName){
		return (int)findOptionSet(setName).getOptionChoice().getOptionPrice();
	}
	
	public synchronized void setOptionChoice(String setName, String optionName){
		findOptionSet(setName).setOptionChoice(optionName);
	}
	
	/* Total Price of the option set*/
	public synchronized float getTotalPrice(){
		float totalPrice = basePrice;
		for (int i=0; i<optionSet.size(); i++){
			totalPrice+=getOptionChoicePrice(optionSet.get(i).getOptionSetName());
		}
		return totalPrice;
	}
	
	/*print Automobile details*/
	public synchronized void printAutoDetails(){
		StringBuffer sb = new StringBuffer();
		sb.append("Make: ");
		sb.append(make);
		sb.append("\n");
		sb.append("ModelName: ");
		sb.append(model);
		sb.append("\n");
		sb.append("Base Price: ");
		sb.append(basePrice);
		System.out.println(sb);
		for(int i=0;i<optionSet.size();i++){
			optionSet.get(i).printOptionSetDetails();
		}
	}
	
}
