/*
 * This class implements a static block which collects SQL queries from a properties file and load them into static variables
 * Author: Vignan Uppugandla
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SQL{
	public static String INSERT_NEW_AUTO;
	public static String INSERT_NEW_OPTION_SET;
	public static String INSERT_NEW_OPTION;
	public static String UPDATE_AUTO;
	public static String UPDATE_OPTION_SET;
	public static String UPDATE_OPTION;
	public static String DELETE_AUTO;
	public static String DELETE_OPTION_SET;
	public static String DELETE_OPTION_SETS;
	public static String DELETE_ALL_OPTIONS;
	public static String DELETE_OPTION;
	
	//static block to load details from properties file
	static {
		try(FileInputStream input = new FileInputStream("resources/sql.properties");){
			if(input != null) {
				Properties prop = new Properties();
				prop.load(input);
				INSERT_NEW_AUTO = prop.getProperty("CREATE_NEW_AUTO");
				INSERT_NEW_OPTION_SET = prop.getProperty("CREATE_NEW_OPTION_SET");
				INSERT_NEW_OPTION = prop.getProperty("CREATE_NEW_OPTION");
				UPDATE_AUTO = prop.getProperty("UPDATE_AUTO");
				UPDATE_OPTION_SET = prop.getProperty("UPDATE_OPTION_SET");
				UPDATE_OPTION = prop.getProperty("UPDATE_OPTION");
				DELETE_AUTO = prop.getProperty("DELETE_AUTO");
				DELETE_OPTION_SET = prop.getProperty("DELETE_OPTION_SET");
				DELETE_OPTION_SETS = prop.getProperty("DELETE_OPTION_SETS");
				DELETE_ALL_OPTIONS = prop.getProperty("DELETE_ALL_OPTIONS");
				DELETE_OPTION = prop.getProperty("DELETE_OPTION");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
