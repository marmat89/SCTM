package Standard;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Nido {
	public NodoE location;
	public List<Formica> formiche;
	public Nido(NodoE nodoE, int nFormiche) {
		location = nodoE;
		formiche = new ArrayList<Formica>();
		for (int i=0; i<nFormiche; i++){
			String walkerForName = "ants_#"+i;
			Formica walkerFor = new Formica(walkerForName, this.location, 10);
			formiche.add(walkerFor);
			
		}
	}
	public void startFind(){
		Iterator i = formiche.iterator();
		while(i.hasNext()) {
		    ((Formica) i.next()).run(); 
	      }
	}
}
