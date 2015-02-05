package it.unibo.sensors.impl;

import java.util.Observable;
import java.util.Observer;

import it.unibo.debugger.OnBoardDebugger;
import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorArduino;
import it.unibo.sensors.simulated.impl.rainSIMSensor;
import it.unibo.sensors.simulated.impl.tempSIMSensor;
import it.unibo.util.FloatMeasure;
import it.unibo.util.IMeasure;
import it.unibo.util.IntMeasure;
import it.unibo.util.SerialCom;

public class RainSensorArduino extends SensorArduino implements Observer {

public RainSensorArduino(String name, String type, OnBoardDebugger deb) {
		super(name, type, deb);
		// TODO Auto-generated constructor stub
	}

	public RainSensorArduino(String name, String type) {
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
			arduino.sendData("getRain=");
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
		RainSensorArduino testSens = new RainSensorArduino("RAINECO", "RL");
		System.out.println("Create new SENSOR name:" + testSens.getName()
				+ " with dataType:" + testSens.getDatatype());
		System.out.println("Test simulated measure:"
				+ (testSens.getSurvey()).getValue());
	}

}