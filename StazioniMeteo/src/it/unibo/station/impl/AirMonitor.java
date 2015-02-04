package it.unibo.station.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.interfaces.Station;
import it.unibo.interfaces.Sensor;
import it.unibo.sensors.impl.*;
import it.unibo.sensors.simulated.impl.*;
import it.unibo.util.*;

public class AirMonitor extends Station {

	public AirMonitor(String name, Coordinate position, int ID) {
		super(name, position, ID);
		System.out.println("BUILD:AirMonitor=>" + this.getName() + " | "
				+ this.getPosition().getLat() + " , "
				+ this.getPosition().getLat());
	}

	private Sensor temperature;
	private Sensor humidity;
	private Sensor speed;
	private Sensor light;
	private Sensor rain;
	private IMeasure lastMes;

	@Override
	public List<Sensor> getSensorList() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(temperature);
		sensors.add(humidity);
		sensors.add(speed);
		sensors.add(light);
		sensors.add(rain);
		return sensors;
	}

	public void addTemperatureSensor(Sensor temp) {
		temperature = temp;
		System.out.println("addTemperatureSensor=>" + temp.getName());
	}

	public void addHumiditySensor(Sensor hum) {
		humidity = hum;
		System.out.println("addHumiditySensor=>" + hum.getName());

	}

	public void addSpeedSensor(Sensor speed) {
		this.speed = speed;
		System.out.println("addSpeedSensor=>" + speed.getName());

	}

	public void addLightSensor(Sensor light) {
		this.light = light;
		System.out.println("addLightSensor=>" + light.getName());

	}

	public void addRainSensor(Sensor rain) {
		this.rain = rain;
		System.out.println("addRainSensor=>" + rain.getName());

	}

	public List<Measure> monitorUpdates() {
		lastMes = temperature.getSurvey();
		if (temperature != null && lastMes != null) {
			mesList.add((FloatMeasure) lastMes);
			System.out.println("UPDATE | "
					+ temperature.getName()
					+ " => "
					+ ((FloatMeasure) mesList.get(mesList.size() - 1))
							.getValue() + ""
					+ mesList.get(mesList.size() - 1).getUOM());
		} else {
			System.err.println("ALLERT | no temperature monitor find");
		}
		lastMes = humidity.getSurvey();
		
		if (humidity != null && lastMes != null) {
			mesList.add((IntMeasure) lastMes);
			System.out.println("UPDATE | " + humidity.getName() + " => "
					+ ((IntMeasure) mesList.get(mesList.size() - 1)).getValue()
					+ "" + mesList.get(mesList.size() - 1).getUOM());
		} else {
			System.err.println("ALLERT | no humidity monitor find");
		}
		lastMes = speed.getSurvey();
		if (speed != null && lastMes != null) {
			mesList.add((FloatMeasure)lastMes);
			System.out.println("UPDATE | "
					+ speed.getName()
					+ " => "
					+ ((FloatMeasure) mesList.get(mesList.size() - 1))
							.getValue());
		} else {
			System.err.println("ALLERT | no speed monitor find");
		}
		lastMes = light.getSurvey();
		if (light != null && lastMes != null) {
			mesList.add((IntMeasure) lastMes);
			System.out.println("UPDATE | " + light.getName() + " => "
					+ ((IntMeasure) mesList.get(mesList.size() - 1)).getValue()
					+ "" + mesList.get(mesList.size() - 1).getUOM());
		} else {
			System.err.println("ALLERT | no light monitor find");
		}
		lastMes = rain.getSurvey();
		if (rain != null && lastMes != null) {
			mesList.add((IntMeasure) lastMes);
			System.out.println("UPDATE | " + rain.getName() + " => "
					+ ((IntMeasure) mesList.get(mesList.size() - 1)).getValue()
					+ "" + mesList.get(mesList.size() - 1).getUOM());
		} else {
			System.err.println("ALLERT | no rain monitor find");
		}

		return mesList;
	}

	public static void main(String[] args) {
		AirMonitor testAir = new AirMonitor("testAirMonitor", new Coordinate(0,
				0), 3);
		System.out.println("Create new STATION name:" + testAir.name);
		testAir.addTemperatureSensor(new TempSensorArduino("DHT11", "TMP"));
		testAir.addHumiditySensor(new HumidSensorArduino("DHT11", "HMD"));
		testAir.addLightSensor(new LightSensorArduino("LDRGL5528", "LGH"));
		testAir.addRainSensor(new RainSensorArduino("RAINECO", "RL"));
		testAir.addSpeedSensor(new SpeedSensorArduino("HC020K", "SPD"));
		testAir.monitorUpdates();
	}
}
