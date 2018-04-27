package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Train;

public class TrainLibrary {

	//Library Data Structure
	public static LinkedList<Train> trainList = new LinkedList<Train>();
	
	//Import constructor
	public TrainLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		trainList = new LinkedList<Train>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Train trainObject = new Train((JSONObject)jsonObject.getJSONObject(objectName[i]));
				trainList.add(trainObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public TrainLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<trainList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Train temp = trainList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("name", temp.name);
			tempJson.put("modelID", temp.modelID);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("train.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
