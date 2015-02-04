package it.unibo.sensors.simulated.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorSim;
import it.unibo.util.FloatMeasure;
import it.unibo.util.Measure;

public class tempSIMSensor extends SensorSim {
	public tempSIMSensor(String name, String type) {
		super(name, type);
		
	}
	private List<Measure> measures=new ArrayList();
	private Random r=new Random();
	
	public FloatMeasure getSurvey() {	
		takeMeasure();
		return (FloatMeasure) measures.get(measures.size()-1);
	}
	
	private void takeMeasure() {
		float temp=(float) (r.nextGaussian() * 10 + 20);
		FloatMeasure mes = new FloatMeasure(true,temp);
		measures.add(mes);
	}
	
	
	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		tempSIMSensor testSens=new tempSIMSensor("DS18B20", "TMP");
		System.out.println("Create new SENSOR name:"+testSens.getName()+" with dataType:"+ testSens.getDatatype());
	    System.out.println("Test simulated measure:"+( testSens.getSurvey()).getValue() );

	}
}
