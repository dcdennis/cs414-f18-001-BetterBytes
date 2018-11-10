package edu.colostate.cs.cs414.betterbytes.p3.utilities;

import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p3.game.Move;

public class RulesEngine {
	private static RulesEngine instance = new RulesEngine();
	private RulesEngine() {}
	public RulesEngine getInstance()
	{
		return instance;
	}
	public GameResult gameHasEnded(Game game)
	{
		
		//CASE: King has been captured
		boolean kingFound = false;
		for(int i = 0; i < 11; i++)
		{
			for(int j=0; j< 11; j++)
			{
				Cell checkLoc = game.getCell(i,j);
				if(checkLoc.isOccupied() && checkLoc.getPiece().isKing())
					kingFound = true;
			}
		}
		if(!kingFound)
			return GameResult.BLACK;
		
		//CASE: King has escaped
		if(	game.getCell(0, 0).isOccupied() || game.getCell(0,10).isOccupied() ||
				game.getCell(10, 0).isOccupied() || game.getCell(10, 10).isOccupied())
			return GameResult.WHITE;
		
		//CASE: King is on an edge, surrounded by black pieces, and there are no other white pieces
		
		//TODO
		
		//CASE: Move pattern repetition
		List<Move> moveHistory = game.getMoves();
		if(moveHistory.size() > 12)
		{
			List<Move> movePattern = moveHistory.subList(moveHistory.size() - 3, moveHistory.size());
			if(moveHistory.subList(moveHistory.size() - 7, moveHistory.size() - 4).equals(movePattern) && moveHistory.get(moveHistory.size() - 11, moveHistory.size() - 8).equals(movePattern))
	            return GameResult.DRAW;
		}
		return GameResult.CONTINUE;		
	}
	/**
	 * checkTerminal(State,moveHistory)
{
    //King has been capturd
    kingFound = false
    for(i = 0; i < 11; 1++)
        for(j=0; j< 11; j++)
            if( state.location(i,j).contains != null && state.location(i,j).contains.type == king)
                kingFound = true
    if(!kingFound)
        return black
    
    //King has escaped
    if(state.location(0,0).contains != null || state.location(0,10).contains != null || state.location(10,0).contains != null state.location(10,10).contains != null)
        return white
        
    //White only has a king, which can not move
    ?????
    
    //Repeated move pattern
    if(moveHistory.length > 12)
    {
        movePattern = moveHistory.get(moveHistory.length - 3, moveHistory.length - 0)
        if(moveHistory.get(moveHistory.length - 7, moveHistory - 4).equals(movePattern) && moveHistory.get(moveHistory.length - 11, moveHistory - 8).equals(movePattern))
            return draw
    }
    
}

	 */
}
