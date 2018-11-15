package sc.player2019.tactic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sc.plugin2019.GameState;
import sc.plugin2019.Move;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.PlayerColor;

/**
 * Does main calculation to choose a Move
 * @author kajus
 * @since 15.11.18 
 * @version 1
 */
public class MoveChooser {
	static Map tacticsAndImportance = new HashMap();
	//static double[] importance;
	//static Tactic[] tactics;

	//TODO well you know, like, code it.
	static Move getBestMove(GameState gamestate) {
		return null;
	}

	/*
	public static double[] evaluateMoves(GameState gs, PlayerColor c){
		ArrayList<Move> possibleMoves = GameRuleLogic.getPossibleMoves(gs);
		double[] indecency = new double[possibleMoves.size()];
		    for (int i=0; i<possibleMoves.size(); i++){
		       GameState copy = Tactic.moveToGameState(gs, possibleMoves.get(i));
		      for(int j=0; j < tactics.length;j++){
		        indecency[i]+= importance[j] * tactics[j].evaluateGS(copy, c);
		      }
		    }
		    return indecency;
		    
		    
		  }
	*/
	
	public static void updateImportance() {
		
	}
	
}
