package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Ticket {
	public int ID;
	public int TypeID;
	public int ScheduleID;
	
	public Ticket() {
		super();
	}

	public Ticket(int iD, int typeID, int scheduleID) {
		super();
		ID = iD;
		TypeID = typeID;
		ScheduleID = scheduleID;
	}

	public Ticket(JSONObject object) throws JSONException{
		this.ID = object.getInt("ID");
		this.TypeID = object.getInt("TypeID");
		this.ScheduleID = object.getInt("scheduleID");
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTypeID() {
		return TypeID;
	}

	public void setTypeID(int typeID) {
		TypeID = typeID;
	}

	public int getScheduleID() {
		return ScheduleID;
	}

	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}
	
	
}
