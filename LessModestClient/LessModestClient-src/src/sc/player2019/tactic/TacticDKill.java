package sc.player2019.tactic;

import java.util.Set;

import sc.plugin2019.Board;
import sc.plugin2019.Field;
import sc.plugin2019.GameState;
import sc.plugin2019.util.GameRuleLogic;

public class TacticDKill implements Tactic{
	@Override
	public double tacticRatesGameState(GameState gamestate, GameState oldGameState) {
		Board board = gamestate.getBoard();
		Set<Field> fields = GameRuleLogic.getOwnFields(board,gamestate.getOtherPlayerColor());
		double enemyNumber = fields.size();
		
		Board boardOld = oldGameState.getBoard();
		Set<Field> fieldsOld = GameRuleLogic.getOwnFields(boardOld,oldGameState.getCurrentPlayerColor());
		double enemyNumberOld = fieldsOld.size();
		return (enemyNumber < enemyNumberOld)? 1:0;
	}
}
