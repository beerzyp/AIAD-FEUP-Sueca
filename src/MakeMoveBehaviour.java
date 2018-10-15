import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class MakeMoveBehaviour extends Behaviour{
	private Round ronda;
	private Mao hand;
	public MakeMoveBehaviour(Round round,Mao agentHand) {
		ronda=round;
		hand=agentHand;
	}
	@Override
	public void action() {
		final ACLMessage request= this.myAgent.blockingReceive();
		Jogo suecada=null;
		try {
			suecada=(Jogo) request.getContentObject();
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(suecada.getMao1().getMao().size());
		//SEND PLAY TO LOGIC
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		inform.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Jogada");
		inform.setContent("A-Copas");
		this.myAgent.send(inform);
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
