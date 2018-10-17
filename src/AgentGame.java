import jade.core.Agent;

public class AgentGame extends Agent{
	Jogo suecaGame;
	public AgentGame(Jogo sueca) {
		this.suecaGame=sueca;
	}
	public void setup() {
		System.out.println("entrou");
		addBehaviour(new AskForPlayerMove(this.suecaGame));

	}
	
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
