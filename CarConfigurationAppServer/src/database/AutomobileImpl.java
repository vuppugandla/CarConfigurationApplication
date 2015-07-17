/*
 * This class interacts with the database to create/manage data at automobile level on the database
 * This class extends BaseImpl class to get connection, execute statements and close the result sets
 * Author: Vignan Uppugandla
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Automobile;
import model.OptionSet;
import model.OptionSet.Option;

public class AutomobileImpl extends BaseImpl{
	
	/*Method to insert new automobile details to database*/
	public void insertNewAuto(Automobile auto) {
		try(Connection conn = super.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_NEW_AUTO, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, auto.getMake());
			stmt.setString(2, auto.getModel());
			stmt.setFloat(3, auto.getBasePrice());
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
			
			try(ResultSet keys = stmt.getGeneratedKeys()){
				if(keys.next()) {
					int auto_id = keys.getInt(1);
					
					for(OptionSet set : auto.getOptionSet()) {
						OptionSetImpl setImpl = new OptionSetImpl();
						int optionSetId = setImpl.insertNewOptionsSet(auto_id, set.getOptionSetName());
					
						for(Option option : set.getOptionList()) {
							OptionsImpl optionImpl = new OptionsImpl();
							optionImpl.insertNewOption(optionSetId, option.getOptionName(), option.getOptionPrice());
						}	
						
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Method to update automobile details in database*/
	public void updateAuto(String make, String oldModel, String newModel, double price) {
		try (Connection conn = super.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.UPDATE_AUTO)) {
			stmt.setString(1, newModel);
			stmt.setDouble(2, price);
			stmt.setString(3, make);
			stmt.setString(4, oldModel);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);		
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}
	}
	
	/*Method to delete automobile from database*/
	public void deleteAuto(String make, String model) {
		try (Connection conn = super.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.DELETE_AUTO);) {
			stmt.setString(1, make);
			stmt.setString(2, model);
			
			//Printing the execution query details and number of rows operation
			super.executeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
