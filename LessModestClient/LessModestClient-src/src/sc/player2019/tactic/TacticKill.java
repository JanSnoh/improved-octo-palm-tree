package sc.player2019.tactic;

import java.util.Set;

import sc.plugin2019.Board;
import sc.plugin2019.Field;
import sc.plugin2019.GameState;
import sc.plugin2019.util.GameRuleLogic;


public class TacticKill implements Tactic{

	@Override
	public double tacticRatesGameState(GameState gamestate, GameState oldGameState) {
		Board board = gamestate.getBoard();
		Set<Field> fields = GameRuleLogic.getOwnFields(board,gamestate.getOtherPlayerColor());
		double t = fields.size();
		return 1.0-t/16;
	}
	@Override
	public double getImportance(GameState gamestate) {
		return 0.5;
	}
}
