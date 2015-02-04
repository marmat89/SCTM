package it.unibo.util;

import java.util.Date;

public interface IMeasure {

	public  String getUOM();

	public  boolean isSimulated();

	public  Date getTime();
	
	public Object getValue();

}