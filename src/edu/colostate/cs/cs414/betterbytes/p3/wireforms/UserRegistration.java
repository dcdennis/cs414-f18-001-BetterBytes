/**
 * 
 */
package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

public class UserRegistration implements Protocol, Message
{
	private String username;
	private String passwordHash;
	//private String passwordSalt;
	
	public UserRegistration(String username, String passwordHash)
	{
		this.username = username;
		this.passwordHash = passwordHash;
		//this.passwordSalt = passwordSalt;
	}
	
	public UserRegistration(String stringRep)
	{
		String[] data = stringRep.split(", ");
		if(!data[0].equals(USER_REGISTRATION))
			System.out.println("Something is wrong");
		this.username = data[1];
		this.passwordHash = data[2];
		//this.passwordSalt = data[3];
	}
	
	public String getStringRepresentation()
	{
		return USER_REGISTRATION + ", " + username + ", " + passwordHash; // + ", " + passwordSalt;
	}
	
	public String getUsername() {return username;}
	public String getPasswordHash() {return passwordHash;}
	//public String getPasswordSalt() {return passwordSalt;}

}
