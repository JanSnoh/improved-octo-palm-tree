/**
 * 
 */
package sc.player2019.tactic;

import sc.plugin2019.GameState;

/**
 * @author jan
 *
 */

//TODO Add more Tactics
//TODO @jan Add minSpread Tactic
//TODO Decide where to put 'moveToGameState' and TacticMitte.'deconcentrationOfGameState'
public interface Tactic {
	public static final  double maxRating = 1;
	public static final double minRating = 0;
	/**
	 * @author Kajus
	 * @param gamestate
	 * @return the rating of given gamestate in the range from 0 to maxRating where 0 is the worst
	 */
	public double tacticRatesGameState(GameState gamestate, GameState oldGameState);
	
	default double getImportance(GameState gamestate) {
		return 1;
	}
	
}
