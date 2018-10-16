import jade.core.Agent;

public class AgentGame extends Agent{
	Jogo suecaGame;
	public AgentGame(Jogo sueca) {
		this.suecaGame=sueca;
	}
	public void setup() {
		System.out.println("entrou");
		addBehaviour(new AskForPlayerMove(this.suecaGame));
		suecaGame = new Jogo();
//		System.out.println("chegou" + suecaGame.getMao1().getMao().size());
//		System.out.println(this.getAID().getLocalName());
	}
	
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
