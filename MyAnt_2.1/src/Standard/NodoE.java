package Standard;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodoE {
	private String name;
	private int footPrint;
	
	private Map<NodoE, Integer> adiac = new HashMap<NodoE, Integer>();
	private Map<NodoE, Integer> adiacSmell = new HashMap<NodoE, Integer>();	
		public NodoE(String e) {
			setName(e);
			footPrint=0;
			System.out.println("add_node_"+e);
		}

		public NodoE addAdiac(NodoE dest, int dist) {
			adiac.put(dest, dist);
			adiacSmell.put(dest, 0);
			System.out.println("add_Adiac_Node_"+name+"_to_node_"+ dest.getName() + "_distance_" + dist);
			return null;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Map<NodoE, Integer> getAdiac() {
			return adiac;
		}		
		public Map<NodoE, Integer> getSmell() {
			return adiacSmell;
		}
		public int getFootPrint() {
			return footPrint;
		}
		public void setFootPrint(int footPrint) {
			this.footPrint = footPrint;
		}


	}
