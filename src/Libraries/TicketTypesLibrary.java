package Libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import classes.TicketTypes;

public class TicketTypesLibrary {

	//Library Data Structure
	public static LinkedList<TicketTypes> ticketTypesList = new LinkedList<TicketTypes>();
	
	//Import constructor
	public TicketTypesLibrary(String location) throws IOException, JSONException {
		FileInputStream file = new FileInputStream(location);
		JSONObject jsonObject = new JSONObject(new JSONTokener(file));
		String[] objectName = JSONObject.getNames(jsonObject);
		
		ticketTypesList = new LinkedList<TicketTypes>();
		for(int i=0; i<objectName.length; i++) {
			if(!objectName[i].equals(null)) {
				TicketTypes ticketTypesObject = new TicketTypes((JSONObject)jsonObject.getJSONObject(objectName[i]));
				ticketTypesList.add(ticketTypesObject);
			}
			
		}
		file.close();
		
		
		
		
	}
	
	//Default
	public TicketTypesLibrary() {
		
	};
	
	
	//Export
	public void ExportJSON() throws JSONException {
		JSONObject exportJson = new JSONObject();
		for(int i= 0; i<ticketTypesList.size();i++) {
			JSONObject tempJson = new JSONObject();
			TicketTypes temp = ticketTypesList.get(i);
			tempJson.put("ID", temp.ID);
			tempJson.put("Type", temp.Type);
			tempJson.put("Price", temp.Price);

			exportJson.put(Integer.toString(temp.ID), tempJson);
		}
		
		try {
			PrintWriter export = new PrintWriter("ticketTypes.json");
			export.println(exportJson.toString(1));
			export.close();
		}catch(FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
