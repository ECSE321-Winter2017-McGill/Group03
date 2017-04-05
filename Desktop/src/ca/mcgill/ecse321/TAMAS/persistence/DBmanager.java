package ca.mcgill.ecse321.TAMAS.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.view.AllJobPostings;
import ca.mcgill.ecse321.TAMAS.view.ShowProgress;

public class DBmanager {
	private static String user = "wzs1234566";
	private static String password = "yuwen120";
	private static String myDriver = "org.gjt.mm.mysql.Driver";
	private static String myUrl = "jdbc:mysql://test.cabyhhnybi2l.us-west-2.rds.amazonaws.com/test";
	private static ManagementSystem ms = new ManagementSystem();

	public static void writeFile(String data) {

		try {
			FileWriter writer = new FileWriter("output/data.xml");
			writer.write(data);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static String getDB() {
		ResultSet rs = null;
		String q = "xml";
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, user, password);
			String query = "SELECT * FROM ";
			query += q;
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		try {
			while (rs.next()) {
				String r = rs.getString("xml");
				return r;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void updateDB(String data) {
		String q = "update xml set xml ='" + data + "' where data='data'";
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
