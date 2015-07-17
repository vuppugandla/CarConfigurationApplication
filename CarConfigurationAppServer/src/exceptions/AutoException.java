/*
 * Exception class implemented to handle and fix exceptions
 * Author: Vignan Uppugandla
 */
package exceptions;

public class AutoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;
	private int errorCode;
	
	/*Default constructor*/
	public AutoException(){super();}
	
	/*Parameterized constructor which takes error Number*/
	public AutoException(int errNo){
		fixProblem(errNo);
	}
	/*Parameterized constructor which takes error message*/
	public AutoException(String errMsg){
		super(errMsg);
	}
	
	/*Method to fix problem and set the message for the problem
	 * Takes error Number and sets the appropriate message for that error*/
	public void fixProblem(int errno){
			switch(errno){
				case 601350: errorCode = 601350; message = "UnknownMake";  break;
				case 601351: errorCode = 601351; message = "File not found"; break;
				case 601352: errorCode = 601352; message = "UnknownOptionSet"; break;
				case 601353: errorCode = 601353; message = "UnknownModelName"; break;
				case 601354: errorCode = 601354; message = "0"; break;
				case 601355: errorCode = 601355; message = "UnknownOption"; break;
				case 601357: errorCode = 601357; message = "Option Set not defined"; break;
				case 601358: errorCode = 601358; message = "Options not defined for an option set"; break;
				case 601359: errorCode = 601359; message = "No data found in the file"; break;
				default: errorCode = 0; message = "Unknown Error Number"; break;
			}
	}
	
	/*getter method for the fixed error, returns message*/
	public String getFixMessage(){
		return message;
	}
	
	/*getter method for the error code, returns error code*/
	public int getErrorCode() {
		return errorCode;
	}

}
