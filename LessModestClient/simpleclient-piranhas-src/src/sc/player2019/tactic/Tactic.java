/**
 * 
 */
package sc.player2019.tactic;

import sc.plugin2019.*;
import sc.shared.InvalidGameStateException;
import sc.shared.InvalidMoveException;
import sc.shared.PlayerColor;

/**
 * @author jan
 *
 */
public abstract class Tactic {
	
	Taktikname name;
	
	Tactic(){
	}
	
	
	abstract Move getMove(GameState gs, PlayerColor c);
	protected GameState moveToGameState(GameState gs, Move m) {
		GameState g = gs.clone();
		try {
			m.perform(g);
		} catch (InvalidGameStateException | InvalidMoveException e) {
			e.printStackTrace();
		}
		return g;
	}
}
