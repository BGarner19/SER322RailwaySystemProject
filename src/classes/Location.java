package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Location {
	public String Address;
	public int StationID;
	
	public Location() {
		super();
	}

	public Location(JSONObject object) throws JSONException {
		this.StationID = object.getInt("StationID");
		this.Address = object.getString("Address");
	}
	
	public Location(String address, int stationID) {
		super();
		Address = address;
		StationID = stationID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getStationID() {
		return StationID;
	}

	public void setStationID(int stationID) {
		StationID = stationID;
	}
	
	
}
