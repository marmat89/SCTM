package it.unibo.system;

import it.unibo.sensors.impl.*;
import it.unibo.sensors.simulated.impl.*;
import it.unibo.station.impl.*;
import it.unibo.util.Coordinate;

public class Builder {
	public static AirMonitor testAir = new AirMonitor("SimulatedAirStationCesena",
			new Coordinate(44.139307, 12.237057), 3);
	
	public static GroundMonitor testGND = new GroundMonitor("SimulatedGroundStationCesena",
			new Coordinate(44.139307, 12.237057),2);
	
	public static WaterMonitor testWTR= new WaterMonitor("SimulatedWaterStationCesena",
			new Coordinate(44.139307, 12.237057),1);



	public static void main(String[] args) throws InterruptedException {
		/*
		System.out.println("Create new STATION name:" + testWTR.getName());
		testWTR.addTemperatureSensor(new TempSensorArduino("DS18B20", "TMP"));
		testWTR.addLevelSensor(new LevelSensorArduino("WSECO", "WL"));
		testWTR.addSpeedSensor(new SpeedSensorArduino("HC020K", "SPD"));
		StationSimulator wS = new StationSimulator();
		wS.ThreadCreation(testWTR);
		*/
		System.out.println("Create new STATION name:" + testAir.getName());
		testAir.addTemperatureSensor(new TempSensorArduino("DHT11", "TMP"));
		testAir.addHumiditySensor(new HumidSensorArduino("DHT11", "HMD"));
		testAir.addLightSensor(new LightSensorArduino("LDRGL5528", "LGH"));
		testAir.addRainSensor(new RainSensorArduino("RAINECO", "RL"));
		testAir.addSpeedSensor(new SpeedSensorArduino("HC020K", "SPD"));
		StationSimulator aS = new StationSimulator();
		aS.ThreadCreation(testAir);
		
		System.out.println("Create new STATION name:" + testGND.getName());
		testGND.addTemperatureSensor(new tempSIMSensor("DS18B20", "TMP"));
		testGND.addTiltSensor(new tiltSIMSensor("TLTECO", "ALL"));
		testGND.addHumiditySensor(new humidSIMSensor("HGMECO", "HMD"));	
		testGND.addDipSensor(new tiltSIMSensor("FC01","ALL"));
	
		
		StationSimulator gndStationSim = new StationSimulator();
		gndStationSim.ThreadCreation(testGND);
		
		Thread.sleep(500);
		
	}

}
