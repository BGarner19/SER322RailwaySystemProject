package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Location;

public class LocationLibrary {

	//Library Data Structure
	public static LinkedList<Location> locationList = new LinkedList<Location>();
	
	//Import constructor
	public LocationLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		locationList = new LinkedList<Location>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Location locationObject = new Location((JSONObject)jsonObject.getJSONObject(objectName[i]));
				locationList.add(locationObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public LocationLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<locationList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Location temp = locationList.get(i);
			tempJson.put("Address", temp.Address);
			tempJson.put("StationID", temp.StationID);
			
			exportJson.put(Integer.toString(temp.StationID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("location.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
