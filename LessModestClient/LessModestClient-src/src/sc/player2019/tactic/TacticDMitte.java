package sc.player2019.tactic;

import sc.plugin2019.Board;
import sc.plugin2019.Field;
import sc.plugin2019.GameState;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.PlayerColor;

public class TacticDMitte implements Tactic{

	@Override
	public double tacticRatesGameState(GameState gamestate, GameState oldGameState) {
		Board boardNew = gamestate.getBoard();
		Board boardOld = oldGameState.getBoard();
		double distNew = 0.0;
		double distOld = 0.0;
		PlayerColor me = gamestate.getCurrentPlayerColor();
		
		assert !(boardNew.equals(boardOld));
		
		
		for (Field f : GameRuleLogic.getOwnFields(boardNew, me)) {
			double dx = (double) (4.5 - f.getX());
			double dy = (double) (4.5 - f.getY());
			///distNew += dx+dy;
			distNew += Math.pow((dx * dx + dy * dy), 0.5);
		}
		
		for (Field f : GameRuleLogic.getOwnFields(boardOld, me)) {
			double dx = (double) (4.5 - f.getX());
			double dy = (double) (4.5 - f.getY());
			//distOld += dx + dy;
			distOld += Math.pow((dx * dx + dy * dy), 0.5);
		}
		//assert (distOld-distNew<=7);
		System.out.println("distOld: "+distOld+"\n distNew:"+distNew);
		return (distOld-distNew);
	}
}
