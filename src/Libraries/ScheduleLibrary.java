package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Schedule;

public class ScheduleLibrary {

	//Library Data Structure
	public static LinkedList<Schedule> scheduleList = new LinkedList<Schedule>();
	
	//Import constructor
	public ScheduleLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		scheduleList = new LinkedList<Schedule>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Schedule passengerObject = new Schedule((JSONObject)jsonObject.getJSONObject(objectName[i]));
				scheduleList.add(passengerObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public ScheduleLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<scheduleList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Schedule temp = scheduleList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("trainID", temp.trainID);
			tempJson.put("routeID", temp.routeID);
			tempJson.put("departTime", temp.departTime);
			tempJson.put("arriveTime", temp.arriveTime);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("schedule.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
