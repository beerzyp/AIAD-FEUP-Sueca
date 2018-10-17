import jade.core.Agent;

public class AgentGame extends Agent{
	Jogo suecaGame;
	public AgentGame(Jogo sueca) {
		this.suecaGame=sueca;
		System.out.println("TRUNFO: " + this.suecaGame.getTrunfo().toString() + "\n\n");
	}
	public void setup() {
		addBehaviour(new AskForPlayerMove(this.suecaGame));

	}
	
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
