package MultiThread;

import java.util.*;

import javax.swing.JLabel;

import Standard.Formica;
import Standard.NodoE;

public class Drier implements Runnable {
	// TODO
	private Thread dry;
	private AnthillM hill;
	private int TIME;

	public void ThreadCreation(AnthillM hill) {
		this.hill = hill;

		// get DRYING RATE from conf file
		Configurator c = new Configurator();
		TIME = c.getTagValueINT("DRYING_RATE");

		// THREAD finder Start
		dry = new Thread(this, "drier");
		dry.start();
	}

	public void run() {
		// drier run until run antManager
		while (hill.antManager.isAlive()) {
			try {
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator<Bridge> bridge = hill.allNodes.getBridges().iterator();
			while (bridge.hasNext()) {
				Bridge currentBr = bridge.next();
				String s = hill.allNodes.getKey(currentBr.a, currentBr.b);
				//used for Update GUI
				hill.list.get(s).setText(
								"Node "
								+ currentBr.a.getName()
								+ "<=="
								+ hill.allNodes.decSmellOf(currentBr.a,
										currentBr.b) + "==>" + "Node "
								+ currentBr.b.getName());
			}
		}
	}
}
