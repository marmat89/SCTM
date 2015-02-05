package it.unibo.interfaces;

import it.unibo.debugger.OnBoardDebugger;
import it.unibo.sensors.impl.LevelSensorArduino;
import it.unibo.station.impl.AirMonitor;
import it.unibo.util.AttendRispThread;
import it.unibo.util.Coordinate;
import it.unibo.util.FloatMeasure;
import it.unibo.util.IMeasure;
import it.unibo.util.IntMeasure;
import it.unibo.util.SerialCom;

import java.util.Observable;
import java.util.Observer;

public abstract class SensorArduino extends Sensor implements Observer {
	// Used to communicate with sensors arduino
	// Using calls to the serial:
	// getHumidity=
	// getTemp=
	// getSpeed=
	// getLight=
	// getLevel=
	public String value;
	// for communication with arduino
	protected SerialCom arduino;
    public OnBoardDebugger deb;
	private AttendRispThread attendRisp;
	public SensorArduino(String name, String type) {
		super(type, reliability, type);
		this.name = name;
		this.reliability = true;
		this.datatype = type;
	}	
	public SensorArduino(String name, String type,OnBoardDebugger deb ) {
		super(type, reliability, type);
		this.name = name;
		this.reliability = true;
		this.datatype = type;
		this.deb=deb;
	}

	protected boolean initSerialCom(SensorArduino sens) {
		arduino = new SerialCom();
		// initialize com port
		if (!arduino.initialize()) {
			deb.SerialOccupatedProblem(this);
			return false;
		} else {
			// TODO implement concurrent programming (not necessary because
			// arduino
			// reply is very fast)
			// used for attend com reply
			value = "";
			// add observer for return variable
			arduino.addObserver(sens);
			// send data to arduino port
			return true;
		}
	}

	protected IntMeasure getIntValue() {
		if (value != "") {
			// add measure to list and return value
			IntMeasure mes = new IntMeasure(false, Integer.parseInt(value));
			measures.add(mes);
			return mes;
		} else {
			System.out.println("Sensor does not respond");
			return null;
		}
	}

	protected IMeasure getFloatValue() {

		if (value != "") {
			// add measure to list and return value
			FloatMeasure mes = new FloatMeasure(false, Float.parseFloat(value));
			measures.add(mes);
			return mes;
		} else {
			System.out.println("Sensor does not respond");
			return null;
		}
	}

	protected void attendSerialCom() {
		// rude attend variable
		System.out.println("====Arduino attend reply====");
		attendRisp = new AttendRispThread();
		attendRisp.ThreadCreation(deb);
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arduino.close();
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		value = ((SerialCom) o).getSomeVariable();
		System.out.println("Arduino received = " + value);
		this.notifyAll();
	}

}
