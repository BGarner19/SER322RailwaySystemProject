package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Station {
	public int ID;
	public String Name;
	
	public Station() {
		super();
	}
	
	public Station(int iD, String name) {
		super();
		ID = iD;
		Name = name;
	}
	
	public Station(JSONObject object) throws JSONException {
		this.ID = object.getInt("ID");
		this.Name = object.getString("Name");
	}
	
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}	
}