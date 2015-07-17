/*
 * This is an interface which has methods for building updating automobile details (option set name and option price)
 * Author: Vignan Uppugandla
 */
package adapter;

public interface UpdateAuto {

	/* Method declaration for updating  Automobile option set name*/
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	
	/* Method declaration for updating  Automobile option name under option set*/
	public void updateOptionName(String modelName, String optionSetName, String optionName, String newName);
	
	/* Method declaration for updating  Automobile option price under option set*/
	public void updateOptionPrice(String modelName, String optionName, String option, float newPrice);
	
	/* Method declaration for updating  Automobile option name and price under option set*/
	public void updateOption(String modelName, String optionSetName, String oldOption, String newOption, float newPrice);
	
}
