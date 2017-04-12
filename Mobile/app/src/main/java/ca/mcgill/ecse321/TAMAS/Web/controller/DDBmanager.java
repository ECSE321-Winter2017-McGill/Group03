package ca.mcgill.ecse321.TAMAS.Web.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.tamas_mobile.AsyncResponse;
import ca.mcgill.ecse321.tamas_mobile.Parameters;

public class DDBmanager extends AsyncTask<Parameters, Void, String> {
	private static Context context;
	private static String user = "wzs1234566";
	private static String password = "yuwen120";
	private static String myDriver = "org.gjt.mm.mysql.Driver";
	private static String myUrl = "jdbc:mysql://test.cabyhhnybi2l.us-west-2.rds.amazonaws.com/test";
	private static ManagementSystem ms = new ManagementSystem();
	public AsyncResponse delegate = null;
	@Override
	protected void onPostExecute(String result) {
		delegate.processFinish(result);
	}
	@Override
	protected String doInBackground(Parameters... ctx) {
		this.context=ctx[0].c;
		if(ctx[0].option==0) {
			return this.getDB();
		}else{
			return this.updateDB(ctx[0].ms);
		}
	}
	private void writeFile(String data) {
		String filePath = context.getFilesDir().getPath().toString() + "/data.xml";
		File f=new File(filePath);
		if (!f.exists()) {
			try {
				f.createNewFile();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		String string = data;
		FileOutputStream outputStream;
		try {
			outputStream =new FileOutputStream (f);
			outputStream.write(string.getBytes());
			System.out.println(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sss"+f.exists());
	}

	private String getDB() {
		System.out.println("Get DB");
		String q = "xml";
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, user, password);
			String query = "SELECT * FROM ";
			query += q;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String r = rs.getString("xml");
				System.out.println(r);
				return r;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private String updateDB(ManagementSystem ms) {
		XStream xstream = new XStream();
		xstream.alias("jobInfo", JobPosting.class);
		xstream.setMode(XStream.ID_REFERENCES);
		String data=xstream.toXML(ms); // save our xml file
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
			return getDB();
		}
		return getDB();
	}
}
