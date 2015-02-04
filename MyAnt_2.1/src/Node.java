import java.util.*;

public class Node {
	private String name;
	private List<Node> adiac;
	private List<Integer> dist;
	private List<Integer> smells;

	public Node(String e) {
		setName(e);
		System.out.println("add_node_" + e);
		adiac = new ArrayList<Node>();
		dist = new ArrayList<Integer>();
		smells = new ArrayList<Integer>();
	}

	public Node addAdiac(Node dest, int distance) {
		adiac.add(dest);
		dist.add(distance);
		smells.add (0);
		System.out.println("addAdiac( " + name + " , "
				+ dest.getName() + ") = " + dist.get(dist.size()-1));
		return null;
	}
	public List<Node> getAdiac() {
		return adiac;
	}	
	public int getDist(int i) {
		//System.out.println("getDistance("+ this.getName()+" , "+this.adiac.get(i).getName()+") = "+dist.get(i));
		return dist.get(i);
	}
	public List<Integer> getSmell() {
		//TODO
		return smells;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer setSmellsOf(int index) {
		int newSmell=smells.get(index)+1;
		smells.set(index,newSmell );
		return smells.get(index);
	}

	public int getSmellOf(int index) {
		return smells.get(index);
	}
	public void allNodeInfo() {
		System.out.println("Node:"+ this.getName());
		System.out.println("Adiacenti:"+ adiac);
		System.out.println("Smells:"+ smells);
		System.out.println("Distanze:"+ dist);
		
	}

	/*
	public void setAdiac(List<Node> adiac) {
		this.adiac = adiac;
	}

	public List<Integer> getDist() {
		return dist;
	}

	public void setDist(List<Integer> dist) {
		this.dist = dist;
	}

	public List<Integer> getSmells() {
		return smells;
	}

	
*/
}
