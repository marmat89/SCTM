package MultiThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;


public class AnthillM implements Runnable {

	public NodeM location; // HOME POSITION
	public NodeM food; // FOOD POSITION
	public int antsCount; // ACTIVE ANTS ON THE GRAPH
	public int finderCount; // CONDITION VARIABLE
	public Thread antManager; // MANAGER THREAD FOR ANT
	public GraphM allNodes; // NODE LIST
	private int NUMATT;// NUMBER ATTEMPT
	public  HashMap<String, JLabel> list;//FOR GUI
	private Configurator c;
	private Brain br;
	
	// NEED: HOME NODE, # OF ANTS, LIST OF NODE
	public AnthillM(NodeM node, NodeM food, int antsCount, GraphM g, HashMap<String, JLabel> list) {
		// simple assignment
		this.list=list;//GUI
		
		this.location = node;
		this.allNodes = g;
		this.antsCount = antsCount;
		this.food = food;
		c = new Configurator();
		br=c.getBrain();
		NUMATT=c.getTagValueINT("NUMBER_ATTEMPT");
		br.initBR(g);
		// Thread START
		antManager = new Thread(this, "Gestore");
		//System.out.println("Thread creato: " + antManager);
		antManager.start();
	}

	// thread start
	@Override
	public void run() {
		finderCount = 0;
		int i = 0;		
		// TODO limitare il numero di casi
		while (i < NUMATT) {
			if (finderCount < antsCount) {
				i++;
				finderCount++;
				String walkerForName = "ants_#" + i;
				AntM walkerFor = new AntM(walkerForName, this.location,
						allNodes.getNodes());
				AntTH a = new AntTH();
				a.ThreadCreation(walkerFor, this,br);
			} else {
				//System.out.println("Buffer_Ant:----->FULL");
				synchronized (this.antManager) {
					try {
						antManager.wait();
						//System.out.println("Buffer_Ant:----->FREE");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		while (antManager.getThreadGroup().activeCount()>c.getTagValueINT("ERROR_NOT_STOP")) {
			synchronized (this.antManager) {
				try {
					System.out.println("COUNT:"+antManager.getThreadGroup().activeCount()+"ACTIVE"
							);
					antManager.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		
		antManager.getThreadGroup().list();
		System.out.println("COUNT:"+antManager.getThreadGroup().activeCount());
	}
}
