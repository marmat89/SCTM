package it.unibo.sensors.simulated.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorSim;
import it.unibo.util.*;

public class tiltSIMSensor extends SensorSim {
	public tiltSIMSensor(String name, String type) {
		super(name, type);
	}
	private List<Measure> measures=new ArrayList();
	private Random r=new Random();
	
	public BooleanMeasure getSurvey() {	
		takeMeasure();
		return (BooleanMeasure) measures.get(measures.size()-1);
	}
	
	private void takeMeasure() {
		BooleanMeasure mes = new BooleanMeasure(true,false);
		measures.add(mes);
	}
	
	public static void main(String[] args) {
		tiltSIMSensor testSens=new tiltSIMSensor("TLTECO", "ALL");
		System.out.println("Create new SENSOR name:"+testSens.getName()+" with dataType:"+ testSens.getDatatype());
	    System.out.println("Test simulated measure:"+( testSens.getSurvey()).getValue() );

	}
	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}
}
