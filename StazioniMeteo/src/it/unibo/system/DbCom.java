package it.unibo.system;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.StationRPI;
import it.unibo.interfaces.IStation;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.Days;

public class DbCom {
	Connection db;
	String user;
	String password;
	String TABLE;
	String componentsTable;

	public DbCom(String user, String password) {
		this.user = user;
		this.password = password;
		// carico il driver JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// mi connetto al database con i dati inseriti nel main
			// la password pu� non essere necessaria
			db = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"
					+ "RCSdb" + "?user=" + user + "&password=" + password);

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMes(IStation s) {

		Statement stmt;
		DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		Iterator<Sensor> slist = s.getSensorList().iterator();
		int i = 0;
		while (slist.hasNext()) {
			Sensor row = slist.next();
			if (row != null) {
				try {
					// eseguo una query
					// stmt.executeQuery("SELECT * FROM sensors");
					// INSERT INTO `RCSdb`.`Measure` (`desc`, `dataObtained`,
					// `datatype`, `reliability`, `value`,
					// `Assembled_Stations_idStations`,
					// `Assembled_Sensors_idSen`) VALUES ('asdf', '1989/6/1
					// 12:00:00', 'TMP', '0', '12', '1', 'DS18B20');
					stmt = (Statement) db.createStatement();
					System.out
							.println("INSERT INTO `RCSdb`.`Measure` (`desc`, `dataObtained`, `datatype`, `reliability`, `value`, `Assembled_Stations_idStations`, `Assembled_Sensors_idSen`) VALUES ('getFrom"
									+ s.getName()
									+ "', '"
									+ (df.format(calobj.getTime()))
									+ "', '"
									+ row.getDatatype()
									+ "', '0', '"
									+ (s.lastUpdate().get(i)).getValue()
									+ "', '"
									+ ((StationRPI) s).ID
									+ "', '"
									+ row.getName() + "');");
					stmt.execute("INSERT INTO `RCSdb`.`Measure` (`desc`, `dataObtained`, `datatype`, `reliability`, `value`, `Assembled_Stations_idStations`, `Assembled_Sensors_idSen`) VALUES ('getFrom"
							+ s.getName()
							+ "', '"
							+ (df.format(calobj.getTime()))
							+ "', '"
							+ row.getDatatype()
							+ "', '0', '"
							+ (s.lastUpdate().get(i)).getValue()
							+ "', '"
							+ ((StationRPI) s).ID
							+ "', '"
							+ row.getName()
							+ "');");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendErr(String statID, String senName, String type,
			String erDesc) {

		Statement stmt;
		DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();

		int i = 0;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
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
			// TODO Auto-generated catch block
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
				// System.out.println(id);
				list.add(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArrayList) list;
	}

	public int getLastUpdateDayDifference(String query, String field) {
		Statement stmt;
		List<Integer> list = new ArrayList<Integer>();
		DateFormat df = new SimpleDateFormat("yy/MM/dd");
		// DateFormat tf = new SimpleDateFormat("HH:mm:ss");

		try {
			stmt = (Statement) db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("exe:" + query + " with field:" + field);
			while (rs.next()) {
				Date id = new Date();
				id = rs.getDate(field);
				Date now = new Date();
				now.toInstant();
				// Time idt = rs.getTime(field);
				System.out.println(df.format(id));
				// System.out.println(tf.format(idt));
				System.out.println(df.format(now.getTime()));
				// System.out.println(tf.format(now.getTime()));
				// System.out.println(now.getTime() - id.getTime()) ;
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