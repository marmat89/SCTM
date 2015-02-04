package it.unibo.station.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.interfaces.Station;
import it.unibo.interfaces.Sensor;
import it.unibo.sensors.simulated.impl.tempSIMSensor;
import it.unibo.util.*;

public class WaterMonitor extends Station {

	public WaterMonitor(String name, Coordinate position,int ID) {
		super(name, position, ID);
		this.name = name;
		this.position = position;
		System.out.println("BUILD:AirMonitor=>" + this.getName() + " | "
				+ this.getPosition().getLat() + " , "
				+ this.getPosition().getLat());
	}
	
	private Sensor temperature;
	private Sensor level;
	private Sensor speed;


	@Override
	public List<Sensor> getSensorList() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(temperature);
		sensors.add(level);
		sensors.add(speed);
		return sensors;
	}

	public void addTemperatureSensor(Sensor temp) {
		temperature = temp;
		System.out.println("addTemperatureSensor=>" + temp.getName());
	}

	public void addLevelSensor(Sensor level) {
		this.level = level;
		System.out.println("addLevelSensor=>" + level.getName());
	}

	public void addSpeedSensor(Sensor speed) {
		this.speed = speed;
		System.out.println("addSpeedSensor=>" + speed.getName());
	}

	public List<Measure> monitorUpdates() {
		mesList = new ArrayList();
		if (temperature != null) {
			mesList.add((FloatMeasure) temperature.getSurvey());
			System.out.println("UPDATE | "
					+ temperature.getName()
					+ " => "
					+ ((FloatMeasure) mesList.get(mesList.size() - 1))
							.getValue() + ""
					+ mesList.get(mesList.size() - 1).getUOM());
		} else {
			System.err.println("ALLERT | no temperature monitor find");
		}
		if (level != null) {
			mesList.add((IntMeasure) level.getSurvey());
			System.out.println("UPDATE | "
					+ level.getName()
					+ " => "
					+ ((IntMeasure) mesList.get(mesList.size() - 1))
							.getValue() + ""
					+ mesList.get(mesList.size() - 1).getUOM());
		} else {
			System.err.println("ALLERT | no level monitor find");
		}
		if (speed != null) {
			mesList.add((FloatMeasure) speed.getSurvey());
			System.out.println("UPDATE | "
					+ speed.getName()
					+ " => "
					+ ((FloatMeasure) mesList.get(mesList.size() - 1))
							.getValue());
		} else {
			System.err.println("ALLERT | no speed monitor find");
		}
		return mesList;
	}

	public static void main(String[] args) {
		WaterMonitor testGND = new WaterMonitor("testGroundMonitor",
				new Coordinate(0, 0), 1);
		System.out.println("Create new STATION name:" + testGND.name);
		testGND.addTemperatureSensor(new tempSIMSensor("DS18B20", "TMP"));
		testGND.monitorUpdates();
	}
	
}
