package classes;

public class Location {
	public String Address;
	public int StationID;
	
	public Location() {
		super();
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
