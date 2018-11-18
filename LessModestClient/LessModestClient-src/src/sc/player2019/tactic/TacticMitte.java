/**
 * 
 */
package sc.player2019.tactic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import sc.plugin2019.Board;
import sc.plugin2019.Field;
import sc.plugin2019.GameState;
import sc.plugin2019.Move;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.PlayerColor;

/**
 * @author jan
 *
 */
public class TacticMitte extends Tactic {

	/**
	 * 
	 */
	public TacticMitte() {
		name = Taktikname.MITTE;

	}

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

	
	/*
	Sollte die Funktion hier rein, in Tactic oder villeicht sogar in Logic bleiben?
	Fragen, Fragen, Fragen.
	*/
	private float deconcentrationOfGameState(PlayerColor c, GameState gs) {
		Board b = gs.getBoard();
		float dist = 0;
		for (Field f : GameRuleLogic.getOwnFields(b, c)) {
			float dx = (float) (4.5 - f.getX());
			float dy = (float) (4.5 - f.getY());
			dist += Math.pow((dx * dx + dy * dy), 0.5); // Without Math everything crumbles to pieces
		}
		return dist;
	}

	@Override
	double tacticRatesGameState(GameState gamestate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
