package it.unibo.system;

import java.io.IOException;
import java.sql.SQLException;

import it.unibo.interfaces.StationRPI;
import it.unibo.util.*;

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
				s.monitorUpdates();
				if (s.monitorUpdates() != null) {
					sm.sendMes(s);
				}

				Thread.sleep(60000);
				sm.turnOffConnection("root", "root");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
