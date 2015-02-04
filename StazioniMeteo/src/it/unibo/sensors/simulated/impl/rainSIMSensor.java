package it.unibo.sensors.simulated.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorSim;
import it.unibo.util.*;

public class rainSIMSensor extends SensorSim {
	public rainSIMSensor(String name, String type) {
		super(name, type);
	}
	
	private List<Measure> measures=new ArrayList();
	private Random r=new Random();
	
	public IntMeasure getSurvey() {	
		takeMeasure();
		return (IntMeasure) measures.get(measures.size()-1);
	}
	
	private void takeMeasure() {
		int temp=(int) (r.nextGaussian() * 10 + 20);
		IntMeasure mes = new IntMeasure(true,temp);
		measures.add(mes);
	}
	
	public static void main(String[] args) {
		rainSIMSensor testSens=new rainSIMSensor("RAINECO", "RL");
		System.out.println("Create new SENSOR name:"+testSens.getName()+" with dataType:"+ testSens.getDatatype());
	    System.out.println("Test simulated measure:"+( testSens.getSurvey()).getValue() );

	}
	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}
}
