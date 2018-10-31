import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class SmartBotAGENT extends Agent{
	private Jogo sueca;
	private Jogador player;
	private Behaviour cardDatabase;
	private Behaviour createDataSets;
	public SmartBotAGENT(Jogo sueca, Jogador player) {
		this.sueca=sueca;
		this.player=player;
	};
	/*

	 * An information set represents all the visible information during a game, and also inferred
information based on certain events. The player must keep an instance of the information set per
game and update it when necessary. It stores the known hand of the player and a deck with all
the cards whose owner is unknown. As a result, each time another player plays a card, it should
be removed from that deck.
	 */
	

	@Override
	public void setup() {
		this.addBehaviour(cardDatabase= new CardDatabaseBehaviour(this.sueca,this.player.getPlayerHand()));
	}
	
	
	@Override
	protected void takeDown() {

	}
}
