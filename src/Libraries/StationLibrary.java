package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Station;

public class StationLibrary {

	//Library Data Structure
	public static LinkedList<Station> stationList = new LinkedList<Station>();
	
	//Import constructor
	public StationLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		stationList = new LinkedList<Station>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Station stationObject = new Station((JSONObject)jsonObject.getJSONObject(objectName[i]));
				stationList.add(stationObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public StationLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<stationList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Station temp = stationList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("Name", temp.Name);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("station.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
