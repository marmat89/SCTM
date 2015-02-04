package MultiThread;
import java.util.*;
public class NodeM {
	private String name;
	private List<NodeM> adiac;

	// Need node Name, and init adiac Array LIST
	public NodeM(String name) {
		this.name = name;
		System.out.println("add_node_" + this.name);
		adiac = new ArrayList<NodeM>();
	}

	//update adiac list
	public NodeM addAdiac(NodeM dest) {
		adiac.add(dest);
		System.out.println("addAdiac( " + name + " , " + dest.getName() + ")");
		return dest;
	}
	
	//UTILITY GETTER
	public List<NodeM> getAdiac() {
		return adiac;
	}
	public String getName() {
		return name;
	}
}
