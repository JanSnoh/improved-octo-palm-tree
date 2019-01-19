/**
 * 
 */
package sc.player2019.tactic;

import sc.plugin2019.Board;
import sc.plugin2019.Field;
import sc.plugin2019.GameState;
import sc.plugin2019.util.GameRuleLogic;

/**
 * @author jan
 *
 */
public class TacticMitte implements Tactic {

	/**
	 * 
	 */
	public TacticMitte() {

	}
	/*
	@Override
	Move getMove(GameState gs, PlayerColor c) {
		ArrayList<Move> possibleMoves = GameRuleLogic.getPossibleMoves(gs);
		HashMap<Move, Float> deconMoves = new HashMap<Move, Float>();
		for (Move m : possibleMoves) {
			deconMoves.put(m, deconcentrationOfGameState(c, moveToGameState(gs, m)));
		}

		Collections.sort(possibleMoves, new Comparator<Move>() {
			@Override
			public int compare(Move move1, Move move2) {
				float dc1 = deconMoves.get(move1);
				float dc2 = deconMoves.get(move2);
				return (dc1 <= dc2) ? -1 : 1;
			}
		});

		System.out.println(possibleMoves);
		return possibleMoves.get(0);
		
	}
	*/

	


	@Override
	public double tacticRatesGameState(GameState gamestate, GameState oldGameState) {
		Board board = gamestate.getBoard();
		double rating = 0;
		for (Field f : GameRuleLogic.getOwnFields(board, gamestate.getCurrentPlayerColor())) {
			float dx = (float) (4.5 - f.getX());
			float dy = (float) (4.5 - f.getY());
			rating += Math.pow((dx * dx + dy * dy), 0.5);
		}
		rating =1/(1+Math.pow(Math.E,(rating*0.07-2))); //Sigmoid stuf with parameters
		
		return rating;
	}

}
