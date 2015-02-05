package it.unibo.util;

import it.unibo.debugger.OnBoardDebugger;
import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.SensorArduino;
import it.unibo.interfaces.Station;
import it.unibo.interfaces.StationRPI;
import it.unibo.station.impl.AirMonitor;

public class AttendRispThread implements Runnable {
	SensorArduino sen;
	OnBoardDebugger deb;

	public void ThreadCreation(OnBoardDebugger deb) {
		this.sen = (SensorArduino) sen;
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
			deb.SerialReplyProblem(sen);
			synchronized (sen) {
				sen.notify();
				}
			}
		} catch (InterruptedException exc) {
		}

	}
}