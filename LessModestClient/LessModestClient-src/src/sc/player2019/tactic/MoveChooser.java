package sc.player2019.tactic;

import java.util.HashMap;
import java.util.Map;

import sc.player2019.logic.Logic;
import sc.player2019.logic.WinState;
import sc.plugin2019.GameState;
import sc.plugin2019.Move;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.PlayerColor;

/**
 * Does main calculation to choose a Move
 * 
 * @author kajus
 * @since 15.11.18
 * @version 1
 */
public class MoveChooser {
	/**
	 * Contains all Tactics and their importance
	 * 
	 */
	static HashMap<Tactic, Double> tacticsAndImportance = new HashMap<Tactic, Double>();
	// static double[] importance;
	// static Tactic[] tactics;

	// TODO well you know, like, code it.
	static Move getBestMove(GameState gamestate) {
		return null;
	}

	/**
	 * TODO Lets all Tactics evaluate the gamestate (int 0 to 100) and multiplies it
	 * by their importance and divides it by the sum of all importances, so that the
	 * Importance is normalized to 1
	 * 
	 * @param gamestate
	 * @return value of the gamestate
	 */
	public static Triple<WinState, Double, Move> evaluateGamestate(GameState gamestate) {
		PlayerColor currentPlayer = gamestate.getCurrentPlayerColor();
		WinState winstate = Logic.getWinState(gamestate, currentPlayer);
		double rating = 0;
		switch(winstate) {
		case NEUTRAL:
			rating = 0;
			double devider = 0;
			for(Map.Entry<Tactic, Double> entry : tacticsAndImportance.entrySet()) {
				rating += entry.getValue() * entry.getKey().tacticRatesGameState(gamestate); 
				devider += entry.getValue();
			}
			rating /= devider;//normalize rating so that importance = 1
			break;
			
		case WIN:
			rating = 100;// max value
			break;
			
		case LOOSE:
			rating = 0;// min value
		}
			
		return new Triple<WinState, Double, Move>(winstate,rating,null);
		
	}

	/**
	 * TODO Uses Alpha Beta search to find the best move
	 * 
	 * @param gametate
	 * @param depth
	 * @return Triple <br>
	 *         first: Is this gamestate is a WIN, a LOOSE or NEUTRAL<br>
	 *         second: What score does the move have<br>
	 *         third: What Move leads to the best outcome
	 * 
	 */
	public static Triple<WinState, Double, Move> alphaBeta(GameState gametate, int depth) {
		return null;
	}

	public static void updateImportance() {

	}

}



class Triple<A, B, C> {
	public final A first;
	public final B second;
	public final C third;

	public Triple(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public C getThird() {
		return third;
	}

}
