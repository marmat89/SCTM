package it.unibo.util;

public class Coordinate {

private double latitude;
private double longitude;
public Coordinate(){
	
}
public Coordinate(double latitude, double longitude) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
}
	public static void main(String[] args) {
		
	}
	public double getLat() {
		return latitude;
	}
	public void setLat(double latitude) {
		this.latitude = latitude;
	}
	public double getLon() {
		return longitude;
	}
	public void setLon(double longitude) {
		this.longitude = longitude;
	}
}
