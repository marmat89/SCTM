package it.unibo.interfaces;

import java.util.ArrayList;
import java.util.List;

import it.unibo.util.IMeasure;
import it.unibo.util.Measure;

//Physical sensor implemented on real hardware
public abstract class Sensor implements ISensor {

	// Used to describe a sensor
	protected String name;
	protected static Boolean reliability;
	private String desc;

	// On DB => "Datatype_code"
	protected String datatype;

	//utility var used on the DB
	private float minValue;
	private float maxValue;
	protected List<Measure> measures = new ArrayList();

	//init Sensor with value on the DB
	// on DB => name:"idSen"
	//			Type:"Datatype_code"
	//			reliability: true if use a simulated Sensor
	public Sensor(String name, Boolean reliability, String type) {
		super();
		this.name = name;
		this.reliability = reliability;
		this.datatype = type;
	}

	// GET: sensor name which is also the primary key on DB
	public String getName() {
		return name;
	}

	// GET:controls the response of the sensor
	public Boolean getReliability() {
		return reliability;
	}

	// GET: data type of sensor which is also the key to the DB
	public String getDatatype() {
		return datatype;
	}

}
