/*
 * This class interacts with the database to create/manage data at option set level on the database
 * This class extends BaseImpl class to get connection, execute statements and close the result sets
 * Author: Vignan Uppugandla
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionSetImpl extends BaseImpl {
	
	/*Method to insert new Option Set details to database*/
	public int insertNewOptionsSet(int auto_id, String name) {
		int opSetId = 0;
		try(Connection conn = super.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_NEW_OPTION_SET, Statement.RETURN_GENERATED_KEYS)){
			stmt.setInt(1, auto_id);
			stmt.setString(2, name);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
			
			try (ResultSet keys = stmt.getGeneratedKeys()) {
	            if (keys.next()) {
	            	opSetId = keys.getInt(1);
	            }
	            else {
	                throw new SQLException("option set not created -- ID's could not be found");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opSetId;
	}
	
	/*Method to update option set details in database*/
	public void updateOptionSet(String model, String oldName, String newName) {
		
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_OPTION_SET)) {
			stmt.setString(1, newName);
			stmt.setString(2, model);
			stmt.setString(3, oldName);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
			
		} catch (SQLException e) {
			System.out.println(e);
		} 
	}
	
	/*Method to delete option set details from database*/
	public void deleteOptionSet(String model, String optionSetName) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.DELETE_OPTION_SET);) {
			stmt.setString(1, model);
			stmt.setString(2, optionSetName);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/*Method to delete All option set under a model from database*/
	public void deleteAllOptionSet(String model) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.DELETE_OPTION_SETS);) {
			stmt.setString(1, model);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
