package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Passenger {
	public int ID;
	public int TicketID;
	public String FirstName;
	public String LastName;
	
	public Passenger() {
		super();
	}

	public Passenger(int iD, int ticketID, String firstName, String lastName) {
		super();
		ID = iD;
		TicketID = ticketID;
		FirstName = firstName;
		LastName = lastName;
	}

	public Passenger(JSONObject object) throws JSONException {
		this.ID = object.getInt("ID");
		this.TicketID = object.getInt("TicketID");
		this.FirstName = object.getString("FirstName");
		this.LastName = object.getString("LastName");
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTicketID() {
		return TicketID;
	}

	public void setTicketID(int ticketID) {
		TicketID = ticketID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	
}
