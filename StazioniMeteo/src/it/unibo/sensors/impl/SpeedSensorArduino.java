package it.unibo.sensors.impl;

import java.util.Observable;
import java.util.Observer;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorArduino;
import it.unibo.sensors.simulated.impl.fanspeedSIMSensor;
import it.unibo.sensors.simulated.impl.humidSIMSensor;
import it.unibo.sensors.simulated.impl.tempSIMSensor;
import it.unibo.util.FloatMeasure;
import it.unibo.util.IMeasure;
import it.unibo.util.SerialCom;

public class SpeedSensorArduino extends SensorArduino implements Observer {

	public SpeedSensorArduino(String name, String type) {
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
			arduino.sendData("getSpeed=");
			// used for attend sensor respond for N seconds
			attendSerialCom();
			return getIntValue();
		} else
			return null;

	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		value = ((SerialCom) o).getSomeVariable();
		System.out.println("Arduino received = " + value);
		this.notifyAll();

	}

	public static void main(String[] args) {
		SpeedSensorArduino testSens = new SpeedSensorArduino("HC020K", "SPD");
		System.out.println("Create new SENSOR name:" + testSens.getName()
				+ " with dataType:" + testSens.getDatatype());
		System.out.println("Test simulated measure:"
				+ (testSens.getSurvey()).getValue());

	}

}
