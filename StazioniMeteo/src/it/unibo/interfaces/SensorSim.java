package it.unibo.interfaces;

import it.unibo.util.IMeasure;

public abstract class SensorSim extends Sensor{

	public SensorSim(String name, String type) {
		super(type, reliability, type);
		this.name = name;
		this.reliability = false;
		this.datatype = type;

	}

	public String getName() {
		return name;
	}

	public Boolean getReliability() {
		return reliability;
	}

	public IMeasure getSurvey() {
		return null;
	}

	public String getDatatype() {
		return datatype;
	}
}
