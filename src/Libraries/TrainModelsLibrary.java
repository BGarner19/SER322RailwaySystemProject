package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.TrainModels;

public class TrainModelsLibrary {

	//Library Data Structure
	public static LinkedList<TrainModels> trainModelsList = new LinkedList<TrainModels>();
	
	//Import constructor
	public TrainModelsLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		trainModelsList = new LinkedList<TrainModels>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				TrainModels trainModelsObject = new TrainModels((JSONObject)jsonObject.getJSONObject(objectName[i]));
				trainModelsList.add(trainModelsObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public TrainModelsLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<trainModelsList.size();i++) {
			JSONObject tempJson = new JSONObject();
			TrainModels temp = trainModelsList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("Name", temp.Name);
			tempJson.put("Weight", temp.Weight);
			tempJson.put("CargoID", temp.CargoID);
			tempJson.put("NumberOfCars", temp.NumberOfCars);
			tempJson.put("Capacity", temp.Capacity);
			tempJson.put("WeightLimit", temp.WeightLimit);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("trainModels.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
