package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Passenger;

public class PassengerLibrary {

	//Library Data Structure
	public static LinkedList<Passenger> passengerList = new LinkedList<Passenger>();
	
	//Import constructor
	public PassengerLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		passengerList = new LinkedList<Passenger>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Passenger passengerObject = new Passenger((JSONObject)jsonObject.getJSONObject(objectName[i]));
				passengerList.add(passengerObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public PassengerLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<passengerList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Passenger temp = passengerList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("TicketID", temp.TicketID);
			tempJson.put("FirstName", temp.FirstName);
			tempJson.put("LastName", temp.LastName);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("passenger.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
