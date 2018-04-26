package classes;

public class CargoType {
	public int ID;
	public String Type;
	
	public CargoType() {
		super();
	}

	public CargoType(int iD, String type) {
		super();
		ID = iD;
		Type = type;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}