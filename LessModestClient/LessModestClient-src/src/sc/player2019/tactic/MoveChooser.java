package sc.player2019.tactic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sc.player2019.logic.Logic;
import sc.player2019.logic.WinState;
import sc.plugin2019.GameState;
import sc.plugin2019.Move;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.PlayerColor;
import sc.shared.WinCondition;
import static sc.player2019.util.CheckWinCondition.checkWinCondition;
import static sc.player2019.util.GameStateUtil.moveToGameState;

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
	static HashMap<Taktiken, Double> tacticsAndImportance = new HashMap<Taktiken, Double>();
	// static double[] importance;
	// static Tactic[] tactics;
	
	//TODO Setup, give mc the tactics. and importance.

	public static Move getBestMove(GameState gamestate) {
		int depth = (gamestate.getCurrentPlayerColor() == PlayerColor.RED)? 2 : 3;
		Triple<WinCondition, Double, Move> bestMoveTriplet = alphaBeta(gamestate, depth);
		return bestMoveTriplet.getThird();
	}

	/**
	 * @param gamestate
	 * @return value of the gamestate
	 */
	public static Triple<WinCondition, Double, Move> evaluateGamestate(GameState gamestate) {
		PlayerColor currentPlayer = gamestate.getCurrentPlayerColor();
		WinCondition winCon = checkWinCondition(gamestate);
		PlayerColor winningPlayer = winCon.getWinner();
		double rating = 0;
		if (winningPlayer == null) {
			rating = 0;
			double divider = 0;
			for(Map.Entry<Taktiken, Double> entry : tacticsAndImportance.entrySet()) {
				rating += entry.getValue() * entry.getKey().tacticRatesGameState(gamestate); 
				divider += entry.getValue();
			}
			rating /= divider;//normalize rating so that importance = 1
		}else if (currentPlayer.equals(winningPlayer)) {
			rating = 100;
		}else if (currentPlayer.opponent().equals(winningPlayer)) {
			rating = 0;
		}

		return new Triple<WinCondition, Double, Move>(winCon,rating,null);
	}

	/**
	 * 
	 * @param gamestate
	 * @param depth has to end on a even turn
	 * @return Triple <br>
	 *         first: Is this gamestate is a WIN, a LOOSE or NEUTRAL<br>
	 *         second: What score does the move have<br>
	 *         third: What Move leads to the best outcome
	 * 
	 */
	public static Triple<WinCondition, Double, Move> alphaBeta(GameState gamestate, int depth) {
		PlayerColor currentPlayer = gamestate.getCurrentPlayerColor();
		WinState ws = Logic.getWinState(gamestate, gamestate.getCurrentPlayerColor());
		if(depth <= 0 || ws != null) {
			return evaluateGamestate(gamestate);
		}else {
			ArrayList<Move> possibleMoves = GameRuleLogic.getPossibleMoves(gamestate);
			Triple<WinCondition, Double, Move> worstForEnemyMoveTriple = alphaBeta(moveToGameState(gamestate,possibleMoves.get(0)),depth-1);
			worstForEnemyMoveTriple.setThird(possibleMoves.get(0));
			for(Move move : possibleMoves) {
				if(worstForEnemyMoveTriple.getFirst().getWinner().equals(currentPlayer)){
					break;
				}
				Triple<WinCondition, Double, Move> currentMoveTriple = alphaBeta(moveToGameState(gamestate,move),depth-1);
				if(worstForEnemyMoveTriple.getSecond() > currentMoveTriple.getSecond()) { // the lower the enemy score the better
					worstForEnemyMoveTriple = currentMoveTriple;
					worstForEnemyMoveTriple.setThird(move);
					
				}
			}
			//winne

			worstForEnemyMoveTriple.setSecond(100-worstForEnemyMoveTriple.getSecond());
			return worstForEnemyMoveTriple;
			
		}
	}

	public static void updateImportance() {

	}

}



class Triple<A, B, C> {
	public  A first;
	public  B second;
	public  C third;

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
	public void setFirst(A a) {
		first = a;
	}
	public void setSecond(B b) {
		second = b;
	}
	public void setThird(C c) {
		third = c;
	}

}
