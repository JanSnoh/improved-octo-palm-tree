package sc.player2019.tactic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import sc.framework.plugins.Player;
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
	public static Triple<WinLoose, Double, Move> evaluateGamestate(GameState gamestate) {
		Player winner = gamestate.getWinner();//TODO how to to this??? assumes that it returns null if there isn't a winner
		Player currentPlayer = gamestate.getCurrentPlayer();
		WinLoose wl;
		if (winner == null) {
			wl = WinLoose.NEUTRAL;
			double rating = 0;
			double devider = 0;
			for(Map.Entry<Tactic, Double> entry : tacticsAndImportance.entrySet()) {
				rating += entry.getValue() * entry.getKey().tacticRatesGameState(gamestate); 
				devider += entry.getValue();
			}
			rating /= devider;
			return new Triple<WinLoose, Double, Move>(wl,rating,null);
			
		}else {
			return (winner == currentPlayer)? new Triple<WinLoose, Double, Move>(WinLoose.WIN,(double)100,null) : //Case win
											  new Triple<WinLoose, Double, Move>(WinLoose.LOOSE,(double)100,null); //Case loose
		}
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
	public static Triple<WinLoose, Double, Move> alphaBeta(GameState gametate, int depth) {
		return null;
	}
	/*
	 * public static double[] evaluateMoves(GameState gs, PlayerColor c){
	 * ArrayList<Move> possibleMoves = GameRuleLogic.getPossibleMoves(gs); double[]
	 * indecency = new double[possibleMoves.size()]; for (int i=0;
	 * i<possibleMoves.size(); i++){ GameState copy = Tactic.moveToGameState(gs,
	 * possibleMoves.get(i)); for(int j=0; j < tactics.length;j++){ indecency[i]+=
	 * importance[j] * tactics[j].evaluateGS(copy, c); } } return indecency;
	 * 
	 * 
	 * }
	 */

	public static void updateImportance() {

	}

}

enum WinLoose {
	WIN, LOOSE, NEUTRAL;
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
