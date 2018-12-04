package edu.colostate.cs.cs414.betterbytes.p4.server;

import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Serializer;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;

public class testingPlzIgnore {
	public static void main(String[] args)
	{
		Account a = new Account("ctunnel","TestPassword");
		System.out.println("A: " + a.toString());
		byte[] bytes = Serializer.serialize(a);
		System.out.println(new String(bytes));
		Account b = Serializer.deserializeAccount(bytes);
		System.out.println("B: " + b.toString());
	}
}
