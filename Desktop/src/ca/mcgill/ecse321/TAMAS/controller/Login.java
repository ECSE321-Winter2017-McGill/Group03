// This class is used for login purposes, it connects to a mysql database and checks the username and password.


package ca.mcgill.ecse321.TAMAS.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	private static String user = "wzs1234566";
	private static String password = "yuwen120";
	private static String myDriver = "org.gjt.mm.mysql.Driver";
	private static String myUrl = "jdbc:mysql://test.cabyhhnybi2l.us-west-2.rds.amazonaws.com/test";
	@SuppressWarnings("unused")
	private static ResultSet getDB(String q) {
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, user, password);
			String query = "SELECT * FROM ";
			query += q;
			Statement st = conn.createStatement();
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unused")
	private static void updateDB(String q) {
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, user, password);
			Statement st = conn.createStatement();
			st.executeUpdate(q);
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

}
