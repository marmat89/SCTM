package it.unibo.debugger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import it.unibo.system.DbCom;

/**
 * An online debugger is a system that allows us to check the overall status of
 * the stations, analyzing each sensor on each station. The goal is identify
 * problems using statistics, this allows us to estimate the quality of the data
 * uploaded to the DB.
 * 
 * @author matteo.mariani11@studio.unibo.it
 * @version 1.0.0
 * @since 05/feb/2015 23:32:55
 *
 */
public class OnlineDebugger {

	/**
	 * UTIL used to represent pairs station-sensor on DB
	 * 
	 * @author matteo.mariani11@studio.unibo.it
	 * @version 1.0.0
	 * @since 05/feb/2015 23:33:00
	 *
	 */
	public class SenList {
		public String sen;
		public String stat;

		private SenList(String stat, String sen) {
			this.stat = stat;
			this.sen = sen;
		}
	}

	/**
	 * Check Reliability Sensor aims to classify the quality of the data. We
	 * consider two significant indexes: JUMPS values that differ too trend.
	 * ZERO because the Arduino sensors are not capable to debug, so even if
	 * physically disconnected continue to send ZERO values, a long sequence of
	 * these values allows us to establish that these values are suspect.
	 */
	public void checkReliabilitySensor() {
		// CONNECTION OPEN
		DbCom sm = new DbCom();
		List<SenList> lst = this.getAllCollection();
		Iterator<SenList> itLst = lst.iterator();
		while (itLst.hasNext()) {
			SenList el = (SenList) itLst.next();
			// QUERY
			String query = ("SELECT * FROM rcsdb.measure WHERE	measure.Assembled_Sensors_idSen='"
					+ el.sen
					+ "' and measure.Assembled_Stations_idStations='"
					+ el.stat + "' and (measure.datatype!='ALL' and measure.datatype!='SPD' ) ORDER BY measure.idMes ;");
			System.out.println("Query:" + query);
			ArrayList<?> i = sm.getQuerySingleValueInt(query, "value");
			Iterator<?> list = i.iterator();
			
			int failCount = 0;
			boolean find = true;
	if (i.size() > 0) {
				list = i.iterator();
				while (list.hasNext() && find) {
					int elem = (int) list.next();
				System.out.println("check:" + elem);
					if (elem == 0 ) {
						failCount++;
						System.out.println("Found an abnormal element");
					} else {
						find = false;
						System.out.println("No Other abnormal element find");
					}
				}
			}
			if (failCount > 16) {
				System.out.println("Sensor " + el.sen
						+ " Mounted On machine ID:" + el.stat
						+ " is giving values ​​suspects");
				String erDesc = "Error Detected form \nStation:"
						+ el.stat
						+ "\nSensor:"
						+ el.sen
						+ "\nError:Machine communicates unreliable data to the central server";
				sm.sendErr(el.stat, el.sen, "RSE", erDesc);
				sm.sensorStateUpdate(el.stat, el.sen, "I");
			} else {
				sm.sensorStateUpdate(el.stat, el.sen, "A");
			}

		}
		lst = this.getAllCollection();
		itLst = lst.iterator();
		while (itLst.hasNext()) {
			SenList el = (SenList) itLst.next();
			// QUERY
			String query = ("SELECT * FROM rcsdb.measure WHERE	measure.Assembled_Sensors_idSen='"
					+ el.sen
					+ "' and measure.Assembled_Stations_idStations='"
					+ el.stat + "' ORDER BY measure.idMes ;");
			System.out.println("Query:" + query);
			ArrayList<?> i = sm.getQuerySingleValueInt(query, "value");
			Iterator<?> list = i.iterator();
			int avg = 0;
			int count = 0;
			// FAST VALUE AVG
			while (list.hasNext() && count < 30) {
				avg += (int) list.next();
				count++;
			}

			int failCount = 0;
			if (i.size()>0)
			avg = avg / i.size();
			/**
			 * Used to estimate reliability we count only suspect value if are
			 * too many, we can consider to send an warning
			 */
			if (i.size() > 0 ) {
				System.out.println("avg=" + avg);
				list = i.iterator();
				count = 0;
				int oldvalue = (int) list.next();
				while (list.hasNext() && count < 30) {
					int elem = (int) list.next();
					count++;
					System.out.println("check:" + elem);
					if ( elem - oldvalue > (avg / 3)
							|| elem - oldvalue < -(avg / 3)) {
						failCount++;
						System.out.println("Found an abnormal element");
					} else {
						System.out.println("No Other abnormal element find");
					}
					oldvalue = elem;
				}
			}
			if (failCount > 0) {
				System.out.println("Sensor " + el.sen
						+ " Mounted On machine ID:" + el.stat
						+ " is giving values ​​suspects");
				String erDesc = "Error Detected form \nStation:"
						+ el.stat
						+ "\nSensor:"
						+ el.sen
						+ "\nError:Machine communicates unreliable data to the central server";
				sm.sendErr(el.stat, el.sen, "RSE", erDesc);
				sm.sensorStateUpdate(el.stat, el.sen, "I");
			} else {
				sm.sensorStateUpdate(el.stat, el.sen, "A");
			}

		}
		// CONNECTION CLOSE
		sm.turnOffConnection();

	}

	/**
	 * UTILITY class used to ask the DB list all pairs STATION-SENSOR * @return
	 * pairs STATION-SENSOR
	 */
	public List<SenList> getAllCollection() {
		// CONNECTION OPEN
		DbCom sm = new DbCom();
		List<SenList> lst = new ArrayList<SenList>();

		// QUERY
		String queryfind = ("SELECT assembled.Stations_idStations FROM rcsdb.assembled GROUP BY assembled.Stations_idStations");
		System.out.println("Query:" + queryfind);
		ArrayList<?> statIndex = sm.getQuerySingleValueString(queryfind,
				"Stations_idStations");
		Iterator<?> itStatIndex = statIndex.iterator();
		while (itStatIndex.hasNext()) {
			String stat = (String) itStatIndex.next();

			queryfind = ("SELECT assembled.Sensors_idSen FROM rcsdb.assembled WHERE assembled.Stations_idStations='"
					+ stat + "'");
			System.out.println("Query:" + queryfind);
			ArrayList<?> senIndex = sm.getQuerySingleValueString(queryfind,
					"Sensors_idSen");
			Iterator<?> itSenIndex = senIndex.iterator();
			while (itSenIndex.hasNext()) {
				String sen = (String) itSenIndex.next();

				lst.add(new SenList(stat, sen));
			}

		}
		// CONNECTION CLOSE
		sm.turnOffConnection();
		return lst;
	}

	/**
	 * another strength factor si last information update distance, recent
	 * information is more safe. If the debugger find information too old
	 * creates an warning of suspected connection lost
	 */
	public void checkMachineConnection() {
		// CONNECTION OPEN
		DbCom sm = new DbCom();
		List<SenList> lst = this.getAllCollection();
		Iterator<SenList> itLst = lst.iterator();
		while (itLst.hasNext()) {
			SenList el = (SenList) itLst.next();
			// QUERY
			String query = ("SELECT * FROM rcsdb.measure WHERE	measure.Assembled_Sensors_idSen='"
					+ el.sen
					+ "' and measure.Assembled_Stations_idStations='"
					+ el.stat + "'ORDER BY measure.idMes DESC LIMIT 1;");
			System.out.println("Query:" + query);

			int i = sm.getLastUpdateDayDifference(query, "dataObtained");
			if (i > 1) {
				System.out.println("Sensor " + el.sen
						+ " Mounted On machine ID:" + el.stat
						+ " is more than " + i
						+ " day that does not communicate");
				String erDesc = "Error Detected form \nStation:"
						+ el.stat
						+ "\nSensor:"
						+ el.sen
						+ "\nError:Machine no longer communicates data to central server,death device suspected";
				sm.sendErr(el.stat, el.sen, "MCE", erDesc);
				sm.stationStateUpdate(el.stat, "L");
			} else {
				System.out.println("Sensor " + el.sen
						+ " Mounted On machine ID:" + el.stat + "is on");
				sm.stationStateUpdate(el.stat, "A");
			}
		}
		// CONNECTION CLOSE
		sm.turnOffConnection();

	}

	public static void main(String[] args) {
		OnlineDebugger SRP = new OnlineDebugger();
		SRP.checkReliabilitySensor();
		//SRP.checkMachineConnection();
		// SRP.getAllCollection();

	}
}
