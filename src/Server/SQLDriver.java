package Server;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDriver {
	
	
	private Connection database;
	private String url = "";
	private String user = "";
	private String pass = "";
	
	private static final SQLDriver instance = new SQLDriver();
	public static SQLDriver getInstance(){return instance;}
	
	private SQLDriver()
	{
		openConnection(url,user,pass);
	}
	
	private void openConnection(String url, String User, String pass)
    {

        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            database = DriverManager.getConnection(url, User, pass);
        } catch (Exception e) {
            System.err.printf("Unable to Open Connection: ");
            System.err.println(e.getMessage());
        }
    }
	
	
}
