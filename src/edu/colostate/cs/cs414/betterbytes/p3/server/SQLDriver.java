
package edu.colostate.cs.cs414.betterbytes.p3.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Player;
import edu.colostate.cs.cs414.betterbytes.p3.utilities.Serializer;

public class SQLDriver {
	

 // Code to add the cases needed for the testing to work
	public static void main (String [] args)
	{
		// setPlayersinDB();
		setGamesinDB();
	}

	
	private static void setPlayersinDB()
	{
		 SQLDriver sql = SQLDriver.getInstance();
		 Account testAcc1 = new Account("ctunnell", "TestPassword");
		 Account testAcc2 = new Account("Jhpokorski", "TestPassword2");
		 Account testAcc3 = new Account("ctunnell@rams.colostate.edu", "TestPassword");
		 boolean val1 = sql.addUser(testAcc1);
		 boolean val2 = sql.addUser(testAcc2);
		 boolean val3 = sql.addUser(testAcc3);		
	}
	
	private static void setGamesinDB()
	{
		SQLDriver sql = SQLDriver.getInstance();
		Account testAcc1 = sql.getAccount("ctunnell");
		Player p1 = new Player(testAcc1);
		Account testAcc2 =  sql.getAccount("Jhpokorski");
		Player p2 = new Player(testAcc2);
		Game g1 = new Game("0:0",p1,p2);	
		sql.addGame(p1, p2, g1);
	}
	
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

	public void setAccount(String username, String password, Account acc) {
		String query = "UPDATE betterbytes.users SET `username`=\"" + username + "\", " + "`password`=\"" + password
				+ "\",`account`=\"" + new String(Serializer.serialize(acc)) + "\" ";

		query += "WHERE `username`=\"" + username + "\" AND `password`=\"" + password + "\";";
		runQuery(query);
	}

	public void setAccount(Account acc) {
		String query = "UPDATE betterbytes.users SET `username`=\"" + acc.getUsername() + "\", " + "`password`=\""
				+ acc.getPassword() + "\",`account`=\"" + new String(Serializer.serialize(acc)) + "\" ";

		query += "WHERE `username`=\"" + acc.getUsername() + "\" AND `password`=\"" + acc.getPassword() + "\";";
		runQuery(query);
	}

	// Gets the account from a given username and password
	// Returns the Account object
	public Account getAccount(String username, String password) {
		String[] results = loginQuery(username, password);
		Account received = Serializer.deserializeAccount(results[2].getBytes());

		return received;
	}
	
	public Account getAccount(String username) {
		String query = "Select * FROM betterbytes.users";
		query += " WHERE `username`=\"" + username + "\";";
		String [] results = runQueryRes(query);		
		System.out.println(results[1]);
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
			String query = "INSERT INTO betterbytes.users (username, password) VALUES ('" + username + "' , '"
					+ password + "' );";
			runQuery(query);
			return true;
		}
	}// End Methods
	
	public boolean addUser(Account acc) {
		String result[] = loginQuery(acc.getUsername(), acc.getPassword());
		if (result[0] != null) {
			return false;
		} else {
			String query = "INSERT INTO betterbytes.users (username, password, account) VALUES ('" + acc.getUsername() + "' , '"
					+ acc.getPassword() + "' , '" +  new String(Serializer.serialize(acc)) + "' );";
			runQuery(query);
			return true;
		}
	}// End Methods

	public void addGame(Player player1, Player player2, Game state) {
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		addGame(p1, p2, state);
	}

	public void addGame(Account player1, Account player2, Game state) {
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		addGame(p1, p2, state);
	}

	void addGame(String p1, String p2, Game state) {
		String query = "INSERT INTO betterbytes.game (player1,player2,state) " + "VALUE('" + p1 + "' , '" + p2 + "' , '"
				+  new String(Serializer.serialize(state)) + "');";
		runQuery(query);
	}

	public boolean deleteGame(Player player1, Player player2) {
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		return deleteGame(p1, p2);
	}

	public boolean deleteGame(Account player1, Account player2) {
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		return deleteGame(p1, p2);
	}

	private boolean deleteGame(String p1, String p2) {
		String query = "DELETE FROM betterbytes.game WHERE `player1` LIKE '" + p1 + "' and `player2` LIKE '" + p2
				+ "';";
		runQuery(query);

		query = "DELETE FROM betterbytes.game WHERE `player1` LIKE '" + p2 + "' and `player2` LIKE '" + p1 + "';";
		runQuery(query);

		String result = getGame(p1, p2);

		if (result == null || result == "null")
			return true;
		return false;
	}

	public String getGame(Player player1, Player player2) {
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		String result = getGame(p1, p2);

		return result;
	}

	public String getGame(Account player1, Account player2) {
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		String result = getGame(p1, p2);

		return result;
	}

	private String[] queryGame(String query) {
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

	// Checks for the player being in either player1 or player2 in the database
	private String getGame(String p1, String p2) {
		String query = "SELECT * FROM betterbytes.game WHERE `player1` LIKE \"" + p1 + "\" and `player2` LIKE \"" + p2
				+ "\";";
		String[] results = queryGame(query);

		if (results[2] == null || results[2].equals("null")) {
			query = "SELECT * FROM betterbytes.game WHERE `player1` LIKE \"" + p2 + "\" and `player2` LIKE \"" + p1
					+ "\";";
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
	
	public boolean deleteUser(Account acc) {
		String query = "DELETE FROM betterbytes.users WHERE `username` LIKE '" + acc.getUsername() + "' and `password` LIKE '"
				+ acc.getPassword() + "';";
		runQuery(query);

		String result[] = loginQuery(acc.getUsername(),acc.getPassword());

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
	
	// Can be changed to Arraylist if needed to be more specific.
	private String [] runQueryRes(String query)
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
			System.err.printf("Exception in runQueryRes(): ");
			System.err.println(e.getMessage());
		}
		return results;
	}
	
	private List <String> getGamesQuery(String query)
	{
		List <String> results = new ArrayList <String>();	
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
					ResultSetMetaData metaData = result.getMetaData();
					int colCount = metaData.getColumnCount();
					while (result.next()) {
						results.add(result.getString(1));
					}
				} finally {
					result.close();
				}
			} finally {
				st.close();
			}
		} catch (Exception e) {
			System.err.printf("Exception in runQueryRes(): ");
			System.err.println(e.getMessage());
		}
		return results;
	}
	
	
	public boolean checkLogin(String username, String password) {
		String result[] = loginQuery(username, password);
		if (result[1] == null || !(result[1].equals(password)))
			return false;
		else if (result[1].equals(password))
			return true;

		return false;

	}// End method

	public String[] loginQuery(String username, String password) {
		String[] results = new String[3];
		String query = "SELECT * FROM betterbytes.users WHERE `username` LIKE '" + username + "' AND `password` LIKE '"
				+ password + "';";
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


	public List<Game> getGames(String username) {
		// TODO Same as above, just that I need every game a user is playing as a list
		String query = "Select 'state' from betterbytes.game WHERE `player1` like '" + username + "';"; 
		
		List <String> resultStrings =  getGamesQuery(query);		
		return null;
	}

	public List<Game> getGames(Account acc) {
		// TODO Same as above, just that I need every game a user is playing as a list
		return null;
	}
	
	public List<Game> getGames(Player acc) {
		// TODO Same as above, just that I need every game a user is playing as a list
		return null;
	}


}// End Class
