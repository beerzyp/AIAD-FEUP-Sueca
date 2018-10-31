import jade.core.behaviours.OneShotBehaviour;

public class CalcScoreRoundBehaviour extends OneShotBehaviour {
	public Jogo sueca;
	public Round round;
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	public CalcScoreRoundBehaviour(Jogo sueca,Round ronda) {
		this.sueca=sueca;
		this.round=ronda;
	}
	@Override
	public void action() {
		//this.calculaPontosRonda(this.round);
		System.out.println("entrou" + ((GameAGENT)this.myAgent).getRondas().size()+ "\n");
	}
	private void calculaPontosRonda(Round round2) {
		// TODO Auto-generated method stub
		
	}

}
