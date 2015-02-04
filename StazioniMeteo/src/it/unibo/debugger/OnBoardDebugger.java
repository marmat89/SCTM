package it.unibo.debugger;

import it.unibo.interfaces.*;
import it.unibo.sensors.impl.RainSensorArduino;
import it.unibo.station.impl.AirMonitor;
import it.unibo.system.DbCom;
import it.unibo.util.Coordinate;

public class OnBoardDebugger {
	Station station;
	public OnBoardDebugger(Station station) {
		super();
		this.station = station;
	}
	public void SerialReplyProblem(Sensor sen){
		System.out.println("Sensor:"+sen.getName()+" no replay to embedded machine");
		DbCom sm = new DbCom("root", "root");
		String erDesc = "Error Detected form \nStation:" + station.getName()
				+ "\nSensor:" + sen.getName()
				+ "\nError:Sensor no replay to embedded machine";
		sm.sendErr(station, sen,"SDE",erDesc);
		sm.turnOffConnection("root", "root");
	}	
	public void SerialOccupatedProblem(Sensor sen){
		//TODO update keySensor erName erDesc
		System.out.println("SensorComPort of "+sen.getName()+"  is occupated on embedded machine");
		DbCom sm = new DbCom("root", "root");
		String erDesc = "Error Detected form \nStation:" + station.getName()
				+ "\nSensor:" + sen.getName()
				+ "\nError:Serial Occupated on embedded machine";
		sm.sendErr(station, sen,"SCE",erDesc);
		sm.turnOffConnection("root", "root");
	}
	public static void main(String[] args) {
		OnBoardDebugger SRP = new OnBoardDebugger(new AirMonitor("testAirMonitor", new Coordinate(0,0),3));
		SRP.SerialReplyProblem(	new  RainSensorArduino("RAINECO", "RL"));
		SRP.SerialOccupatedProblem(	new  RainSensorArduino("RAINECO", "RL"));
		
	}

}
