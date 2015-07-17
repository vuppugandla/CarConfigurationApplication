/*
 * This is an interface which has methods for editing automobile details linked with the thread created for that purpose
 * Referred in the EditOptions class under Scale package
 * Author: Vignan Uppugandla
 */
package scale;

public interface IEditOptions {
	
	/* Method declaration for editing the option name under a option set*/
	public void editOptionName(String modelName, String optionSet,String oldName, String newName);
	
	/* Method declaration for editing the option price under a option set*/
	public void editOptionPrice(String modelName, String optionSet, String option, float newPrice);
}
