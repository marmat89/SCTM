package MultiThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.*;

public class RandomBrain  extends Brain {

	public NodeM moveCalc(NodeM start, NodeM prec) {
		int dest;
		do {
	
			dest = (rng.nextInt((start.getAdiac().size())));
		
		} while (start.getAdiac().get(dest) == prec
				&& start.getAdiac().size() != 1);
		return start.getAdiac().get(dest);
	}

	

	@Override
	public void updateRoute(List<NodeM> memory) {
		Iterator<NodeM> bufferMem = memory.iterator();
		NodeM sorg = bufferMem.next();
		while (bufferMem.hasNext()) {
			NodeM dest = (NodeM) bufferMem.next();
			List<NodeM> adiac = sorg.getAdiac();
			int index = adiac.indexOf(dest);
			// Iterator<NodeM> i = sorg.getAdiac().iterator();
			// while (i.hasNext()){
			// System.out.println(i.next().getName());
	
			// }
	
			// System.out.println(index);
			int newSmell = g.incSmellsOf(sorg,dest);
			System.err.println("setSmellsOf( " + sorg.getName() + "=>"
					+ adiac.get(index).getName() + " ) = " + newSmell);
			g.getSmellOf(sorg, dest);
			sorg = dest;
		}
	}



	@Override
	public int fun(int n) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getSumSmells(NodeM start) {
		// TODO Auto-generated method stub
		return 0;
	}





}
