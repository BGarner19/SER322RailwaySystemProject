package classes;

public class TrainModels {
	public int ID;
	public String Name;
	public int Weight;
	public int CargoID;
	public int NumberOfCars;
	public int Capacity;
	public int WeightLimit;
	
	public TrainModels() {
		super();
	}

	public TrainModels(int iD, String name, int weight, int cargoID, int numberOfCars, int capacity, int weightLimit) {
		super();
		ID = iD;
		Name = name;
		Weight = weight;
		CargoID = cargoID;
		NumberOfCars = numberOfCars;
		Capacity = capacity;
		WeightLimit = weightLimit;
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

	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	public int getCargoID() {
		return CargoID;
	}

	public void setCargoID(int cargoID) {
		CargoID = cargoID;
	}

	public int getNumberOfCars() {
		return NumberOfCars;
	}

	public void setNumberOfCars(int numberOfCars) {
		NumberOfCars = numberOfCars;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}

	public int getWeightLimit() {
		return WeightLimit;
	}

	public void setWeightLimit(int weightLimit) {
		WeightLimit = weightLimit;
	}
}