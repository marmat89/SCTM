

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Standard.NodoE;

public class Graph{
	
	public Map<String, Node> nodes = new HashMap<String, Node>();
	
	public Graph(){
		nodes =  new HashMap<String, Node>();	
	}
	
	public Node addNodo(String info) {
		Node v = new Node(info);
		nodes.put(info, v);
		return v;
	}

	public boolean addArco( String source,String destination,int dist) {
	nodes.get(source).addAdiac(nodes.get(destination), dist);
		nodes.get(destination).addAdiac(nodes.get(source), dist);
		return true; 	
	}
	
		public Object getArcList() {
		
		return null; 
	}
		public Node node(String code) {

			return nodes.get(code);
			
		}
}
