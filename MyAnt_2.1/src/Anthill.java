

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Standard.Formica;

public class Anthill {
	public Node location;
	public List<Ant> ants;
	public Map<String, Node> nodes = new HashMap<String, Node>();
	
	public Anthill(Node node, int antsCount, Map<String, Node> nodes) {
		location = node;
		this.nodes=nodes;
		ants = new ArrayList<Ant>();
		// crea # formiche
		for (int i=0; i<antsCount; i++){
			String walkerForName = "ants_#"+i;
			// TODO possibilità di scegliere quanta vita
			Ant walkerFor = new Ant(walkerForName, this.location, 4,nodes);
			
			ants.add(walkerFor);
			
		}
	}
	
	public void startFind(){
		Iterator i = ants.iterator();
		while(i.hasNext()) {
			((Ant) i.next()).run(); 		
		}
		
	}
}
