package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

public interface Protocol {
	public final String USER_REGISTRATION = "00";
	public final String USER_LOGON = "01";
	public final String USER_REGISTRATION_RESPONSE = "02";
	public final String USER_LOGON_RESPONSE = "03";
	public final String RECORDS_REQUEST = "04";
	public final String RECORDS_REQUEST_RESPONSE = "05";
	public final String CREATE_INVITATION = "06";
	public final String CREATE_INVITATION_RESPONSE = "07";
	public final String RESPOND_TO_INVITATION = "08";
	public final String RESPOND_TO_INVITATION_RESPONSE = "09";
	public final String SUBMIT_MOVE = "10";
	public final String SUBMIT_MOVE_RESPONSE = "11";
	
}
