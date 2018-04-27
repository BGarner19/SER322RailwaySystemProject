package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.Ticket;

public class TicketLibrary {

	//Library Data Structure
	public static LinkedList<Ticket> ticketList = new LinkedList<Ticket>();
	
	//Import constructor
	public TicketLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		ticketList = new LinkedList<Ticket>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				Ticket ticketObject = new Ticket((JSONObject)jsonObject.getJSONObject(objectName[i]));
				ticketList.add(ticketObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public TicketLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<ticketList.size();i++) {
			JSONObject tempJson = new JSONObject();
			Ticket temp = ticketList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("TypeID", temp.TypeID);
			tempJson.put("scheduleID", temp.ScheduleID);

			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("ticket.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
