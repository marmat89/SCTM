package it.unibo.system;

import it.unibo.interfaces.Sensor;
import it.unibo.interfaces.Station;
import it.unibo.interfaces.IStation;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
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
									+ ((Station) s).ID
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
							+ ((Station) s).ID + "', '" + row.getName() + "');");
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

	public void sendErr(Station stat, Sensor s, String type,String erDesc) {

		Statement stmt;
		DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		
		int i = 0;
		if (s != null) {
			try {
				stmt = (Statement) db.createStatement();
				System.out
						.println("INSERT INTO `rcsdb`.`errors` (`erDesc`, `assembled_Stations_idStations`, `assembled_Sensors_idSen`, `machine_idMachine`, `ErrorType_idErrorType`, `erData`)  VALUES ('"
								+ erDesc
								+ "', '"
								+ stat.ID
								+ "', '"
								+ s.getName()
								+ "', 'RPI','"
								+ type
								+ "','"
								+ (df.format(calobj.getTime())) + "');");
				stmt.execute("INSERT INTO `rcsdb`.`errors` (`erDesc`, `assembled_Stations_idStations`, `assembled_Sensors_idSen`, `machine_idMachine`, `ErrorType_idErrorType`, `erData`)  VALUES ('"
						+ erDesc
						+ "', '"
						+ stat.ID
						+ "', '"
						+ s.getName()
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
}