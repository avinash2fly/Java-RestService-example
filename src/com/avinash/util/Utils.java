package com.avinash.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	private static Map<String, Integer> cache = new HashMap<>();

	public static void clearCache() {
		cache.clear();
	}

	public static void addData(String Driver, int count) {
		cache.put(Driver, count);
	}

	public static int getData(String driver) {
		if (cache.get(driver) != null) {
			return cache.get(driver);
		}
		return -1;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/ny_cab_data", "root", "root");
	}

	// @Override
	// protected void finalize() throws Throwable {
	// // TODO Auto-generated method stub
	// super.finalize();
	// connection.close();
	// }

	public static Date getDateByString(String Date) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = sdf1.parse(Date);
		return new java.sql.Date(date.getTime());
	}

	// public static void main(String[] args) throws SQLException {
	// Connection connection = getConnection();
	// System.out.println(connection);
	// }

}
