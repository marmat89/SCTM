package it.unibo.interfaces;

import it.unibo.util.Coordinate;
import it.unibo.util.Measure;

import java.util.List;


public abstract class Station implements IStation  {
//on DB: 	idStations
//			statName
//			position
public int ID;
protected String name;
protected Coordinate position;

//lastUpdate
public List<Measure> mesList;

public Station(String name, Coordinate position,int ID){
	this.name = name;
	this.position = position;
	this.ID=ID;
}

//getter methods
@Override
public String getName() {
	return name;
}
@Override
public Coordinate getPosition() {
	return position;
}
@Override
public List<Measure> lastUpdate() {
	return mesList;
}
}
