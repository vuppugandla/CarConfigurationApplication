/*
 * This is an interface which has methods for building Automobile(reading file) and printing the automobile details 
 * Author: Vignan Uppugandla
 */
package adapter;

public interface CreateAuto {
	
	/* Method declaration for building Automobile from a file*/
	public void buildAuto(String fileName, String fileType);
	
	/* Method declaration for printing details of an Automobile from model name*/
	public void printAuto(String modelName);
	
	/* Method declaration for printing details of all Automobiles */
	public void printAllAuto();

}
