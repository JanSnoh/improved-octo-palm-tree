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

//TODO Add more Tactics
//TODO @jan Add minSpread Tactic
//TODO Decide where to put 'moveToGameState' and TacticMitte.'deconcentrationOfGameState'
public abstract class Tactic {
	Tactic(){
	}
	/**
	 * @author Kajus
	 * @param gamestate
	 * @return the rating of given gamestate in the range from 0 to 100 where 0 is the worst
	 */
	abstract double tacticRatesGameState(GameState gamestate);
	
	//TODO stuff below here is not longer useful
	abstract Move getMove(GameState gs, PlayerColor c);
	
	static GameState moveToGameState(GameState gs, Move m) {
		GameState g = gs.clone();
		try {
			m.perform(g);
		} catch (InvalidGameStateException | InvalidMoveException e) {
			e.printStackTrace();
		}
		return g;
	}
}
