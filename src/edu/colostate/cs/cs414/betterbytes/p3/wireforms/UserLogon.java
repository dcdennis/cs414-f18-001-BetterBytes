package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

public class UserLogon implements Protocol, Message {
	private String username;
	private String passwordHash;
		
	public UserLogon(String username, String passwordHash)
	{
		this.username = username;
		this.passwordHash = passwordHash;
	}
		
	public UserLogon(String stringRep)
	{
		String[] data = stringRep.split(", ");
		if(!data[0].equals(USER_LOGON))
			System.out.println("Something is wrong");
		this.username = data[1];
		this.passwordHash = data[2];
	}
		
	public String getStringRepresentation()
	{
		return USER_LOGON + ", " + username + ", " + passwordHash;
	}
		
	public String getUsername() {return username;}
	public String getPasswordHash() {return passwordHash;}
}
