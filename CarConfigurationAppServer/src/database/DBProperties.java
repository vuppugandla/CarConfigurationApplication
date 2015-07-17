/*
 * This class implements methods to get the details of the database - connection url, username, password
 * Author: Vignan Uppugandla
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBProperties {
	public static String DB_CONN_URL;
	public static String DB_USERNAME;
	public static String DB_PASSWORD;
	
	//static block to get the database connection details from properties file
static {
	try(FileInputStream input = new FileInputStream("resources/dbconfig.properties");){
		if(input != null) {
			Properties prop = new Properties();
			prop.load(input);
			DB_CONN_URL = prop.getProperty("dbConnUrl");
			DB_USERNAME = prop.getProperty("dbUsername");
			DB_PASSWORD = prop.getProperty("dbPassword");
		}
	} catch (IOException e) {
		e.printStackTrace();
	} 
	finally {
		initializeWithDefaultValuesIfNeeded();
	}
}

//method to initialize default values incase unable to get details from properties file
private static void initializeWithDefaultValuesIfNeeded() {
	if (DB_CONN_URL == null) {
		DB_CONN_URL = "jdbc:mysql://localhost:3306/car_config";
	}

	if (DB_USERNAME == null) {
		DB_USERNAME = "carconfig_user";
	}

	if (DB_PASSWORD == null) {
		DB_PASSWORD = "carconfig_password";
	}
}
}
