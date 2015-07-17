/*
 * This is the driver class for testing the database implementation
 * Author: Vignan Uppugandla
 */
package driver;

import adapter.BuildAuto;

public class DBTestDriver {

	public static void main(String[] args) {
		
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("PriusProperties.txt", "properties");//loading prius automobile details from properties file
		auto.buildAuto("TeslaSProperties.txt","properties");//loading tesla automobile details from properties file
		auto.buildAuto("Focus Wagon ZTW.txt","text");//loading ford automobile details from text file
		auto.printAllAuto();
		
		auto.updateAuto("Prius", "Prius II", 20123);//updating prius model name details
		
		auto.updateOptionSetName("Model S", "Side Impact Air Bags", "Air Bags");//updating option set name
		auto.deleteOptionSet("Model S", "Power Moonroof");//deleting option set
		
		auto.updateOption("Prius II", "Color", "Cloud 9 White Clearcoat", "Clear White", 10);//updating option name and price
		auto.deleteOption("Prius II", "Brakes/Traction Control", "ABS");//deleting option
		
		auto.deleteAuto("Focus Wagon ZTW");//deleting ford automobile
		auto.printAllAuto();//final available automobile details
	}

}
