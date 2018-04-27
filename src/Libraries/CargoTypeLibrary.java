package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.CargoType;

public class CargoTypeLibrary {

	//Library Data Structure
	public static LinkedList<CargoType> cargoTypeList = new LinkedList<CargoType>();
	
	//Import constructor
	public CargoTypeLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		cargoTypeList = new LinkedList<CargoType>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				CargoType cargoTypeObject = new CargoType((JSONObject)jsonObject.getJSONObject(objectName[i]));
				cargoTypeList.add(cargoTypeObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public CargoTypeLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<cargoTypeList.size();i++) {
			JSONObject tempJson = new JSONObject();
			CargoType temp = cargoTypeList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("Type", temp.Type);
			
			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("cargoType.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
