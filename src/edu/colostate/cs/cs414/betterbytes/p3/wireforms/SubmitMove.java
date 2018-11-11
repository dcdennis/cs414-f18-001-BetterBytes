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
	
	public SubmitMove(Game gameUpdate)
	{
		this.gameUpdate = gameUpdate;
	}
	
	public Game getGameUpdate()
	{
		return gameUpdate;
	}
	@Override
	public String getType() {
		return type;
	}

}
