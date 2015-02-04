package MultiThread;

public class Bridge {
	public NodeM a;
	public NodeM b;
	private int smell;
	private int	dist;
	
	public Bridge(NodeM a,NodeM b,int dist){
		this.a=a;
		this.b=b;
		this.dist=dist;
		this.smell=1;
	}
	public int getDist() {
		return dist;
	}
	public int getSmell() {
		return smell;
	}
	public int setSmell(int smell) {
		return this.smell = smell;
	}
	//TODO conf inc
	public void incSmell() {
		smell=smell+1;
		
	}

	//TODO conf inc
	public void decSmell() {
		smell=smell-1;		
	}
}
