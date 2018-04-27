package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import classes.*;

public class TrainDatabaseGUI {
	
	public TrainDatabaseGUI() {
		init();
	}
	
	// base GUI stuff
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel(new GridLayout(10,11));
	
	// all panel creations (add with each new class + add panel section)
	
	JPanel cargoTypePanel = new JPanel(new GridLayout(2,11));
	JPanel trainModelsPanel = new JPanel(new GridLayout(2,11));
	JPanel trainPanel = new JPanel(new GridLayout(2,11));
	JPanel schedulePanel = new JPanel(new GridLayout(2,11));
	JPanel routePanel = new JPanel(new GridLayout(2,11));
	JPanel stationPanel = new JPanel(new GridLayout(2,11));
	JPanel locationPanel = new JPanel(new GridLayout(2,11));
	JPanel ticketPanel = new JPanel(new GridLayout(2,11));
	JPanel ticketTypesPanel = new JPanel(new GridLayout(2,11));
	JPanel passengerPanel = new JPanel(new GridLayout(2,11));
	
	// cargoType panel stuff
	
	JComboBox cargoTypeDrop = new JComboBox();	
	JButton cargoTypeSearch = new JButton("Search");
	JButton cargoTypeAdd = new JButton("Add");	
	JTextField cargoTypeIDField = new JTextField();
	JTextField cargoTypeTypeField = new JTextField();
	
	// trainModels panel stuff
	
	JComboBox<TrainModels> trainModelsDrop = new JComboBox<TrainModels>();	
	JButton trainModelsSearch = new JButton("Search");
	JButton trainModelsAdd = new JButton("Add");	
	JTextField trainModelsIDField = new JTextField();
	JTextField trainModelsNameField = new JTextField();
	JTextField trainModelsWeightField = new JTextField();
	JTextField trainModelsCargoIDField = new JTextField();
	JTextField trainModelsNumberOfCarsField = new JTextField();
	JTextField trainModelsCapacityField = new JTextField();
	JTextField trainModelsWeightLimitField = new JTextField();
	
	// train panel stuff
	
	JComboBox<Train> trainDrop = new JComboBox<Train>();	
	JButton trainSearch = new JButton("Search");
	JButton trainAdd = new JButton("Add");	
	JTextField trainIDField = new JTextField();
	JTextField trainNameField = new JTextField();
	JTextField trainModelIDField = new JTextField();
	
	// schedule panel stuff
	
	JComboBox<Schedule> scheduleDrop = new JComboBox<Schedule>();	
	JButton scheduleSearch = new JButton("Search");
	JButton scheduleAdd = new JButton("Add");	
	JTextField scheduleIDField = new JTextField();
	JTextField scheduleTrainIDField = new JTextField();
	JTextField scheduleRouteIDField = new JTextField();
	JTextField scheduleDepartTimeField = new JTextField();
	JTextField scheduleArriveTimeField = new JTextField();
	
	// route panel stuff
	
	JComboBox<Route> routeDrop = new JComboBox<Route>();	
	JButton routeSearch = new JButton("Search");
	JButton routeAdd = new JButton("Add");	
	JTextField routeIDField = new JTextField();
	JTextField routeNameField = new JTextField();
	JTextField routeSrcStationIDField = new JTextField();
	JTextField routeDestStationIDField = new JTextField();
	
	// station panel stuff
	
	JComboBox<Station> stationDrop = new JComboBox<Station>();	
	JButton stationSearch = new JButton("Search");
	JButton stationAdd = new JButton("Add");	
	JTextField stationIDField = new JTextField();
	JTextField stationNameField = new JTextField();
	
	// location panel stuff
	
	JComboBox<Location> locationDrop = new JComboBox<Location>();	
	JButton locationSearch = new JButton("Search");
	JButton locationAdd = new JButton("Add");	
	JTextField locationAddressField = new JTextField();
	JTextField locationStationIDField = new JTextField();
	
	// ticket panel stuff
	
	JComboBox<Ticket> ticketDrop = new JComboBox<Ticket>();	
	JButton ticketSearch = new JButton("Search");
	JButton ticketAdd = new JButton("Add");	
	JTextField ticketIDField = new JTextField();
	JTextField ticketTypeIDField = new JTextField();
	JTextField ticketScheduleIDField = new JTextField();
	
	// ticketTypes panel stuff
	
	JComboBox<TicketTypes> ticketTypesDrop = new JComboBox<TicketTypes>();	
	JButton ticketTypesSearch = new JButton("Search");
	JButton ticketTypesAdd = new JButton("Add");	
	JTextField ticketTypesIDField = new JTextField();
	JTextField ticketTypesTypeField = new JTextField();
	JTextField ticketTypesPriceField = new JTextField();
	
	// passenger panel stuff
	
	JComboBox<Passenger> passengerDrop = new JComboBox<Passenger>();	
	JButton passengerSearch = new JButton("Search");
	JButton passengerAdd = new JButton("Add");	
	JTextField passengerIDField = new JTextField();
	JTextField passengerTicketIDField = new JTextField();
	JTextField passengerFirstNameField = new JTextField();
	JTextField passengerLastNameField = new JTextField();
	
	// setting up panels

	void init() {
		
		// cargoType panel setup
		
		cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel("ID", JLabel.CENTER)); cargoTypePanel.add(new JLabel("Type", JLabel.CENTER)); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel());
		cargoTypePanel.add(new JLabel("Cargo Type", JLabel.CENTER)); cargoTypePanel.add(cargoTypeIDField); cargoTypePanel.add(cargoTypeTypeField); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(cargoTypeDrop); cargoTypePanel.add(cargoTypeSearch); cargoTypePanel.add(cargoTypeAdd);		
		
		cargoTypeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		cargoTypeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
// testing list selection (will work much better with json but this gives a good idea of how it will work)
		
		CargoType test = new CargoType(2, "Passenger");
		cargoTypeDrop.addItem("Select From...");
		cargoTypeDrop.addItem(test);
		
		cargoTypeDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if (cargoTypeDrop.getSelectedItem().equals("Select From...")) {
						cargoTypeIDField.setText("");
						cargoTypeTypeField.setText("");
					}
					else {
						CargoType hold = (CargoType) cargoTypeDrop.getSelectedItem();
						cargoTypeIDField.setText(Integer.toString(hold.ID));
						cargoTypeTypeField.setText(hold.Type);
					}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
// end test
		
		// trainModels panel setup
				
		trainModelsPanel.add(new JLabel()); trainModelsPanel.add(new JLabel("ID", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Name", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Weight", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Cargo ID", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Number of Cars", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Capacity", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Weight Limit", JLabel.CENTER)); trainModelsPanel.add(new JLabel()); trainModelsPanel.add(new JLabel()); trainModelsPanel.add(new JLabel());
		trainModelsPanel.add(new JLabel("Train Models", JLabel.CENTER)); trainModelsPanel.add(trainModelsIDField); trainModelsPanel.add(trainModelsNameField); trainModelsPanel.add(trainModelsWeightField); trainModelsPanel.add(trainModelsCargoIDField); trainModelsPanel.add(trainModelsNumberOfCarsField); trainModelsPanel.add(trainModelsCapacityField); trainModelsPanel.add(trainModelsWeightLimitField); trainModelsPanel.add(trainModelsDrop); trainModelsPanel.add(trainModelsSearch); trainModelsPanel.add(trainModelsAdd);
		
		trainModelsSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		trainModelsAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// train panel setup
		
		trainPanel.add(new JLabel()); trainPanel.add(new JLabel("ID", JLabel.CENTER)); trainPanel.add(new JLabel("Name", JLabel.CENTER)); trainPanel.add(new JLabel("Model ID", JLabel.CENTER)); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel());
		trainPanel.add(new JLabel("Train", JLabel.CENTER)); trainPanel.add(trainIDField); trainPanel.add(trainNameField); trainPanel.add(trainModelIDField); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(new JLabel()); trainPanel.add(trainDrop); trainPanel.add(trainSearch); trainPanel.add(trainAdd);

		trainSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		trainAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// schedule panel setup
		
		schedulePanel.add(new JLabel()); schedulePanel.add(new JLabel("ID", JLabel.CENTER)); schedulePanel.add(new JLabel("Train ID", JLabel.CENTER)); schedulePanel.add(new JLabel("Route ID", JLabel.CENTER)); schedulePanel.add(new JLabel("Depart Time", JLabel.CENTER)); schedulePanel.add(new JLabel("Arrive Time", JLabel.CENTER)); schedulePanel.add(new JLabel()); schedulePanel.add(new JLabel()); schedulePanel.add(new JLabel()); schedulePanel.add(new JLabel()); schedulePanel.add(new JLabel());
		schedulePanel.add(new JLabel("Schedule", JLabel.CENTER)); schedulePanel.add(scheduleIDField); schedulePanel.add(scheduleTrainIDField); schedulePanel.add(scheduleRouteIDField); schedulePanel.add(scheduleDepartTimeField); schedulePanel.add(scheduleArriveTimeField); schedulePanel.add(new JLabel()); schedulePanel.add(new JLabel()); schedulePanel.add(scheduleDrop); schedulePanel.add(scheduleSearch); schedulePanel.add(scheduleAdd);
		
		scheduleSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		scheduleAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// route panel setup
		
		routePanel.add(new JLabel()); routePanel.add(new JLabel("ID", JLabel.CENTER)); routePanel.add(new JLabel("Name", JLabel.CENTER)); routePanel.add(new JLabel("Source Station ID", JLabel.CENTER)); routePanel.add(new JLabel("Destination Station ID", JLabel.CENTER)); routePanel.add(new JLabel()); routePanel.add(new JLabel()); routePanel.add(new JLabel()); routePanel.add(new JLabel()); routePanel.add(new JLabel()); routePanel.add(new JLabel());
		routePanel.add(new JLabel("Route", JLabel.CENTER)); routePanel.add(routeIDField); routePanel.add(routeNameField); routePanel.add(routeSrcStationIDField); routePanel.add(routeDestStationIDField); routePanel.add(new JLabel()); routePanel.add(new JLabel()); routePanel.add(new JLabel()); routePanel.add(routeDrop); routePanel.add(routeSearch); routePanel.add(routeAdd);
		
		routeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		routeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// station panel setup
		
		stationPanel.add(new JLabel()); stationPanel.add(new JLabel("ID", JLabel.CENTER)); stationPanel.add(new JLabel("Name", JLabel.CENTER)); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel());
		stationPanel.add(new JLabel("Station", JLabel.CENTER)); stationPanel.add(stationIDField); stationPanel.add(stationNameField); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(new JLabel()); stationPanel.add(stationDrop); stationPanel.add(stationSearch); stationPanel.add(stationAdd);
		
		stationSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		stationAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// location panel setup
		
		locationPanel.add(new JLabel()); locationPanel.add(new JLabel("Address", JLabel.CENTER)); locationPanel.add(new JLabel("Station ID", JLabel.CENTER)); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel());
		locationPanel.add(new JLabel("Location", JLabel.CENTER)); locationPanel.add(locationAddressField); locationPanel.add(locationStationIDField); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(new JLabel()); locationPanel.add(locationDrop); locationPanel.add(locationSearch); locationPanel.add(locationAdd);
		
		locationSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		locationAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// ticket panel setup
		
		ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel("ID", JLabel.CENTER)); ticketPanel.add(new JLabel("Type ID", JLabel.CENTER)); ticketPanel.add(new JLabel("Schedule ID", JLabel.CENTER)); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel());
		ticketPanel.add(new JLabel("Ticket", JLabel.CENTER)); ticketPanel.add(ticketIDField); ticketPanel.add(ticketTypeIDField); ticketPanel.add(ticketScheduleIDField); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(new JLabel()); ticketPanel.add(ticketDrop); ticketPanel.add(ticketSearch); ticketPanel.add(ticketAdd);

		ticketSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		ticketAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// ticketType panel setup
		
		ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel("ID", JLabel.CENTER)); ticketTypesPanel.add(new JLabel("Type", JLabel.CENTER)); ticketTypesPanel.add(new JLabel("Price", JLabel.CENTER)); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel());
		ticketTypesPanel.add(new JLabel("Ticket Types", JLabel.CENTER)); ticketTypesPanel.add(ticketTypesIDField); ticketTypesPanel.add(ticketTypesTypeField); ticketTypesPanel.add(ticketTypesPriceField); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(new JLabel()); ticketTypesPanel.add(ticketTypesDrop); ticketTypesPanel.add(ticketTypesSearch); ticketTypesPanel.add(ticketTypesAdd);

		ticketTypesSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		ticketTypesAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// passenger panel setup
		
		passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel("ID", JLabel.CENTER)); passengerPanel.add(new JLabel("Ticket ID", JLabel.CENTER)); passengerPanel.add(new JLabel("First Name", JLabel.CENTER)); passengerPanel.add(new JLabel("Last Name", JLabel.CENTER)); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel());
		passengerPanel.add(new JLabel("Passenger", JLabel.CENTER)); passengerPanel.add(passengerIDField); passengerPanel.add(passengerTicketIDField); passengerPanel.add(passengerFirstNameField); passengerPanel.add(passengerLastNameField); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel()); passengerPanel.add(new JLabel()); passengerPanel.add(passengerDrop); passengerPanel.add(passengerSearch); passengerPanel.add(passengerAdd);
	
		passengerSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		passengerAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// actual panel setup (add with each new class)
		
		panel.add(cargoTypePanel);
		panel.add(trainModelsPanel);
		panel.add(trainPanel);
		panel.add(schedulePanel);
		panel.add(routePanel);
		panel.add(stationPanel);
		panel.add(locationPanel);
		panel.add(ticketPanel);
		panel.add(ticketTypesPanel);
		panel.add(passengerPanel);
		
		frame.add(panel);
		
		// visibility (add with each new class)
		
		cargoTypePanel.setVisible(true);
		trainModelsPanel.setVisible(true);
		trainPanel.setVisible(true);
		schedulePanel.setVisible(true);
		routePanel.setVisible(true);
		stationPanel.setVisible(true);
		locationPanel.setVisible(true);
		ticketPanel.setVisible(true);
		ticketTypesPanel.setVisible(true);
		passengerPanel.setVisible(true);
		
		panel.setVisible(true);
		
		// frame setup
		
		frame.setVisible(true);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(1500,800);
		frame.setLocation(200, 100);
	}
}
