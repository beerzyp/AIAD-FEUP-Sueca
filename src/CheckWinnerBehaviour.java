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
		
		System.out.println("\nJogador 1:" + sueca.getPlayer1().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer1().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer1().getPlayerHand().getMao().get(i).toString());
			
		}
		System.out.println("\nJogador 2:" + sueca.getPlayer2().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer2().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer2().getPlayerHand().getMao().get(i).toString());
			
		}
		System.out.println("\nJogador 3:" + sueca.getPlayer3().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer3().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer3().getPlayerHand().getMao().get(i).toString());
			
		}
		System.out.println("\nJogador 4:" + sueca.getPlayer4().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer4().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer4().getPlayerHand().getMao().get(i).toString());
			
		}
	}

}
