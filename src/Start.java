import ui.TrainDatabaseGUI;

public class Start {
	
	public static void main(String args[]) {
		Database db = new Database(5432, "Team6RailwayDB");
		new TrainDatabaseGUI();
	}
}
