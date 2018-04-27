package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Route {
	public int ID;
	public String Name;
	public int SrcStationID;
	public int DestStationID;
	
	public Route() {
		super();
	}

	public Route(int iD, String name, int srcStationID, int destStationID) {
		super();
		ID = iD;
		Name = name;
		SrcStationID = srcStationID;
		DestStationID = destStationID;
	}

	public Route(JSONObject object) throws JSONException {
		this.ID = object.getInt("ID");
		this.Name = object.getString("Name");
		this.SrcStationID = object.getInt("SrcStationID");
		this.DestStationID = object.getInt("DestStationID");
		
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

	public int getSrcStationID() {
		return SrcStationID;
	}

	public void setSrcStationID(int srcStationID) {
		SrcStationID = srcStationID;
	}

	public int getDestStationID() {
		return DestStationID;
	}

	public void setDestStationID(int destStationID) {
		DestStationID = destStationID;
	}
}