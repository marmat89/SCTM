package it.unibo.sensors.simulated.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorSim;
import it.unibo.util.*;

public class fanspeedSIMSensor extends SensorSim {
	public fanspeedSIMSensor(String name, String type) {
		super(name, type);
	}
	
	private List<Measure> measures=new ArrayList();
	private Random r=new Random();
	
	public FloatMeasure getSurvey() {	
		takeMeasure();
		return  (FloatMeasure) measures.get(measures.size()-1);
	}
	
	private void takeMeasure() {
		int temp=(int) (r.nextGaussian() * 10 + 20);
	    FloatMeasure mes = new FloatMeasure(true,temp);
		measures.add(mes);
	}
	
	public static void main(String[] args) {
		fanspeedSIMSensor testSpeedSen=new fanspeedSIMSensor("HC020K","SPD");
		System.out.println("Create new SENSOR name:"+testSpeedSen.getName()+" with output Data Type:"+ testSpeedSen.getDatatype());
	    System.out.println("Test simulated measure:"+ (float) (testSpeedSen.getSurvey()).getValue());

	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

}