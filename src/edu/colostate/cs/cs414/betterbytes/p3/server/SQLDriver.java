
package edu.colostate.cs.cs414.betterbytes.p3.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class SQLDriver {	
	
	private Connection database;
	//must be changed for whatever machine it is being run on 3306 is the port server
	private String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
	//Credentials for the database to log into
	private String user = "root";
	private String pass = "Code";
	
	private static final SQLDriver instance = new SQLDriver();
	public static SQLDriver getInstance(){return instance;}
	
	private SQLDriver()
	{
		openConnection(url,user,pass);
	}
	
	private void openConnection(String url, String User, String pass)
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            database = DriverManager.getConnection(url, User, pass);
        } catch (Exception e) {
            System.err.printf("Unable to Open Connection: ");
            System.err.println(e.getMessage());
        }
    }
	

	public boolean checkLogin(String username,String password)
	{
		String result[] = loginQuery(username,password);		
		if(result[1].equals(password))
			return true;
		return false;
	}//End method
	
    public String[] loginQuery(String username,String password) {        
        String[] results  = new String [2];        
        String query =  "SELECT * FROM test.database WHERE `username` LIKE '" + username + "';";
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
     	SQLDriver sql = SQLDriver.getInstance();
		String[] results = sql.loginQuery("ctunnell@rams.colostate.edu","TestPassword");

		if(sql.checkLogin("ctunnell@rams.colostate.edu", "TestPassword"))
		{
			System.out.println("Login Verified");
		}
		else
		{
			System.out.println("Login Denied");
		}
     */
    
    
    
    
}//End Class
