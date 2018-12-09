package edu.colostate.cs.cs414.betterbytes.p4.server.wireforms;

import java.io.Serializable;

/**
 * SubmitMoveResponse class. Response to submitting a move message.
 * @version 1.0
 */
public class SubmitMoveResponse implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1393201663237341644L;
	/**
	 * 
	 */
	private final String type = Protocol.SUBMIT_MOVE_RESPONSE;
	private boolean status;
	private String message;

	public SubmitMoveResponse(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public boolean getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubmitMoveResponse other = (SubmitMoveResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (status != other.status)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String getType() {
		return type;
	}

}
