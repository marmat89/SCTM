package it.unibo.interfaces;

import it.unibo.util.Coordinate;
import it.unibo.util.Measure;

import java.util.List;

//a station is a center of detection of data, 
//has the aim to provide data to the database
//based on sensors installed on board, 
//which interrogates, and that provide data
//of different nature: temperature, speed, radiation etc.
public interface IStation {

//usually refers to the primary key in DB
public String getName();
//geoReference of Station
public Coordinate getPosition();
//GET: list of onBoard Sensor
public List<Sensor> getSensorList();
//GET: refresh LastUpdates
public List<Measure> monitorUpdates();
//GET: list of lastUpdate
public List<Measure> lastUpdate();
}
