package sc.player2019.util;

import sc.framework.plugins.Player;
import sc.plugin2019.GameState;
import sc.plugin2019.util.Constants;
import sc.shared.PlayerColor;
import sc.shared.WinCondition;
import static sc.plugin2019.util.GameRuleLogic.isSwarmConnected;


public abstract class CheckWinCondition {

	  public static WinCondition checkWinCondition(GameState gameState) {
		    // TODO check whether this is right
		    int[][] stats = gameState.getGameStats();
		    if (gameState.getTurn() % 2 == 1) {
		      return null;
		    }
		    if (gameState.getTurn() < 2 * Constants.ROUND_LIMIT) {
		      // round limit not reached
		      Player winningPlayer = getWinner(gameState);
		      if (winningPlayer != null) {
		        return new WinCondition(winningPlayer.getColor(), Constants.WINNING_MESSAGE);
		      } else {
		        return null;
		      }
		    } else {
		      // round limit reached
		      Player winningPlayer = getWinner(gameState);
		      if (winningPlayer != null) {
		        return new WinCondition(winningPlayer.getColor(), Constants.WINNING_MESSAGE);
		      } else {
		        PlayerColor winner;
		        if (stats[Constants.GAME_STATS_RED_INDEX][Constants.GAME_STATS_SWARM_SIZE] > stats[Constants.GAME_STATS_BLUE_INDEX][Constants.GAME_STATS_SWARM_SIZE]) {
		          winner = PlayerColor.RED;
		        } else if (stats[Constants.GAME_STATS_RED_INDEX][Constants.GAME_STATS_SWARM_SIZE] < stats[Constants.GAME_STATS_BLUE_INDEX][Constants.GAME_STATS_SWARM_SIZE]) {
		          winner = PlayerColor.BLUE;
		        } else {
		          winner = null;
		        }
		        return new WinCondition(winner, Constants.ROUND_LIMIT_MESSAGE);
		      }
		    }
		  }

		  static Player getWinner(GameState gameState) {
		    if (isSwarmConnected(gameState.getBoard(), PlayerColor.RED)) {
		      if (isSwarmConnected(gameState.getBoard(), PlayerColor.BLUE)) {
		        if (gameState.getPointsForPlayer(PlayerColor.RED) > gameState.getPointsForPlayer(PlayerColor.BLUE)) {
		          return gameState.getPlayer(PlayerColor.RED);
		        } else if (gameState.getPointsForPlayer(PlayerColor.RED) < gameState.getPointsForPlayer(PlayerColor.BLUE)) {
		          return gameState.getPlayer(PlayerColor.BLUE);
		        } else {
		          return null;
		        }
		      }
		      return gameState.getPlayer(PlayerColor.RED);
		    } else {
		      if (isSwarmConnected(gameState.getBoard(), PlayerColor.BLUE)) {
		        return gameState.getPlayer(PlayerColor.BLUE);
		      }
		    }
		    return null;
		  }
}
