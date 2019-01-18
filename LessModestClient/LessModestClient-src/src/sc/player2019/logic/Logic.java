package sc.player2019.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sc.framework.plugins.Player;
import sc.player2019.Starter;
import sc.player2019.tactic.MoveChooser;
import sc.plugin2019.Board;
import sc.plugin2019.Field;
import sc.plugin2019.GameState;
import sc.plugin2019.IGameHandler;
import sc.plugin2019.Move;
import sc.plugin2019.util.GameRuleLogic;
import sc.shared.GameResult;
import sc.shared.InvalidGameStateException;
import sc.shared.InvalidMoveException;
import sc.shared.PlayerColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.lang.Math;

/**
 * Das Herz des Clients: Eine sehr simple Logik, die ihre Zuege zufaellig
 * waehlt, aber gueltige Zuege macht. Ausserdem werden zum Spielverlauf
 * Konsolenausgaben gemacht.
 */
public class Logic implements IGameHandler {

	private Starter client;
	private GameState gameState;
	private Player currentPlayer;

	private static final Logger log = LoggerFactory.getLogger(Logic.class);

	/**
	 * Erzeugt ein neues Strategieobjekt, das zufaellige Zuege taetigt.
	 *
	 * @param client
	 *            Der zugrundeliegende Client, der mit dem Spielserver kommuniziert.
	 */
	public Logic(Starter client) {
		this.client = client;
	}

	/**
	 * {@inheritDoc}
	 */
	public void gameEnded(GameResult data, PlayerColor color, String errorMessage) {
		log.info("Das Spiel ist beendet.");
	}

	/**
	 * {@inheritDoc}
	 */
	//TODO replace with mochooser getMove() or sth. like this
	@Override
	public void onRequestAction() {
		long startTime = System.currentTimeMillis();
		log.info("Es wurde ein Zug angefordert.");
		PlayerColor c = currentPlayer.getColor();
		sendAction(MoveChooser.getBestMove(gameState));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpdate(Player player, Player otherPlayer) {
		currentPlayer = player;
		log.info("Spielerwechsel: " + player.getColor());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpdate(GameState gameState) {
		this.gameState = gameState;
		currentPlayer = gameState.getCurrentPlayer();
		log.info("Zug: {} Spieler: {}", gameState.getTurn(), currentPlayer.getColor());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendAction(Move move) {
		client.sendMove(move);
	}

	// Jan's Funktionen
	//TODO delete
	// Alternative deconcentration Funktion mit einfacherem Code
	@SuppressWarnings("unused")
	private double deconcentrationOfGameState(PlayerColor c, GameState gs) {
		Board b = gs.getBoard();
		double dist = 0;
		for (Field f : GameRuleLogic.getOwnFields(b, c)) {
			double dx = 4.5 - f.getX();
			double dy = 4.5 - f.getY();
			dist += Math.pow((dx * dx + dy * dy),1); // Without Math everything crumbles to pieces
			}
		return dist;
	}

	@SuppressWarnings("unused")
	public static GameState moveToGameState(GameState gs, Move m) {
		GameState g = gs.clone();
		try {
			m.perform(g);
		} catch (InvalidGameStateException | InvalidMoveException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	/**TODO delete
	 * and add wincondition for 30 turns ended.
	 * !!!!!!!!
	 * 
	 * @param gs
	 * @return
	 */
	public static WinState getWinState(GameState gs, PlayerColor c) {
		if(GameRuleLogic.isSwarmConnected(gs.getBoard(), c)) {
			return WinState.WIN;
		} else if(GameRuleLogic.isSwarmConnected(gs.getBoard(), c.opponent())) {
			return WinState.LOOSE;
		}	else {
			return WinState.NEUTRAL;
		}
		
		
	
	}
	
	

}
