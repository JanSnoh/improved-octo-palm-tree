package sc.player2019.tactic;

import static sc.player2019.util.CheckWinCondition.checkWinCondition;
import static sc.player2019.util.GameStateUtil.moveToGameState;

import java.util.ArrayList;

import sc.player2019.util.CheckWinCondition;
import sc.player2019.util.Triple;
import sc.plugin2019.GameState;
import sc.plugin2019.Move;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.PlayerColor;
import sc.shared.WinCondition;

/**
 * Does main calculation to choose a Move
 * 
 * @author kajus
 * @since 15.11.18
 * @version 1
 */
public class MoveChooser {
	/*FIXME Big massive Error either here or in GameStateUtilities 
	The error causes gameState and oldGameState to be (almost) the same so that differences detected by TacticDMitte
	are in the order of magnitude of E-14. 
	
	*/
	public MoveChooser() {
	}

	// static double[] importance;
	// static Tactic[] tactics;

	public Move getBestMove(GameState gamestate) {
		int depth = (gamestate.getCurrentPlayerColor() == PlayerColor.RED) ? 1 : 2; //TODO after debugs 2:3
		Triple<WinCondition, Double, Move> bestMoveTriplet = alphaBeta(gamestate, null, depth);
		return bestMoveTriplet.getThird();
	}

	/**
	 * @param gamestate
	 * @return value of the gamestate
	 */
	private Triple<WinCondition, Double, Move> evaluateGamestate(GameState gamestate, GameState oldGameState) {
		PlayerColor currentPlayer = gamestate.getCurrentPlayerColor();
		WinCondition winCon = checkWinCondition(gamestate);
		// PlayerColor winningPlayer = winCon.getWinner();
		double rating = 0;
		if (winCon == null) {
			rating = 0;
			double divider = 0;
			for (Taktiken i : Taktiken.values()) {
				double importance = i.getImportance(gamestate);
				rating += i.tacticRatesGameState(gamestate, oldGameState) * importance;
				divider += importance;
			}
			rating /= divider;// normalize rating so that importance = 1
		} else if (currentPlayer.equals(winCon.getWinner())) {
			rating = Tactic.maxRating;
		} else if (currentPlayer.opponent().equals(winCon.getWinner())) {
			rating = Tactic.minRating;
		}

		return new Triple<WinCondition, Double, Move>(winCon, rating, null);
	}

	/**
	 * 
	 * @param gamestate
	 * @param depth     has to end on a even turn
	 * @return Triple <br>
	 *         first: Is this gamestate is a WIN, a LOOSE or NEUTRAL<br>
	 *         second: What score does the move have<br>
	 *         third: What Move leads to the best outcome
	 * 
	 */
	private Triple<WinCondition, Double, Move> alphaBeta(GameState gamestate, GameState oldGameState, int depth) {
		PlayerColor currentPlayer = gamestate.getCurrentPlayerColor();
		WinCondition ws = CheckWinCondition.checkWinCondition(gamestate);
		if (depth <= 0 || ws != null) {
			return evaluateGamestate(gamestate, oldGameState);
		} else {
			ArrayList<Move> possibleMoves = GameRuleLogic.getPossibleMoves(gamestate);
			Triple<WinCondition, Double, Move> worstForEnemyMoveTriple = alphaBeta(
					moveToGameState(gamestate, possibleMoves.get(0)), gamestate, depth - 1);
			worstForEnemyMoveTriple.setThird(possibleMoves.get(0));
			for (Move move : possibleMoves) {
				if (worstForEnemyMoveTriple.getFirst()!= null && worstForEnemyMoveTriple.getFirst().getWinner().equals(currentPlayer)) {
					break;
				}
				Triple<WinCondition, Double, Move> currentMoveTriple = alphaBeta(moveToGameState(gamestate, move), gamestate,
						depth - 1);
				if (worstForEnemyMoveTriple.getSecond() > currentMoveTriple.getSecond()
						||(worstForEnemyMoveTriple.getFirst()!= null && currentMoveTriple.getFirst().getWinner().equals(currentPlayer))) { // the lower the enemy
																								// score the better
					worstForEnemyMoveTriple = currentMoveTriple;
					worstForEnemyMoveTriple.setThird(move);

				}
			}
			// winne

			worstForEnemyMoveTriple.setSecond(Tactic.maxRating - worstForEnemyMoveTriple.getSecond());
			return worstForEnemyMoveTriple;

		}
	}

	public void updateImportance() {

	}

}
