package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Route;

public class RouteLibrary {

	//Library Data Structure
	public static LinkedList<Route> routeList = new LinkedList<Route>();
	
	//Import constructor
	public RouteLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		routeList = new LinkedList<Route>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Route passengerObject = new Route((JSONObject)jsonObject.getJSONObject(objectName[i]));
				routeList.add(passengerObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public RouteLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<routeList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Route temp = routeList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("Name", temp.Name);
			tempJson.put("SrcStationID", temp.SrcStationID);
			tempJson.put("DestStationID", temp.DestStationID);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("route.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
