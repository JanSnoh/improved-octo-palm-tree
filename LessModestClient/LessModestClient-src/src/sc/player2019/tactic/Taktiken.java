package sc.player2019.tactic;

import sc.plugin2019.GameState;

public enum Taktiken {
	
	/*DEFAULT, RANDOM,*/ MITTE(new TacticMitte());//, MINSPREAD;
	
	Tactic tactic;
	Taktiken(Tactic tactic){
		this.tactic = tactic;
	}
	public Tactic getTactic(){
		return tactic;
	}
	public double tacticRatesGameState(GameState gamestate) {
		return tactic.tacticRatesGameState(gamestate);
	}
	
}
