package it.unibo.util;

import java.util.Date;


public abstract class Measure implements IMeasure{

private boolean simulated;
private Date time;
private String UOM;
public Measure(boolean simulated) {
	this.simulated=simulated;
}

public static void main(String[] args) {
	}

/* (non-Javadoc)
 * @see it.unibo.util.IMeasure#getUOM()
 */
@Override
public String getUOM() {
	return UOM;
}

/* (non-Javadoc)
 * @see it.unibo.util.IMeasure#isSimulated()
 */
@Override
public boolean isSimulated() {
	return simulated;

}
/* (non-Javadoc)
 * @see it.unibo.util.IMeasure#getTime()
 */
@Override
public Date getTime() {
	return time;
}




}
