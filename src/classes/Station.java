package classes;

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