package it.unibo.sensors.impl;

import java.util.Observable;
import java.util.Observer;

import it.unibo.debugger.OnBoardDebugger;
import it.unibo.interfaces.SensorArduino;
import it.unibo.util.AttendRispThread;
import it.unibo.util.IMeasure;
import it.unibo.util.IntMeasure;
import it.unibo.util.SerialCom;

public class HumidSensorArduino  extends SensorArduino {

public HumidSensorArduino(String name, String type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}


	public HumidSensorArduino(String name, String type, OnBoardDebugger deb) {
		super(name, type, deb);
		// TODO Auto-generated constructor stub
	}




	//used for debug mode, if Serial port don't respond in N seconds we have an allarm
	private AttendRispThread attendRisp;

	//  The DHT11 is a basic, ultra low-cost digital temperature and humidity sensor.
	//	It uses a capacitive humidity sensor and a thermistor to measure the surrounding air,
	//	and spits out a digital signal on the data pin (no analog input pins needed). 
	//	Its fairly simple to use, but requires careful timing to grab data. 
	//	The only real downside of this sensor is you can only get new data from it once every 2 seconds,
	//	so when using our library, sensor readings can be up to 2 seconds old.



	// All physical sensors must implement getSurvey ()
	// Depending on the type of data that is required
	// have all the same iter: OPEN port => SEND request => GET answer
	@Override
	public synchronized IMeasure getSurvey() {
	
		//arduino serial com setup
		if (initSerialCom(this)){
		
		//keyword arduino request 
		arduino.sendData("getHumidjity=");
		//used for attend sensor respond for N seconds
		attendSerialCom();
		//For Level Sensor we return an int value from 0 to 100
			return getIntValue();
		} else
			return null;

		
		
	}


	public static void main(String[] args) {
		HumidSensorArduino testSens = new HumidSensorArduino("DHT11", "HMD");
		System.out.println("Create new SENSOR name:" + testSens.getName()
				+ " with dataType:" + testSens.getDatatype());
		System.out.println("Arduino measure:"
				+ (testSens.getSurvey()).getValue());

	}

}