package MultiThread;

import java.util.List;
import java.util.Random;

public abstract class Brain {
	protected Random rng;
	protected GraphM g;

	public void initBR(GraphM allNodes) {
		this.g = allNodes;
		rng = new Random();
	}

	public int calcDist(NodeM source, NodeM destination) {
		return g.getDist(source, destination);
	}

	public long calcSleep(NodeM p1, NodeM p2, int time) {
		// TODO setting dinamico del tempo
		return (long) (Math.pow(g.getDist(p1, p2), 3) * time);
	}

	public abstract NodeM moveCalc(NodeM start, NodeM prec);

	public abstract void updateRoute(List<NodeM> memory);
	public abstract int fun(int n);
	public abstract int getSumSmells(NodeM start) ;
}