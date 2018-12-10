
package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Serializer;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

/**
 * Handles SQL DB access and data storage. Defaults to faure.cs.colostate.edu/betterbytes
 * @version 1.0
 */
public class SQLDriver {

	// Code to add the cases needed for the testing to work
	public static void main(String[] args) {
		setPlayersinDB();
		setGamesinDB();
		// SQLDriver.getInstance().getGames("ctunnell");
	}

	/**
	 * Sets the players in the data base to a default list of users. Used for testing
	 */
	private static void setPlayersinDB() {
		SQLDriver sql = SQLDriver.getInstance();
		sql.runQuery("Delete from users");
		Account testAcc1 = new Account("ctunnell", "TestPassword");
		Account testAcc2 = new Account("Jhpokorski", "TestPassword2");
		Account testAcc3 = new Account("ctunnell@rams.colostate.edu", "TestPassword");
		Account testAcc4 = new Account("AIUSER", "AILOGON");
		sql.addUser(testAcc1);
		sql.addUser(testAcc2);
		sql.addUser(testAcc3);
		sql.addUser(testAcc4);

		for (int i = 0; i < 10; i++) {
			sql.addUser(new Account("" + i, "TestPassword"));
		}
	}

	/**
	 * Sets the games in the data base to a default list of games. Used for testing
	 */
	private static void setGamesinDB() {
		SQLDriver sql = SQLDriver.getInstance();
		sql.runQuery("Delete from game");

		Account testAcc1 = sql.getAccount("ctunnell");
		Player p1 = new Player(testAcc1);
		Account testAcc2 = sql.getAccount("Jhpokorski");
		Player p2 = new Player(testAcc2);
		Game g1 = new Game("0:0", p1, p2);
		sql.addGame(p1, p2, g1);

		Account testAcc3 = sql.getAccount("AIUSER");
		Player p3 = new Player(testAcc3);
		Game g2 = new Game("0:0", p1, p3);
		sql.addGame(p3, p3, g2);

	}

	private Connection database;
	// must be changed for whatever machine it is being run on 3306 is the port
	// server
	private String url = "jdbc:mysql://faure.cs.colostate.edu/betterbytes";
	// Credentials for the database to log into
	private String user = "ctunnell";
	private String pass = "830336263";

	private static final SQLDriver instance = new SQLDriver();

	/**
	 * Gets the singleton instance object
	 * @return the instance of the SQLDriver
	 */
	public static SQLDriver getInstance() {
		return instance;
	}

	private SQLDriver() {
		openConnection(url, user, pass);
	}

	/**
	 * Opens a connect with the database based on the url, user, and their password
	 * @param url url of the database
	 * @param User Username credential
	 * @param pass Password credential
	 */
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

	/**
	 * Makes a query for the database to update the username and password fields for a given account.
	 * Username and password fields must be correct, though inputs are not sanitized.
	 * @param username Username to set
	 * @param password Password to set
	 * @param acc Account to set the fields in
	 */
	public void setAccount(String username, String password, Account acc) {
		String query = "UPDATE betterbytes.users SET `username`=\"" + username + "\", " + "`password`=\"" + password
				+ "\",`account`=\"" + Base64.getEncoder().encodeToString(Serializer.serialize(acc)) + "\" ";

		query += "WHERE `username`=\"" + username + "\" AND `password`=\"" + password + "\";";
		runQuery(query);
	}

	/**
	 * Makes a query to update the account in the database. Gets password and username fields from the account.
	 * @param acc Account to set
	 */
	public void setAccount(Account acc) {
		String query = "UPDATE betterbytes.users SET `username`=\"" + acc.getUsername() + "\", " + "`password`=\""
				+ acc.getPassword() + "\",`account`=\"" + Base64.getEncoder().encodeToString(Serializer.serialize(acc))
				+ "\" ";

		query += "WHERE `username`=\"" + acc.getUsername() + "\" AND `password`=\"" + acc.getPassword() + "\";";
		runQuery(query);
	}

	// Gets the account from a given username and password
	// Returns the Account object
	/**
	 * Gets the account from a given username and password. Counts a login query.
	 * @param username Username of the account
	 * @param password Password of the account
	 * @return the account object based on the username and password
	 */
	public Account getAccount(String username, String password) {
		String[] results = loginQuery(username, password);
		System.out.println("Bytes: " + new String(results[2].getBytes()));
		Account received = Serializer.deserializeAccount(Base64.getDecoder().decode(results[2]));

		return received;
	}

	/**
	 * Gets the account from a given username.
	 * @param username Username of the account
	 * @return the account object based on the username
	 */
	public Account getAccount(String username) {
		String query = "Select * FROM betterbytes.users";
		query += " WHERE `username`=\"" + username + "\";";
		String[] results = runQueryRes(query);
		for (String i : results)
			System.out.println(i);
		if (results[2] != null) {
			Account received = Serializer.deserializeAccount(Base64.getDecoder().decode(results[2]));

			return received;
		} else {
			return new Account("null","null");//Cause theres literally no null checks anywhere. 
		}
	}

	// Checks if a user already exists
	// if they do not then we add them to the database
	/**
	 * Checks if a user already exists. If they do not then are added to the database.
	 * @param username Username to add
	 * @param password Password to add linked to the username
	 * @return true if a user was added, false otherwise
	 */
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

	/**
	 * Checks if an account already exists. If they do not then they are added to the database.
	 * @param acc Account to add
	 * @return true if they account was added, false otherwise
	 */
	public boolean addUser(Account acc) {
		String result[] = loginQuery(acc.getUsername(), acc.getPassword());
		if (result[0] != null) {
			return false;
		} else {
			String query = "INSERT INTO betterbytes.users (username, password, account) VALUES ('" + acc.getUsername()
					+ "' , '" + acc.getPassword() + "' , '"
					+ Base64.getEncoder().encodeToString(Serializer.serialize(acc)) + "' );";
			runQuery(query);
			return true;
		}
	}// End Methods


	/**
	 * Adds a game to the database between two players and a given state
	 * @param player1 Player 1
	 * @param player2 Player 2
	 * @param state Game object
	 */
	public void addGame(Player player1, Player player2, Game state) {
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		addGame(p1, p2, state);
	}

	/**
	 * Adds a game to the database between two accounts and a given state
	 * @param player1 Player 1's account
	 * @param player2 Player 2's account
	 * @param state Game object
	 */
	public void addGame(Account player1, Account player2, Game state) {
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		addGame(p1, p2, state);
	}

	/**
	 * Adds a game to the database between two players as strings and a given state
	 * @param p1 Player 1 as a string
	 * @param p2 Player 2 as a string
	 * @param state Game object
	 */
	void addGame(String p1, String p2, Game state) {
		String query = "INSERT INTO betterbytes.game (player1,player2,state) " + "VALUE('" + p1 + "' , '" + p2 + "' , '"
				+ Base64.getEncoder().encodeToString(Serializer.serialize(state)) + "');";
		runQuery(query);
	}

	/**
	 * Delete a game between two players
	 * @param player1 Player 1
	 * @param player2 Player 2
	 * @return true if the game was deleted, false otherwise
	 */
	public boolean deleteGame(Player player1, Player player2) {
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		return deleteGame(p1, p2);
	}

	/**
	 * Delete a game between two player's accounts
	 * @param player1 Player 1's account
	 * @param player2 Player 2's account
	 * @return true if the game was deleted, false otherwise
	 */
	public boolean deleteGame(Account player1, Account player2) {
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		return deleteGame(p1, p2);
	}

	/**
	 * Delete a game between two players as strings
	 * @param p1 Player 1 as a string
	 * @param p2 Player 2 as a string
	 * @return true if the game was deleted, false otherwise
	 */
	private boolean deleteGame(String p1, String p2) {
		String query = "DELETE FROM betterbytes.game WHERE `player1` LIKE '" + p1 + "' and `player2` LIKE '" + p2
				+ "';";
		runQuery(query);

		query = "DELETE FROM betterbytes.game WHERE `player1` LIKE '" + p2 + "' and `player2` LIKE '" + p1 + "';";
		runQuery(query);

		return true;
	}

	/**
	 * Gets a current game between two players
	 * @param player1 Player 1
	 * @param player2 Player 2
	 * @return true if the game was retrieved, false otherwise
	 */
	public Game getGame(Player player1, Player player2) {
		String p1 = player1.getAccount().getUsername();
		String p2 = player2.getAccount().getUsername();
		Game result = getGame(p1, p2);

		return result;
	}

	/**
	 * Gets a current game between two players' accounts
	 * @param player1 Player 1's account
	 * @param player2 Player 2's account
	 * @return true if the game was retrieved, false otherwise
	 */
	public Game getGame(Account player1, Account player2) {
		String p1 = player1.getUsername();
		String p2 = player2.getUsername();
		Game result = getGame(p1, p2);

		return result;
	}


	/**
	 * Executes a Query and returns the results
	 * @param query database Query
	 * @return the size 3 results array
	 */
	private String[] queryGame(String query) {
		String[] results = new String[3];
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
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
	/**
	 * Gets the game being played between two players as strings
	 * @param p1 Player 1 as a string
	 * @param p2 Player 2 as a string
	 * @return the Game object being played
	 */
	private Game getGame(String p1, String p2) {
		String query = "SELECT * FROM betterbytes.game WHERE `player1` LIKE \"" + p1 + "\" and `player2` LIKE \"" + p2
				+ "\";";
		String[] results = queryGame(query);

		if (results[2] == null || results[2].equals("null")) {
			query = "SELECT * FROM betterbytes.game WHERE `player1` LIKE \"" + p2 + "\" and `player2` LIKE \"" + p1
					+ "\";";
			results = queryGame(query);
		}

		return Serializer.deserializeGame(Base64.getDecoder().decode(results[2]));
	}

	/**
	 * Deletes a user from the database based on username and password
	 * @param username Username of the user
	 * @param password Password of the user
	 * @return true if the user was deleted, false otherwise
	 */
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

	/**
	 * Deletes a user from the database based on an account
	 * @param acc Account of the user
	 * @return true if the user was deleted, false otherwise
	 */
	public boolean deleteUser(Account acc) {
		String query = "DELETE FROM betterbytes.users WHERE `username` LIKE '" + acc.getUsername()
				+ "' and `password` LIKE '" + acc.getPassword() + "';";
		runQuery(query);

		String result[] = loginQuery(acc.getUsername(), acc.getPassword());

		if (result[0] == null)
			return true;
		else
			return false;
	}// End Method

	/**
	 * Executes a query without any return
	 * @param query Query to run
	 */
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

	// Can be changed to Array list if needed to be more specific.

	/**
	 * Executes a query and returns the results of the query
	 * @param query Query to run
	 * @return results of the query
	 */
	private String[] runQueryRes(String query) {
		String[] results = new String[3];
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
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


	/**
	 * Executes a query and returns a list of results
	 * @param query Query to run
	 * @return List of the query results
	 */
	private List<String> getGamesQuery(String query) {
		List<String> results = new ArrayList<String>();
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
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

	/**
	 * Check if a username/password pair is valid
	 * @param username Username
	 * @param password Password
	 * @return true if the username password pair is valid, false otherwise
	 */
	public boolean checkLogin(String username, String password) {
		String result[] = loginQuery(username, password);
		if (result[1] == null || !(result[1].equals(password)))
			return false;
		else if (result[1].equals(password))
			return true;

		return false;

	}// End method

	/**
	 * Executes a long query based on the username and password pair
	 * @param username Username
	 * @param password Password
	 * @return results of the long in query
	 */
	public String[] loginQuery(String username, String password) {
		String[] results = new String[3];
		String query = "SELECT * FROM betterbytes.users WHERE `username` LIKE '" + username + "' AND `password` LIKE '"
				+ password + "';";
		try {
			Statement st = database.createStatement();
			try {
				ResultSet result = st.executeQuery(query);
				try {
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

	/**
	 * Update the game between two players in the database
	 * @param p1 Player 1
	 * @param p2 Player 2
	 * @param g1 Game object
	 */
	public void updateGame(Player p1, Player p2, Game g1) {
		String query = "UPDATE game SET `state` = '" + Base64.getEncoder().encodeToString(Serializer.serialize(g1))
				+ "' WHERE `player1` = '" + p1.getAccount().getUsername() + "' AND `player2` = '"
				+ p2.getAccount().getUsername() + "';";
		// System.out.println("Query: " + query);
		runQuery(query);
	}

	/**
	 * Get the list of games linked to a username
	 * @param username Username string
	 * @return List of game objects
	 */
	public List<Game> getGames(String username) {
		String query = "Select `state` from betterbytes.game WHERE `player1` like '" + username + "';";
		List<String> resultStrings = getGamesQuery(query);
		query = "Select `state` from betterbytes.game WHERE `player2` like '" + username + "';";
		resultStrings.addAll(getGamesQuery(query));

		List<Game> games = new ArrayList<Game>();

		for (int i = 0; i < resultStrings.size(); i++) {
			games.add(Serializer.deserializeGame(Base64.getDecoder().decode(resultStrings.get(i))));
		}

//		System.out.println("Games: " + Arrays.toString(games.toArray(new Game[] {})));
		return games;
	}

	/**
	 * Get the list of games linked to an account
	 * @param acc Account object
	 * @return List of game objects
	 */
	public List<Game> getGames(Account acc) {
		return getGames(acc.getUsername());
	}

	/**
	 * Get the list of game objects linked to a player
	 * @param p1 Player object
	 * @return List of game objects
	 */
	public List<Game> getGames(Player p1) {
		return getGames(p1.getAccount().getUsername());
	}

}// End Class
