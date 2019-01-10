package sc.player2019.tactic;

public enum Taktiken {
	
	/*DEFAULT, RANDOM,*/ MITTE(new TacticMitte());//, MINSPREAD;
	
	Tactic tactic;
	Taktiken(Tactic tactic){
		this.tactic = tactic;
	}
	public Tactic getTactic(){
		return tactic;
	}
	
}
