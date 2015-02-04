package it.unibo.sensors.impl;

import java.util.Observable;
import java.util.Observer;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorArduino;
import it.unibo.sensors.simulated.impl.rainSIMSensor;
import it.unibo.sensors.simulated.impl.tempSIMSensor;
import it.unibo.util.AttendRispThread;
import it.unibo.util.FloatMeasure;
import it.unibo.util.IMeasure;
import it.unibo.util.IntMeasure;
import it.unibo.util.SerialCom;

public class LevelSensorArduino extends SensorArduino {

	public LevelSensorArduino(String name, String type) {
		super(name, type);

	}

	// All physical sensors must implement getSurvey ()
	// Depending on the type of data that is required
	// have all the same iter: OPEN port => SEND request => GET answer
	@Override
	public synchronized IMeasure getSurvey() {
		// arduino serial com setup
		if (initSerialCom(this)) {
			// keyword arduino request
			arduino.sendData("getLevel=");
			// used for attend sensor respond for N seconds
			attendSerialCom();
			return getIntValue();
		} else
			return null;

	}

	public static void main(String[] args) {
		LevelSensorArduino testSens = new LevelSensorArduino("WSECO", "WL");
		System.out.println("Create new SENSOR name:" + testSens.getName()
				+ " with dataType:" + testSens.getDatatype());
		System.out.println("Test simulated measure:"
				+ (testSens.getSurvey()).getValue());

	}

}