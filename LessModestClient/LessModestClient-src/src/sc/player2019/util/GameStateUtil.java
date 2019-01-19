package sc.player2019.util;

import sc.plugin2019.GameState;
import sc.plugin2019.Move;
import sc.shared.InvalidGameStateException;
import sc.shared.InvalidMoveException;

public class GameStateUtil {
	public static GameState moveToGameState(GameState gs, Move m) {
		GameState g = gs.clone();
		try {
			m.perform(g);
			
		} catch (InvalidGameStateException | InvalidMoveException e) {
			e.printStackTrace();
		}
		return g;
	}

}
