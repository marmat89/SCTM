import java.util.Random;

import org.uncommons.maths.*;
import org.uncommons.maths.random.ExponentialGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import Standard.GrafoE;
import Standard.Nido;


public class main {

	/**
	 * @param args
	 * @throws EccezioneNodoRadice
	 * @throws EccezioneRadiceEsistente
	 * @throws EccezioneStrutturaVuota
	 * @throws EccezioneNodo
	 * @throws EccezioneNullReferenceNodo
	 * @throws NullObjectAssigned
	 * 
	 */
	public static void main(String[] args) {
	
		Graph g = new Graph();
		g.addNodo("A");
		g.addNodo("B");
		g.addNodo("C");
		g.addNodo("D");
		g.addNodo("E");
		g.addNodo("F");

		g.addArco("A", "B", 1);
		g.addArco("C", "E", 1);
		g.addArco("E", "F", 1);
		g.addArco("B", "C", 1);
		g.addArco("B", "D", 4);
		g.addArco("D", "E", 4);
		
		Anthill n = new Anthill(g.node("A"),2,g.nodes);
	

	n.startFind();
	}
}
