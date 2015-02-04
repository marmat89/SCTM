package MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AntM {
	private String name;
	private NodeM home;
	private int lifePoints; 
	private NodeM position;
	private NodeM lastPosition;
	private List<NodeM> buffer;
	private Brain brain;
	//COSTRUTTORE
	public AntM(String walkerForName, NodeM location, Map<String, NodeM> nodes) {
		setName(walkerForName);
		setHome(location);
		buffer = new ArrayList<NodeM>();
	}
	//for simplify thread work
	public void setStart(int lp){
		position=home;
		lastPosition=home;

		Configurator c = new Configurator();
		int life = c.getTagValueINT("LIFE_POINT");
		if(life==0){
			lifePoints=lp-1;
		}else{
			lifePoints=life;
		}
	}
	public NodeM getHome() {
		return home;
	}
	
	public void setHome(NodeM location) {
		this.home = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public NodeM getPosition() {
		return position;
	}

	public void setPosition(NodeM position) {
		this.position = position;
	}
	public NodeM getLastPosition() {
		return lastPosition;
	}
	public void setLastPosition(NodeM lastPosition) {
		this.lastPosition = lastPosition;
	}
	public List<NodeM> getBuffer() {
		return buffer;
	}
	public void setBuffer(List<NodeM> buffer) {
		this.buffer = buffer;
	}
	public void moveStep() {
		lifePoints--;
		buffer.add(position);
	}
	public boolean dieAnt() {
		if (lifePoints>0){
			return false;
		}else{
			return true;
		}
	}
}
