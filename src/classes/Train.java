package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Train {
	public int ID;
	public String name;
	public int modelID;

	public Train() {
		super();
	}

	public Train(int iD, String name, int modelID) {
		super();
		ID = iD;
		this.name = name;
		this.modelID = modelID;
	}

	public Train(JSONObject object) throws JSONException{
		this.ID = object.getInt("ID");
		this.name = object.getString("name");
		this.modelID = object.getInt("modelID");
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getModelID() {
		return modelID;
	}

	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
}