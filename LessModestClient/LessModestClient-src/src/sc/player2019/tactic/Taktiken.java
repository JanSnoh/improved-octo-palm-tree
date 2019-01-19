package sc.player2019.tactic;

import sc.plugin2019.GameState;

public enum Taktiken {
	
	/*DEFAULT, RANDOM,*/MITTE(new TacticDMidde());//, MITTE(new TacticDMidde());//, MINSPREAD;
	
	Tactic tactic;
	double importance;
	
	Taktiken(Tactic tactic){
		this.tactic = tactic;;
	}
	public Tactic getTactic(){
		return tactic;
	}
	public double tacticRatesGameState(GameState gamestate, GameState old) {
		return tactic.tacticRatesGameState(gamestate, old);
	}
	
	public double getImportance(GameState gamestate) {
		return tactic.getImportance(gamestate);
	}
	
	
}
