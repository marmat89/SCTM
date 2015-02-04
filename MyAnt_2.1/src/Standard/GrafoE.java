package Standard;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GrafoE{
	// Variabile n = #Nodi
	// Variabile m = #Archi

	private Map<String, NodoE> nodi = new HashMap<String, NodoE>();
	private Map<String, NodoE> nodiForward = new HashMap<String, NodoE>();
	private Map<String, NodoE> nodiReturn= new HashMap<String, NodoE>();	
	
	
	public GrafoE(){
		nodi =  new HashMap<String, NodoE>();	
	}
	
	// Restituisce # Nodi presenti nel Grafo
	public int numNodi() {
		return this.nodi.size();
	}

	public NodoE nodo(String code) {
		return nodi.get(code);
	}

	public NodoE addNodo(String info) {
		NodoE v = new NodoE(info);
		nodi.put(info, v);
		return v;
	}

	public boolean addArco(String sorg, String dest, int dist) {
		nodi.get(sorg).addAdiac(nodi.get(dest), dist);
		nodi.get(dest).addAdiac(nodi.get(sorg), dist);
		return true; 
	}
	
		public Map<NodoE, Integer>  getArcList(String sorg) {
		Map<NodoE, Integer> n = nodi.get(sorg).getAdiac();
		Iterator i = nodi.get(sorg).getAdiac().keySet().iterator();
		Iterator j = nodi.get(sorg).getAdiac().values().iterator();
		System.out.println("adiac_node_of_"+sorg+":");
		while (i.hasNext()){
			System.out.println(((NodoE) i.next()).getName()+ "==dist==>"+j.next());
		}
		return n; 
	}
}
