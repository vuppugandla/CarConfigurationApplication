/*
 * This util class is for getting the connection detail loaded from db properties class and getting the connection details
 * Author: Vignan Uppugandla
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection conn = null;
	
	//method to establish connection
	public static Connection getConnection() throws  SQLException {
		
		if(conn == null ||(conn != null && conn.isClosed())) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DBProperties.DB_CONN_URL , DBProperties.DB_USERNAME, DBProperties.DB_PASSWORD);
			} catch (ClassNotFoundException e) {
				System.out.println("test");
				e.printStackTrace();
			}
		}
		return conn;
	}
	
}
