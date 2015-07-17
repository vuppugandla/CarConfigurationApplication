/*
 * This class implements methods related to get connection, execute statements and close the result sets
 * Extended by AutomobileImpl, OptionSetImpl, OptionsImpl to perform the above operations
 * Author: Vignan Uppugandla
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseImpl {
	/*Method to close the result set after operation*/
	protected void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error when closing ResultSet "+ e);
			}
		}
	}

	/*Method to get the connection details to connect to My SQL db*/
	protected Connection getConnection() throws SQLException {
        return DBUtils.getConnection();
	}

	/*Method to execute the SQL stmnt*/
	protected void executeStatement(PreparedStatement stmt) throws SQLException {
		System.out.println("Executing query : " + stmt);
		int rowUpdated = stmt.executeUpdate();
		System.out.println(rowUpdated + " row(s) updated");
	}
}
