package it.unibo.system;

import java.io.IOException;
import java.sql.SQLException;
import it.unibo.interfaces.StationRPI;
import it.unibo.util.*;
/**
 * 
 * @author matteo.mariani11@studio.unibo.it
 * @version 1.0.0
 * @since  06/feb/2015 01:36:19
 *
 */
public class StationSimulator implements Runnable {
	StationRPI s;
	DbCom sm;

	public void ThreadCreation(StationRPI s) {
		this.s = s;

		// THREAD finder Start
		Thread thUpdate = new Thread(this);
		thUpdate.start();
	}

	@Override
	public void run() {
		while (true) {

			sm = new DbCom("root", "root");
			try {
			if (s.monitorUpdates() != null) {
					sm.sendMes(s,s.mesList);
				}

				Thread.sleep(30000);
				sm.turnOffConnection("root", "root");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
