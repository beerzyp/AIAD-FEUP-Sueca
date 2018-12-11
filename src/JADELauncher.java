

import java.util.ArrayList;
import java.util.List;

import Agents.CortaAGENT;
import Agents.GameAGENT;
import Agents.GreedyAGENT;
import Agents.RandomBotAGENT;
import Agents.SmartBotAGENT;
import GameLogic.Jogo;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class JADELauncher {

	private static final boolean FLAG = false;

	public static void main(String[] args) {
		Runtime rt = Runtime.instance();

		Profile p1 = new ProfileImpl();
		//p1.setParameter(...);
		ContainerController mainContainer = rt.createMainContainer(p1);

		Profile p2 = new ProfileImpl();
		//p2.setParameter(...);
		ContainerController container = rt.createAgentContainer(p2);
		
		Agent randomBotAgent1, randomBotAgent2, randomBotAgent3, randomBotAgent4, 
			SmartBotAgent1, SmartBotAgent2, SmartBotAgent3, SmartBotAgent4, gameAgent,GreedyAGENT,CortaAGENT;

		Jogo sueca = new Jogo();
		
		
		if(FLAG) {
			gameAgent = new GameAGENT(sueca,"randomBotAgent");
		}
		else {
			gameAgent = new GameAGENT(sueca,"SmartBotAgent");
		}
	
		if(FLAG) {
			randomBotAgent1 = new RandomBotAGENT(sueca,sueca.getPlayer1());
			randomBotAgent2 = new RandomBotAGENT(sueca,sueca.getPlayer2());
			randomBotAgent3 = new RandomBotAGENT(sueca,sueca.getPlayer3());
			randomBotAgent4 = new RandomBotAGENT(sueca,sueca.getPlayer4());
		}
		else {
			ArrayList<String> strats = new ArrayList<String>();
			strats.add("GreedyAGENT");
			strats.add("CortaAGENT");
			SmartBotAgent1 = new SmartBotAGENT(sueca,sueca.getPlayer1(),strats);
			SmartBotAgent2 = new SmartBotAGENT(sueca,sueca.getPlayer2(),strats);
			SmartBotAgent3 = new SmartBotAGENT(sueca,sueca.getPlayer3(),strats);
			SmartBotAgent4 = new SmartBotAGENT(sueca,sueca.getPlayer4(),strats);
			GreedyAGENT = new GreedyAGENT(sueca);
			CortaAGENT = new CortaAGENT(sueca);
		}
	
		//Agent NeuralNetworkAGENT = new NeuralNetworkAGENT();
		
		AgentController ac1;

		AgentController ac3;
		try {
			ac3 = mainContainer.acceptNewAgent("myRMA", new jade.tools.rma.rma());
			ac3.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		//		try {
		//			Thread.sleep(10000);
		//		} catch (InterruptedException e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}
		if(FLAG) {
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
			try {
				ac1 = mainContainer.acceptNewAgent("randomBotAgent3", randomBotAgent3);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			try {
				ac1 = mainContainer.acceptNewAgent("randomBotAgent4", randomBotAgent4);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
		

		}
		else {
			AgentController ac2;
			try {
				ac2 = container.acceptNewAgent("gameAgent", gameAgent);
				ac2.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}

			try {
				ac1 = mainContainer.acceptNewAgent("GreedyAGENT", GreedyAGENT);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			try {
				ac1 = mainContainer.acceptNewAgent("CortaAGENT", CortaAGENT);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
	
	
			try {
				ac1 = mainContainer.acceptNewAgent("SmartBotAgent1", SmartBotAgent1);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			try {
				ac1 = mainContainer.acceptNewAgent("SmartBotAgent2", SmartBotAgent2);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			try {
				ac1 = mainContainer.acceptNewAgent("SmartBotAgent3", SmartBotAgent3);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			try {
				ac1 = mainContainer.acceptNewAgent("SmartBotAgent4", SmartBotAgent4);
				ac1.start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
		}

		/*
		try {
			ac1 = mainContainer.acceptNewAgent("NeuralNetworkAGENT", NeuralNetworkAGENT);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		 */
		rt.shutDown();
	}

}
