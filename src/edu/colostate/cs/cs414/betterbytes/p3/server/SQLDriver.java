
package edu.colostate.cs.cs414.betterbytes.p3.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Player;
import edu.colostate.cs.cs414.betterbytes.p3.utilities.Serializer;

public class SQLDriver {

	private Connection database;
	// must be changed for whatever machine it is being run on 3306 is the port
	// server
	private String url = "jdbc:mysql://faure.cs.colostate.edu/betterbytes";	
	// Credentials for the database to log into
	private String user = "ctunnell";
	private String pass = "830336263";

	private static final SQLDriver instance = new SQLDriver();

	public static SQLDriver getInstance() {
		return instance;
	}

	private SQLDriver() {
		openConnection(url, user, pass);
	}

	private void openConnection(String url, String User, String pass) {
		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
			database = DriverManager.getConnection(url, User, pass);
		} catch (Exception e) {
			System.err.printf("Unable to Open Connection: ");
			System.err.println(e.getMessage());
		}
	}
	
	public void setAccount(String username,String password,Account acc)
	{		
		String query  = "UPDATE betterbytes.users SET `username`=\"" + username + "\", "
						+ "`password`=\"" + password + "\",`account`=\""
						+ new String(Serializer.serialize(acc)) + "\" ";
		
			   query += "WHERE `username`=\"" + username + "\" AND `password`=\"" + password+ "\";";
		runQuery(query);		
	}
	
	public void setAccount(Account acc)
	{		
		String query  = "UPDATE betterbytes.users SET `username`=\"" + acc.getUsername() + "\", "
						+ "`password`=\"" + acc.getPassword() + "\",`account`=\""
						+ new String(Serializer.serialize(acc)) + "\" ";
		
			   query += "WHERE `username`=\"" + acc.getUsername() + "\" AND `password`=\"" + acc.getPassword() + "\";";
		runQuery(query);		
	}
	
	// Gets the account from a given username and password
	// Returns the Account object
	public Account getAccount(String username,String password)
	{		
		String query  = "Select `account` FROM betterbytes.users";
			   query += "WHERE `username`=\"" + username + "\" AND `password`=\"" + password+ "\";";
			   
		String [] results = loginQuery(username, password);		
		Account received = Serializer.deserializeAccount(results[2].getBytes());
			  
		return received;		
	}

	// Checks if a user already exists
	// if they do not then we add them to the database
	// needs work to determine if name already exists.
	// will work with GUI team to determine return values
	public boolean addUser(String username, String password) {
		String result[] = loginQuery(username, password);
		if (result[0] != null) {
			return false;
		} else {
			String query = "INSERT INTO betterbytes.users (username, password) VALUES ('" + username + "' , '" + password
					+ "' );";
			runQuery(query);
			return true;
		}
	}// End Methods
	
	
	
	public void addGame(Player player1, Player player2, String state)
	{
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		addGame(p1,p2,state);	
	}
	
	
	public void addGame(Account player1, Account player2, String state)
	{
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		addGame(p1,p2,state);
	}
	//TODO
	// state Must be changed to a Game
	// String for testing purposes
	private void addGame (String p1, String p2, String state)
	{		
		String query = "INSERT INTO betterbytes.game (player1,player2,state) "
				+ "VALUE('" + p1 + "' , '" + p2 + "' , '" + state + "');";
		runQuery(query);		
	}
	
	public boolean deleteGame(Player player1, Player player2)
	{
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		return deleteGame(p1,p2);	
	}
	
	
	public boolean deleteGame(Account player1, Account player2)
	{
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		return deleteGame(p1,p2);
	}
	
	private boolean deleteGame(String p1, String p2)
	{
		String query = "DELETE FROM betterbytes.game WHERE `player1` LIKE '" + p1 + "' and `player2` LIKE '"
				+ p2 + "';";
		runQuery(query);
		
		query = "DELETE FROM betterbytes.game WHERE `player1` LIKE '" + p2 + "' and `player2` LIKE '"
				+ p1 + "';";
		runQuery(query);
		
		String result = getGame(p1,p2);
		
		if(result == null || result == "null")
			return true;
		return false;
	}
	
	public String getGame(Player player1,Player player2)
	{
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		String result = getGame(p1,p2);
		
		return result;
	}
	
	public String getGame(Account player1,Account player2)
	{
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		String result = getGame(p1,p2);
		
		return result;
	}
	
	
	private String [] queryGame(String query)
	{
		String[] results = new String[3];
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
					ResultSetMetaData metaData = result.getMetaData();
					int colCount = metaData.getColumnCount();
					while (result.next()) {
						results[0] = result.getString(1);
						results[1] = result.getString(2);
						results[2] = result.getString(3);
					}
				} finally {
					result.close();
				}
			} finally {
				st.close();
			}
		} catch (Exception e) {
			System.err.printf("Exception in getGame(): ");
			System.err.println(e.getMessage());
		}		
		return results;		
	}
	
	
	//Checks for the player being in either player1 or player2 in the database
	private String getGame(String p1,String p2)
	{		
		String query = "SELECT * FROM betterbytes.game WHERE `player1` LIKE \"" + p1 +
				       "\" and `player2` LIKE \"" + p2 + "\";";			
		String [] results = queryGame(query);
	
		if(results[2] == null || results[2].equals("null"))
		{
			query = "SELECT * FROM betterbytes.game WHERE `player1` LIKE \"" + p2 +
				       "\" and `player2` LIKE \"" + p1 + "\";";			
			results = queryGame(query);			
		}
		return results[2];
	}


	public boolean deleteUser(String username, String password) {
		String query = "DELETE FROM betterbytes.users WHERE `username` LIKE '" + username + "' and `password` LIKE '"
				+ password + "';";		
		runQuery(query);

		String result[] = loginQuery(username, password);

		if (result[0] == null)
			return true;
		else
			return false;
	}// End Method

	private void runQuery(String query) {
		try {
			Statement st = database.createStatement();
			try {
				st.executeUpdate(query);
			} finally {
				st.close();
			}
		} catch (Exception e) {
			System.err.printf("Exception: ");
			System.err.println(e.getMessage());
		}
	}

	
	public boolean checkLogin(String username, String password) {
		String result[] = loginQuery(username, password);
		if(result[1] == null || !(result[1].equals(password)))
			return false;		
		else if (result[1].equals(password))
			return true;
		
		return false;

	}// End method

	public String[] loginQuery(String username, String password) {
		String[] results = new String[3];
		String query = "SELECT * FROM betterbytes.users WHERE `username` LIKE '" + username + "' AND `password` LIKE '" + password + "';";
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
					ResultSetMetaData metaData = result.getMetaData();
					int colCount = metaData.getColumnCount();
					while (result.next()) {
						results[0] = result.getString(1);
						results[1] = result.getString(2);
						results[2] = result.getString(3);
						
					}
				} finally {
					result.close();
				}
			} finally {
				st.close();
			}
		} catch (Exception e) {
			System.err.printf("Exception: ");
			System.err.println(e.getMessage());
		}
		return results;
	}

	/*
	 * SQLDriver sql = SQLDriver.getInstance(); String[] results =
	 * sql.loginQuery("ctunnell@rams.colostate.edu","TestPassword");
	 * 
	 * if(sql.checkLogin("ctunnell@rams.colostate.edu", "TestPassword")) {
	 * System.out.println("Login Verified"); } else {
	 * System.out.println("Login Denied"); }
	 */

}// End Class
