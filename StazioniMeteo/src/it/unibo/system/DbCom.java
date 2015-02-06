package it.unibo.system;

import it.unibo.interfaces.StationRPI;
import it.unibo.util.AssembledList;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author matteo.mariani11@studio.unibo.it
 * @version 1.0.0
 * @since  06/feb/2015 01:36:09
 *
 */
public class DbCom {
	Connection db;
	String user;
	String password;
	String TABLE;
	String componentsTable;

	public DbCom(String user, String password) {
		this.user = user;
		this.password = password;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			db = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"
					+ "RCSdb" + "?user=" + user + "&password=" + password);

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void sendMes(StationRPI stat, List<AssembledList> lastUpdate) {

		Statement stmt;
		DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		Iterator<AssembledList> itLast = lastUpdate.iterator();
		while (itLast.hasNext()) {
			AssembledList row = (AssembledList)itLast.next();
			if (row != null) {
				try {
					stmt = (Statement) db.createStatement();
					System.out
							.println("INSERT INTO `RCSdb`.`Measure` (`desc`, `dataObtained`, `datatype`, `reliability`, `value`, `Assembled_Stations_idStations`, `Assembled_Sensors_idSen`) VALUES ('getFrom"
									+ stat.getName()
									+ "', '"
									+ (df.format(calobj.getTime()))
									+ "', '"
									+ row.sen.getDatatype()
									+ "', '0', '"
									+ row.lastMes.getValue()
									+ "', '"
									+ stat.ID
									+ "', '"
									+ row.sen.getName() + "');");
					stmt.execute("INSERT INTO `RCSdb`.`Measure` (`desc`, `dataObtained`, `datatype`, `reliability`, `value`, `Assembled_Stations_idStations`, `Assembled_Sensors_idSen`) VALUES ('getFrom"
							+ stat.getName()
							+ "', '"
							+ (df.format(calobj.getTime()))
							+ "', '"
							+ row.sen.getDatatype()
							+ "', '0', '"
							+ row.lastMes.getValue()
							+ "', '"
							+ stat.ID
							+ "', '"
							+ row.sen.getName() + "');");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void getMes(String column) {

		Statement stmt;
		try {
			stmt = (Statement) db.createStatement();
			String query = "SELECT * FROM rcsdb.measure";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("SELECT idMes FROM rcsdb.measure");
			while (rs.next()) {
				int id = rs.getInt(column);
				System.out.println(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void stationStateUpdate(String stationID, String stat) {
		System.out

		.println("UPDATE `rcsdb`.`stations` SET `state`='" + stat
				+ "' WHERE `idStations`='" + stationID + "';");
		try {
			Statement stmt = (Statement) db.createStatement();
			stmt.execute("UPDATE `rcsdb`.`stations` SET `state`='" + stat
					+ "' WHERE `idStations`='" + stationID + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void sensorStateUpdate(String stationID, String sensor, String stat) {
		System.out

		.println("UPDATE `rcsdb`.`assembled` SET `states_idStates`='" + stat
				+ "' WHERE `Stations_idStations`='" + stationID
				+ "' and`Sensors_idSen`='" + sensor + "';");
		try {
			Statement stmt = (Statement) db.createStatement();
			stmt.execute("UPDATE `rcsdb`.`assembled` SET `states_idStates`='"
					+ stat + "' WHERE `Stations_idStations`='" + stationID
					+ "' and`Sensors_idSen`='" + sensor + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void sendErr(String statID, String senName, String type,
			String erDesc) {

		Statement stmt;
		DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();

		if (senName != null) {
			try {
				stmt = (Statement) db.createStatement();
				System.out
						.println("INSERT INTO `rcsdb`.`errors` (`erDesc`, `assembled_Stations_idStations`, `assembled_Sensors_idSen`, `machine_idMachine`, `ErrorType_idErrorType`, `erData`)  VALUES ('"
								+ erDesc
								+ "', '"
								+ statID
								+ "', '"
								+ senName
								+ "', 'RPI','"
								+ type
								+ "','"
								+ (df.format(calobj.getTime())) + "');");
				stmt.execute("INSERT INTO `rcsdb`.`errors` (`erDesc`, `assembled_Stations_idStations`, `assembled_Sensors_idSen`, `machine_idMachine`, `ErrorType_idErrorType`, `erData`)  VALUES ('"
						+ erDesc
						+ "', '"
						+ statID
						+ "', '"
						+ senName
						+ "', 'RPI','"
						+ type
						+ "','"
						+ (df.format(calobj.getTime())) + "');");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String Args[]) throws SQLException,
			ClassNotFoundException, IllegalAccessException,
			InstantiationException, IOException {
		DbCom c = new DbCom("root", "root");
		c.getMes("value");

	}

	public void turnOffConnection(String user, String password) {
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList getQuerySingleValueInt(String query, String field) {
		Statement stmt;
		List<Object> list = new ArrayList();
		try {
			stmt = (Statement) db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("exe:" + query + " with field:" + field);
			while (rs.next()) {
				Object id = rs.getInt(field);
				list.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList) list;
	}

	public ArrayList getQuerySingleValueString(String query, String field) {
		Statement stmt;
		List<Object> list = new ArrayList();
		try {
			stmt = (Statement) db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("exe:" + query + " with field:" + field);
			while (rs.next()) {
				Object id = rs.getString(field);
				System.out.println(id);
				list.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList) list;
	}

	public int getLastUpdateDayDifference(String query, String field) {
		Statement stmt;
		List<Integer> list = new ArrayList<Integer>();
		DateFormat df = new SimpleDateFormat("yy/MM/dd");

		try {
			stmt = (Statement) db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("exe:" + query + " with field:" + field);
			while (rs.next()) {
				Date id = new Date();
				id = rs.getDate(field);
				Date now = new Date();
				now.toInstant();
				System.out.println(df.format(id));
				System.out.println(df.format(now.getTime()));
				System.out.println((now.getTime() - id.getTime())
						/ (1000L * 60L * 60L * 24L) + 1);
				list.add((int) ((now.getTime() - id.getTime())
						/ (1000L * 60L * 60L * 24L) + 1));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return 0;
		} else {
			return (list.get(list.size() - 1));
		}
	}
}