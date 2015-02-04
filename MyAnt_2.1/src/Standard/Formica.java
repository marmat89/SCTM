package Standard;
public class Formica {
	private String name;
	private NodoE home;
	private int lifePoints; 
	private FormicaTH f;

	// ogni formica ha una casa e un nome
	public Formica(String walkerForName, NodoE location, int lp) {
		setName(walkerForName);
		setLifePoints(lp);
		setHome(location);
	}

	public NodoE getHome() {
		return home;
	}

	public void setHome(NodoE location) {
		this.home = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void run() {
			f = new FormicaTH();
			f.ThreadCreation(this);
			f.findEat.start();
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

}
