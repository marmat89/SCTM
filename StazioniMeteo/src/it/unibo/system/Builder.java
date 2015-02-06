package it.unibo.system;

import it.unibo.debugger.OnBoardDebugger;
import it.unibo.sensors.impl.*;
import it.unibo.sensors.simulated.impl.*;
import it.unibo.station.impl.*;
import it.unibo.util.Coordinate;

/**
 * the builder is used to define the weather stations on the device, usually a
 * weather station at a time, but we can also define a DEVICE that simulates
 * more than a weather station at a time weather each station needs a simulator
 * that allows the station to make requests to Sensors and does the upload on DB
 * 
 * @author matteo.mariani11@studio.unibo.it
 * @version 1.0.0
 * @since 06/feb/2015 01:33:00
 *
 */
public class Builder {
	public static AirMonitor testAir = new AirMonitor(
			"SimulatedAirStationCesena", new Coordinate(44.139307, 12.237057),
			3);
	public static GroundMonitor testGND = new GroundMonitor(
			"SimulatedGroundStationCesena",
			new Coordinate(44.139307, 12.237057), 2);
	public static WaterMonitor testWTR = new WaterMonitor(
			"SimulatedWaterStationCesena",
			new Coordinate(44.139307, 12.237057), 1);

	public static void main(String[] args) throws InterruptedException {
//		System.out.println("Create new STATION name:" + testWTR.getName());
//		testWTR.addTemperatureSensor(new TempSensorSim("DS18B20", "TMP"));
//		testWTR.addLevelSensor(new LevelSensorArduino("WSECO", "WL"));
//		testWTR.addSpeedSensor(new SpeedSensorSim("HC020K", "SPD"));
//		StationSimulator wS = new StationSimulator();
//		wS.ThreadCreation(testWTR);

//		 System.out.println("Create new STATION name:" + testAir.getName());
//		 OnBoardDebugger deb= new OnBoardDebugger(testAir);
//		 testAir.addTemperatureSensor(new TempSensorSim("DS18B20", "TMP"));
//		 testAir.addHumiditySensor(new HumidSensorArduino("DHT11",
//		 "HMD",deb));
//		 testAir.addLightSensor(new LightSensorArduino("LDRGL5528",
//		 "LGH",deb));
//		 testAir.addRainSensor(new RainSensorArduino("RAINECO", "RL",deb));
//		 testAir.addSpeedSensor(new SpeedSensorArduino("HC020K", "SPD",deb));
//		 StationSimulator aS = new StationSimulator();
//		 aS.ThreadCreation(testAir);
		
		 System.out.println("Create new STATION name:" + testGND.getName());
		 OnBoardDebugger deb= new OnBoardDebugger(testGND);
		 testGND.addTemperatureSensor(new TempSensorArduino("DS18B20", "TMP",deb));
//		 testGND.addTiltSensor(new TiltSensorSim("TLTECO", "ALL")); 
		 testGND.addHumiditySensor(new HumidSensorArduino("HGMECO", "HMD",deb));
		 testGND.addDipSensor(new ShockSensorArduino("FC01","ALL",deb));
		
		
		 StationSimulator gndStationSim = new StationSimulator();
		 gndStationSim.ThreadCreation(testGND);
		
		Thread.sleep(500);

	}

}
