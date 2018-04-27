package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Schedule {
	public int ID;
	public int trainID;
	public int routeID;
	public String departTime;
	public String arriveTime;
	
	public Schedule() {
		super();
	}

	public Schedule(int iD, int trainID, int routeID, String departTime, String arriveTime) {
		super();
		ID = iD;
		this.trainID = trainID;
		this.routeID = routeID;
		this.departTime = departTime;
		this.arriveTime = arriveTime;
	}

	public Schedule(JSONObject object) throws JSONException{
		this.ID = object.getInt("ID");
		this.trainID = object.getInt("trainID");
		this.routeID = object.getInt("routeID");
		this.departTime = object.getString("departTime");
		this.arriveTime = object.getString("arriveTime");
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
}