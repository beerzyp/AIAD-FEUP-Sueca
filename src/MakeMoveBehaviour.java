import java.util.Random;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class MakeMoveBehaviour extends Behaviour{
//	private Round ronda;
//	private Mao hand;
	private Jogo sueca;
	private int player;
	public MakeMoveBehaviour(Jogo sueca,int player) {
		this.sueca=sueca;
		this.player=player;
	}
	@Override
	public void action() {
		final ACLMessage request= this.myAgent.blockingReceive();
		
		String message = request.getContent();
		
		System.out.println(message);
		//SEND PLAY TO LOGIC
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		inform.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Jogada");
		String cartaValue=this.returnPLay().toString();
		inform.setContent(cartaValue);
		this.myAgent.send(inform);
		System.out.println("agent " + this.myAgent.getAID().getLocalName() + "\n");
	}
	
	public Carta returnPLay() {
		Random r = new Random();
		int Low = 0;
		int High = 9;
		int Result = r.nextInt(High-Low) + Low;
		switch(this.player) {
		case 1:
			High = this.sueca.getPlayer1().getPlayerHand().getMao().size() - 1;
			Result = r.nextInt(High-Low) + Low;
			return this.sueca.getPlayer1().getPlayerHand().getCartaAt(Result);
		case 2:
			High = this.sueca.getPlayer2().getPlayerHand().getMao().size() - 1;
			Result = r.nextInt(High-Low) + Low;
			return this.sueca.getPlayer2().getPlayerHand().getCartaAt(Result);
		case 3:
			High = this.sueca.getPlayer3().getPlayerHand().getMao().size() - 1;
			Result = r.nextInt(High-Low) + Low;
			return this.sueca.getPlayer3().getPlayerHand().getCartaAt(Result);
		case 4:
			High = this.sueca.getPlayer4().getPlayerHand().getMao().size() - 1;
			Result = r.nextInt(High-Low) + Low;
			return this.sueca.getPlayer4().getPlayerHand().getCartaAt(Result);
		
		default:
			return null;
		}
	}
	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
