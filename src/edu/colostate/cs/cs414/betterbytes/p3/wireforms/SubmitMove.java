package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;

public class SubmitMove implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074226053924694692L;
	private final String type = Protocol.SUBMIT_MOVE;

	private Game gameUpdate;

	public SubmitMove(Game gameUpdate) {
		this.gameUpdate = gameUpdate; 
	}

	public Game getGameUpdate() {
		return gameUpdate;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SubmitMove other = (SubmitMove) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
