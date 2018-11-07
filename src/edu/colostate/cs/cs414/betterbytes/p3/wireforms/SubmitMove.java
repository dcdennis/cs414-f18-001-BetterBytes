package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

public class SubmitMove implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074226053924694692L;
	private final String type = Protocol.SUBMIT_MOVE;
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
