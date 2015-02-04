import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Standard.FormicaTH;

public class Ant {
	private String name;
	private Node home;
	private int lifePoints; 
	private AntTH f;
	//TODO eliminare
	public Map<String, Node> nodes = new HashMap<String, Node>();
	
	//COSTRUTTORE
	public Ant(String walkerForName, Node location, int lp, Map<String, Node> nodes) {
		setName(walkerForName);
		setLifePoints(lp);
		setHome(location);
		//TODO eliminare
		this.nodes=nodes;
		lifePoints=10;
		
	}

	public Node getHome() {
		return home;
	}

	public void setHome(Node location) {
		this.home = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void run() {

			f = new AntTH();
			f.ThreadCreation(this);
			if(!f.findEat.isAlive()){
				Iterator<Node> bufferMem = (Iterator<Node>) nodes.values().iterator();
				Node sorg =  bufferMem.next();
				while (bufferMem.hasNext()) {
					bufferMem.next().allNodeInfo();
					
				}
				
				}

	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

}
