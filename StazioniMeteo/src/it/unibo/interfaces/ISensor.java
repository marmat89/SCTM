package it.unibo.interfaces;

import it.unibo.util.IMeasure;
//interface that represents a sensor
public interface ISensor {
	// utility used to retrieve the name of the sensor, which corresponds to the identifier on DB
	public String getName();
	// control used to manage malfunctions
	public String getState();
	// base: allows to know a measurement of a given sensor
	public IMeasure getSurvey();

}
