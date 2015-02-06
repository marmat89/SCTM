package it.unibo.util;

import it.unibo.debugger.OnBoardDebugger;
import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorArduino;
import it.unibo.interfaces.StationRPI;
import it.unibo.station.impl.AirMonitor;

/**
 * 
 * @author matteo.mariani11@studio.unibo.it
 * 
 * Sensor need attend a serial replay, thread permits attending for 5 second 
 * serial COM port replay
 * @version 1.0.0
 * @since  05/feb/2015 21:26:17
 *
 */
public class AttendRispThread implements Runnable {
	public SensorArduino sen;
	public OnBoardDebugger deb;
/**
 * Thread attending repaly creation
 * @param deb Same Sensor have an on-board Debugger that update on the DB
 */
	public void ThreadCreation(OnBoardDebugger deb, SensorArduino sen) {
		this.sen =  sen;
		this.deb=deb;
		// THREAD finder Start
		Thread findTh = new Thread(this);
		findTh.start();
	}

	public void run() {

		try {
			Thread.sleep(5000);
			if (sen.value == "") {
			System.out.println("Thread Request OUT OF TIME");
			if (deb!=null)
			deb.SerialReplyProblem(sen);
			synchronized (sen) {
				sen.notify();
				}
			}
		} catch (InterruptedException exc) {
		}

	}
}