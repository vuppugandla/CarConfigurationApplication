/*
 * This class interacts with the database to create/manage data at options level on the database
 * This class extends BaseImpl class to get connection, execute statements and close the result sets
 * Author: Vignan Uppugandla
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionsImpl extends BaseImpl{
	
	/*Method to insert new options details to database*/
	public void insertNewOption(int options_set_id, String name, float price) {
		try(Connection conn = super.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_NEW_OPTION, Statement.RETURN_GENERATED_KEYS)){
			stmt.setInt(1, options_set_id);
			stmt.setString(2, name);
			stmt.setFloat(3, price);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
			
			try (ResultSet keys = stmt.getGeneratedKeys()) {
	            if (keys.next()) {
	            	keys.getInt(1);
	            }
	            else {
	                throw new SQLException("option set not created -- ID's could not be found");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Method to update option details in database*/
	public void updateOption(String model, String optionSetName, String oldName, String newName, double price) {
		
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_OPTION)) {
			stmt.setString(1, newName);
			stmt.setDouble(2, price);
			stmt.setString(3, model);
			stmt.setString(4, optionSetName);
			stmt.setString(5, oldName);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}
	}
	
	/*Method to delete option from database*/
	public void deleteOption(String model, String optionSetName, String name) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.DELETE_OPTION);) {
			stmt.setString(1, model);
			stmt.setString(2, optionSetName);
			stmt.setString(3, name);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/*Method to delete All option of a option set under a model from database*/
	public void deleteAllOptions(String model, String optionSetName) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.DELETE_ALL_OPTIONS);) {
			stmt.setString(1, model);
			stmt.setString(2, optionSetName);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
