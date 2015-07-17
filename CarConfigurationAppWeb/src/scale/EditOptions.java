/*
 * EditOptions Class extends Thread class
 * Takes care that threads do not interfere with each other and are in synchronization
 * Author: Vignan Uppugandla
 */

package scale;

import adapter.BuildAuto;

public class EditOptions extends Thread{
	
	private String threadName;
	private String modelName;
	private String optionSet;
	private String option;
	private String optionNewName;
	private float priceNew;
	private int method;
	
	/*Default Constructor*/
	public EditOptions(){}
	
	/*Parameterized constructor
	 * Accepts and sets 
	 * operation method number
	 * Interface Object
	 * Model, Option Set, Option Old Name and Option New Name*/
	public EditOptions(String threadName, int method, String modelName, String optionSet, String option, String optionNewName)
	{
		this.threadName = threadName;
		this.method = method;
		this.modelName = modelName;
		this.optionSet = optionSet;
		this.option = option;
		this.optionNewName = optionNewName;
	}
	
	/*Parameterized constructor
	 * Accepts and sets 
	 * operation method number
	 * Interface Object
	 * Model, Option Set, Option Name and Option Price*/
	public EditOptions(String threadName, int method, String modelName, String optionSet, String option, float newPrice)
	{
		this.threadName = threadName;
		this.method = method;
		this.modelName = modelName;
		this.optionSet = optionSet;
		this.option = option;
		this.priceNew = newPrice;
	}

	/*Method which sends the executing method to sleep for 3sec*/
	private void waitTime(){
		try{
			Thread.currentThread();
			Thread.sleep((long)(3000*Math.random()	));
		} catch(InterruptedException e){
			System.out.println("Interrupted");
		}
	}
	
	/*Run method which starts executing invoking the thread Start method
	 * Synchronizes the operation on the autoInterf object such that there is no interference between multiple threads
	 * Executes respective method based on the operation method Number set when constructor is invoked*/
	public void run()
	{		
		BuildAuto ba = new BuildAuto();
			switch(method){
				case 0: 
					waitTime();
					System.out.println("Running Thread:" + this.threadName + " \nSetting old OptionName:" + this.optionSet+ " to new OptionName:" +this.optionNewName);
					ba.editOptionName(modelName, optionSet, option, optionNewName);
					break;
				case 1: 
					waitTime();
					System.out.println("Running Thread:" + this.threadName + " \nSetting Option:" + this.option+ " price to new OptionPrice:" +this.priceNew);
					ba.editOptionPrice(modelName, optionSet, option, priceNew);
					break;
			}
	}			
}