/*
 * This is the Option Set class which stores the details of the option sets for a automobile
 * It has option class which has details of the options
 * It has inner class Option which has options of option set and the price . 
 * Author: Vignan Uppugandla
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

	public class OptionSet implements Serializable{
	
		private static final long serialVersionUID = 1L;
		private String optionSetName;
		private ArrayList<Option> option = new ArrayList<Option>();
		private Option choice;
		
		/*default constructor*/
		protected OptionSet(){
		}
		
		/*parameterized constructor*/
		protected OptionSet(String optionSetName, int optSize){
			this.optionSetName = optionSetName;
			setOption(optSize);
		}
		
		/*getter and setter for Option Set Name*/
		public String getOptionSetName(){ return optionSetName; }
		protected void setOptionSetName(String optionSetName){ this.optionSetName = optionSetName; }
		
		/*getter for Option object*/
		protected Option getOption(int index){ return option.get(index);}
		
		/*creating Option objects and adding them to array list*/
		protected void setOption(int optSize){ 
			for (int i =0; i<optSize; i++){
				Option opt = new Option(); //instantiating the option object
				option.add(opt);
			}
		}

		/*getting the option list  object*/
		public ArrayList<Option> getOptionList(){
			return option;
		}
				
			/*Option class: Inner Class for Option Set*/
			/*--------------------Inner class starts here-------------------*/
			public class Option implements Serializable{

				private static final long serialVersionUID = 1L;
				private String optionName;
				private float price;
				
				/*default constructor*/
				protected Option(){}
				
				/*parameterized constructor*/
				protected Option(String optionName, float price){
					this.optionName = optionName;
					this.price = price;
				}
				
				/*getter and setter for Option Name*/
				public String getOptionName(){ return optionName; }
				protected void setOptionName(String optionName){ this.optionName = optionName; }
				
				/*getter and setter for Option Price*/
				public float getOptionPrice(){ return price; }
				protected void setOptionPrice(float price){ this.price = price; }
				
				/*Print option details*/
				protected void printOptionDetails(){
					StringBuffer sb = new StringBuffer();
					sb.append("\t");
					sb.append(optionName);
					sb.append("\t");
					sb.append(price);
					System.out.println(sb);

				}
			}
			/*--------------------Inner class ends here-------------------*/
			
		/*find Option with name*/
		protected Option findOption(String name){
			for (int i=0; i<option.size(); i++){
				if(option.get(i).getOptionName().equals(name)) return option.get(i);
			}
			return null;
		}
		
		/*delete Option with name*/
		protected void deleteOption(String name){
			for(int i=0; i<option.size(); i++){
				if(option.get(i).getOptionName().equals(name)){
					option.remove(i);
					return;
				}
			}
		}
		
		/* print option set details*/
		protected void printOptionSetDetails(){
			StringBuffer sb = new StringBuffer();
			sb.append(optionSetName+": ");
			System.out.println(sb);
			for(int i=0;i<option.size();i++){
				option.get(i).printOptionDetails();
			}
		}

		/*getter method for user option choice*/
		protected Option getOptionChoice() {
			return choice;
		}
		
		/*setter method for user option choice for a option name*/
		protected void setOptionChoice(String OptionName){
			choice = new Option();
			choice.setOptionName(findOption(OptionName).getOptionName());
			choice.setOptionPrice(findOption(OptionName).getOptionPrice());
		}
	}