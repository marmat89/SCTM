import java.util.*;

import Standard.Formica;
import Standard.NodoE;

public class AntTH implements Runnable {
	private List<Node> buffer;
	private char target;
	private Node position;	
	public Thread findEat;
	public Ant io;
	private Node lastPosition;

	public void ThreadCreation(Ant ant) {
		buffer = new ArrayList<Node>();
		position = ant.getHome();
		lastPosition=ant.getHome();
		io = ant;
		findEat = new Thread(this, ant.getName());
		System.out.println("Thread creato: " + findEat);
		findEat.start();
	}

	public void run() {
		// TODO setting dinamico target
		while ((position.getName() != "F")) {
			try {
				buffer.add(position);				
				Node temp = position;
				Node nextStep = moveCalc(position,lastPosition);
				lastPosition = temp;
				System.out.println("Ant:" + io.getName() + "go_to"
						+ nextStep.getName() + "_wait_"
						+ ((int) (Math.pow(calcDist(position, nextStep), 2) * 100))+ "_ms");

				// TODO setting dinamico del tempo
				Thread.sleep((long) (Math.pow(calcDist(position, nextStep), 2) * 1000));
				// TODO far morire le formiche
				position = nextStep;
				System.out.println("NewPosition:" + position.getName());

			} catch (InterruptedException e) {
				System.out.println("Gesù_decide_che_La_Formica_è_MORTA");
			}
		}
		if (position.getName() == "F") {
			buffer.add(position);
			System.err.println("Buffer:" + buffer);
			updateRoute(buffer);
			System.err.println("La_formica:_" + io.getName()
					+ "_ha_trovato_da_mangiare");
			buffer.clear();
			
		
		}
		
	}

	public Node moveCalc(Node start,Node prec) {
		int dest;
		do {
		dest = (int) (Math.random() * start.getAdiac().size());
		}
		while(start.getAdiac().get(dest)==prec && start.getAdiac().size()!=1);
		bubbleSort(start.getSmell(), start.getAdiac());
		return start.getAdiac().get(dest);
	}

	private int calcDist(Node source, Node destination) {
		int codeDestination = source.getAdiac().indexOf(destination);
		source.getDist(codeDestination);
		return source.getDist(codeDestination);
	}

	private int calcSmell(Node source, Node destination) {
		List<Node> adiac = source.getAdiac();
		int index = adiac.indexOf(destination);
		System.out.println("calcSmell( "+source.getName()+", "+ destination.getName()+")="+source.getSmellOf(index));
		return source.getSmellOf(index);
	}

	private void updateRoute(List<Node> memory) {
		Iterator<Node> bufferMem = memory.iterator();
		Node sorg =  bufferMem.next();
		while (bufferMem.hasNext()) {
			Node dest = (Node) bufferMem.next();
			List<Node> adiac = sorg.getAdiac();
			int index = adiac.indexOf(dest);
			int newSmell = sorg.setSmellsOf(index);
			System.err.println("setSmellsOf( "+sorg.getName()+"=>"+ adiac.get(index).getName()+" ) = "+newSmell);
			calcSmell(sorg, dest);
			sorg = dest;
		}
	}

	public List<Node> bubbleSort(List<Integer> smells,List<Node> near) {
		System.out.println("");
		List<Integer> smellList = new ArrayList();
		List<Node> adiac = new ArrayList();
		smellList.addAll(smells);
		adiac.addAll(near);
		for (int i = 0; i < smellList.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < smellList.size() - 1; j++) {
				// Se l' elemento j e maggiore del successivo allora //scambiamo
				// i valori
				if ((smellList.get(j)) > (smellList.get(j + 1))) {
					Node kk=adiac.get(j);
					int k = smellList.get(j);
					adiac.set(j, adiac.get(j + 1));
					adiac.set(j + 1, kk);
					smellList.set(j, smellList.get(j + 1));
					smellList.set(j + 1, k);
					System.out.println("SCAMBIO_" + adiac.get(j).getName() + "_con_"
							+ adiac.get(j + 1).getName());
					flag = true; // Lo setto a true per indicare che é avvenuto					// uno scambio }

				}
				if (!flag)
					break; // Se flag=false allora vuol dire che nell' ultima
				// non ci sono stati scambi, quindi il metodo può terminare
				// poiché l' smellList risulta ordinato } }

			}
		}
		
		return adiac;
		
	}

}