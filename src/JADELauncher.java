

import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class JADELauncher {

	public static void main(String[] args) {
		Runtime rt = Runtime.instance();

		Profile p1 = new ProfileImpl();
		//p1.setParameter(...);
		ContainerController mainContainer = rt.createMainContainer(p1);
		
		Profile p2 = new ProfileImpl();
		//p2.setParameter(...);
		ContainerController container = rt.createAgentContainer(p2);

		Jogo sueca = new Jogo();
		Agent randomBotAgent1 = new RandomBotAgent(sueca);
		Agent randomBotAgent2 = new RandomBotAgent(sueca);
//		Agent randomBotAgent3 = new RandomBotAgent(sueca);
//		Agent randomBotAgent4 = new RandomBotAgent(sueca);
		Agent gameAgent = new AgentGame(sueca);
		AgentController ac1;

		Object[] agentArgs = new Object[0];
		AgentController ac2;
		try {
			ac2 = container.acceptNewAgent("gameAgent", gameAgent);
			ac2.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		try {
			ac1 = mainContainer.acceptNewAgent("randomBotAgent1", randomBotAgent1);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		try {
			ac1 = mainContainer.acceptNewAgent("randomBotAgent2", randomBotAgent2);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

		AgentController ac3;
		try {
			ac3 = mainContainer.acceptNewAgent("myRMA", new jade.tools.rma.rma());
			ac3.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

	}

}
