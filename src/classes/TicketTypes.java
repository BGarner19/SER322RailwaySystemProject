package classes;

public class TicketTypes {
	public int ID;
	public String Type;
	public int Price; // could be string instead?
	
	public TicketTypes() {
		super();
	}

	public TicketTypes(int iD, String type, int price) {
		super();
		ID = iD;
		Type = type;
		Price = price;
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

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}
	
	
}
