import jade.core.behaviours.OneShotBehaviour;

public class CheckWinnerBehaviour extends OneShotBehaviour {
	Jogo sueca;
	Round ronda;
	public CheckWinnerBehaviour(Jogo sueca,Round ronda) {
		this.sueca=sueca;
		this.ronda=ronda;
	}
	@Override
	public void action() {
		int lastRoundWinner=this.sueca.getGameLogic().winner(ronda, sueca);
		System.out.println("VENCEDOR RONDA: player"+ lastRoundWinner+"\n");
	}

}
