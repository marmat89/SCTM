package MultiThread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GraphM {
	// HASHMAP used for find note and adiac list
	private Map<String, NodeM> nodes = new HashMap<String, NodeM>();
	private Map<String, Bridge> adiac = new HashMap<String, Bridge>();

	// ADD node to graph
	// TODO existence of a node error
	public NodeM addNodo(String nodeName) {
		NodeM newNode = new NodeM(nodeName);
		nodes.put(nodeName, newNode);
		return newNode;
	}

	// ADD link between node to graph
	// TODO existence of a link error
	public void addLink(String source, String destination, int dist) {
		Bridge br = new Bridge(nodes.get(source), nodes.get(destination), dist);
		adiac.put(source + "" + destination, br);
		nodes.get(source).addAdiac(nodes.get(destination));
		nodes.get(destination).addAdiac(nodes.get(source));
	}

	public NodeM node(String code) {
		return nodes.get(code);
	}

	// AVG smells on node adiac
	public int avgSmells(NodeM sorg) {
		int count = 0;
		Iterator<NodeM> adiacs = sorg.getAdiac().iterator();
		while (adiacs.hasNext()) {
			NodeM dest = adiacs.next();
			count = count + getSmellOf(sorg, dest);
		}
		return (int) count / adiac.size();
	}

	// key of ash map is Node a name + Node b name, when user need key can
	// request node a and node b or node b and node a, is the same thing
	public String getKey(NodeM sorg, NodeM dest) {
		if (adiac.containsKey(sorg.getName() + "" + dest.getName())) {
			return (sorg.getName() + "" + dest.getName());
		}
		if (adiac.containsKey(dest.getName() + "" + sorg.getName())) {
			return (dest.getName() + "" + sorg.getName());
		}
		// if there aren't Node a and Node b link function return null
		return null;
	}

	// INC and DEC smell
	public int incSmellsOf(NodeM sorg, NodeM dest) {
		return adiac.get(getKey(sorg, dest)).setSmell(
				adiac.get(getKey(sorg, dest)).getSmell() + 1);
	}

	public int decSmellOf(NodeM sorg, NodeM dest) {
		if (adiac.get(getKey(sorg, dest)).getSmell()>5){
			return adiac.get(getKey(sorg, dest)).setSmell(
					adiac.get(getKey(sorg, dest)).getSmell() - 5);
		}
		else{
			return adiac.get(getKey(sorg, dest)).setSmell(
					adiac.get(getKey(sorg, dest)).getSmell());
		}
	}

	// UTILITY getter an setter
	public int getSmellOf(NodeM sorg, NodeM dest) {
		return adiac.get(getKey(sorg, dest)).getSmell();
	}

	public int getDist(NodeM source, NodeM destination) {
		return adiac.get(getKey(source, destination)).getDist();
	}

	public Collection<Bridge> getBridges() {
		return adiac.values();
	}

	public int getSize() {
		return nodes.size();
	}

	public Map<String, NodeM> getNodes() {
		return nodes;
	}

	public void setNodes(Map<String, NodeM> nodes) {
		this.nodes = nodes;
	}

	public void bubbleSort(NodeM start) {
		for (int j = 0; j < start.getAdiac().size() - 1; j++) {
			// Se l' elemento j e maggiore del successivo allora //scambiamo
			// i valori
			if ((this.getSmellOf(start, start.getAdiac().get(j))) < (this
					.getSmellOf(start, start.getAdiac().get(j + 1)))) {
				System.out.println("scambio");
				NodeM k = start.getAdiac().get(j);
				start.getAdiac().set(j, start.getAdiac().get(j + 1));
				start.getAdiac().set(j + 1, k);

			}
		}
	}

	
}
