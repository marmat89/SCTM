package MultiThread;

import java.util.Iterator;
import java.util.List;

public class LinearBrain extends Brain {

	@Override
	public NodeM moveCalc(NodeM start, NodeM prec) {
		int dest;
		int i = 0;
		if (start.getAdiac().size() != 0) {
			do {
				double div=(100/(double)getSumSmells(start));
				dest = (rng.nextInt(100));
				i = 0;
				boolean notfind = true;
				while (notfind) {				
					double choice = fun(g.getSmellOf(start, start.getAdiac().get(i)))*div;
					System.out.println("choice" + dest + "to" + choice);
					if (choice > dest) {
						dest = i;
						notfind = false;
					} else {
						dest = (int) (dest - choice);
						i++;
					}
				}
			} while (start.getAdiac().get((int) dest) == prec
					&& start.getAdiac().size() != 1);
		} else {
			dest = 0;
		}
		return start.getAdiac().get((int) dest);
	}

	@Override
	public void updateRoute(List<NodeM> memory) {
		Iterator<NodeM> bufferMem = memory.iterator();
		NodeM sorg = bufferMem.next();
		while (bufferMem.hasNext()) {
			NodeM dest = (NodeM) bufferMem.next();
			List<NodeM> adiac = sorg.getAdiac();
			int index = adiac.indexOf(dest);
			int newSmell = g.incSmellsOf(sorg, dest);
			System.err.println("setSmellsOf( " + sorg.getName() + "=>"
					+ adiac.get(index).getName() + " ) = " + newSmell);
			g.getSmellOf(sorg, dest);
			sorg = dest;
		}
	}


	@Override
	public int fun(int n) {
		return n;
	}

	@Override
	public int getSumSmells(NodeM start) {
		int count = 0;
		Iterator<NodeM> adiac = start.getAdiac().iterator();
		while (adiac.hasNext()) {
			count = count + fun(g.getSmellOf(start, adiac.next()));
		}
		return count;
	}
}
