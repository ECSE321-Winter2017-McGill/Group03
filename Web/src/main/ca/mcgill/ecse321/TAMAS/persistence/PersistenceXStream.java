package ca.mcgill.ecse321.TAMAS.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public abstract class PersistenceXStream {

	private static XStream xstream = new XStream();
	private static String filename = "output/data.xml";

	public static ManagementSystem initializeModelManager(String fileName) {
		// Initialization for persistence
		ManagementSystem ms;
		// setFilename(fileName);
		setAlias("jobInfo", JobPosting.class);
		DBmanager.writeFile("initialize model manager");
		// load model if exists, create otherwise
		File file = new File(fileName);
		if (file.exists()) {
			System.out.println("File Exists");

			ms = (ManagementSystem) loadFromXMLwithXStream();
		}

		else {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				// System.exit(1);
			}
			ms = (ManagementSystem) loadFromXMLwithXStream();
			saveToXMLwithXStream(ms);
		}
		return ms;

	}

	public static String saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file
		System.out.println(xml);
//		try {
//			DBmanager.updateDB(xml);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(xml);
			writer.close();
			return xml;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
		DBmanager.writeFile(DBmanager.getDB());
		try {
			FileReader fileReader = new FileReader(filename); // load our xml
																// file
			return xstream.fromXML(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

}