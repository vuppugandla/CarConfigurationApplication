/*
 * This is an interface which has methods for deleting  Automobile, option set, option details
 * Author: Vignan Uppugandla
 */
package adapter;

public interface DeleteAuto {

	/*delete Automobile declaration*/
	public void deleteAuto(String modelName);
	
	/*delete option Set declaration*/
	public void deleteOptionSet(String modelName, String optionSet);
	
	/*delete options declaration*/
	public void deleteOption(String model, String optionSet, String option);
}
