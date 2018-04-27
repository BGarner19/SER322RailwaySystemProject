package ui;

import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import classes.*;
import database.Database;
import Libraries.*;


public class TrainDatabaseGUI {

    private Database database;

    private CargoTypeLibrary cargoTypeLibrary;
    private LocationLibrary locationLibrary;
    private PassengerLibrary passengerLibrary;
    private RouteLibrary routeLibrary;
    private ScheduleLibrary scheduleLibrary;
    private StationLibrary stationLibrary;
    private TicketLibrary ticketLibrary;
    private TicketTypesLibrary ticketTypesLibrary;
    private TrainLibrary trainLibrary;
    private TrainModelsLibrary trainModelsLibrary;
    
    
    
    
    
	public TrainDatabaseGUI() throws IOException, JSONException {
	    //database = new Database(5432, "Team6RailwayDB", "postgres", "322");
	    cargoTypeLibrary = new CargoTypeLibrary("cargoType.json");
	    locationLibrary = new LocationLibrary("location.json");
	    passengerLibrary = new PassengerLibrary("passenger.json");
	    routeLibrary = new RouteLibrary("route.json");
	    scheduleLibrary = new ScheduleLibrary("schedule.json");
	    stationLibrary = new StationLibrary("station.json");
	    ticketLibrary = new TicketLibrary("ticket.json");
	    ticketTypesLibrary = new TicketTypesLibrary("ticketTypes.json");
	    trainLibrary = new TrainLibrary("train.json");
	    trainModelsLibrary = new TrainModelsLibrary("trainModels.json");

		init();
	}
	
	// base GUI stuff
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel(new GridLayout(11,11));
	JPanel wholePanel = new JPanel(new BorderLayout());
	
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
	JPanel bufferPanel = new JPanel(new GridLayout(1,0));
	JPanel queryPanel = new JPanel(new GridLayout(1, 2));
	
	// cargoType panel stuff
	
	JComboBox cargoTypeDrop = new JComboBox<CargoType>();	
	JButton cargoTypeSearch = new JButton("Search");
	JButton cargoTypeAdd = new JButton("Add");	
	JTextField cargoTypeIDField = new JTextField();
	JTextField cargoTypeTypeField = new JTextField();
	
	// trainModels panel stuff
	
	JComboBox trainModelsDrop = new JComboBox<TrainModels>();	
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
	
	JComboBox trainDrop = new JComboBox<Train>();	
	JButton trainSearch = new JButton("Search");
	JButton trainAdd = new JButton("Add");	
	JTextField trainIDField = new JTextField();
	JTextField trainNameField = new JTextField();
	JTextField trainModelIDField = new JTextField();
	
	// schedule panel stuff
	
	JComboBox scheduleDrop = new JComboBox<Schedule>();	
	JButton scheduleSearch = new JButton("Search");
	JButton scheduleAdd = new JButton("Add");	
	JTextField scheduleIDField = new JTextField();
	JTextField scheduleTrainIDField = new JTextField();
	JTextField scheduleRouteIDField = new JTextField();
	JTextField scheduleDepartTimeField = new JTextField();
	JTextField scheduleArriveTimeField = new JTextField();
	
	// route panel stuff
	
	JComboBox routeDrop = new JComboBox<Route>();	
	JButton routeSearch = new JButton("Search");
	JButton routeAdd = new JButton("Add");	
	JTextField routeIDField = new JTextField();
	JTextField routeNameField = new JTextField();
	JTextField routeSrcStationIDField = new JTextField();
	JTextField routeDestStationIDField = new JTextField();
	
	// station panel stuff
	
	JComboBox stationDrop = new JComboBox<Station>();	
	JButton stationSearch = new JButton("Search");
	JButton stationAdd = new JButton("Add");	
	JTextField stationIDField = new JTextField();
	JTextField stationNameField = new JTextField();
	
	// location panel stuff
	
	JComboBox locationDrop = new JComboBox<Location>();	
	JButton locationSearch = new JButton("Search");
	JButton locationAdd = new JButton("Add");	
	JTextField locationAddressField = new JTextField();
	JTextField locationStationIDField = new JTextField();
	
	// ticket panel stuff
	
	JComboBox ticketDrop = new JComboBox<Ticket>();	
	JButton ticketSearch = new JButton("Search");
	JButton ticketAdd = new JButton("Add");	
	JTextField ticketIDField = new JTextField();
	JTextField ticketTypeIDField = new JTextField();
	JTextField ticketScheduleIDField = new JTextField();
	
	// ticketTypes panel stuff
	
	JComboBox ticketTypesDrop = new JComboBox<TicketTypes>();	
	JButton ticketTypesSearch = new JButton("Search");
	JButton ticketTypesAdd = new JButton("Add");	
	JTextField ticketTypesIDField = new JTextField();
	JTextField ticketTypesTypeField = new JTextField();
	JTextField ticketTypesPriceField = new JTextField();
	
	// passenger panel stuff
	
	JComboBox passengerDrop = new JComboBox<Passenger>();	
	JButton passengerSearch = new JButton("Search");
	JButton passengerAdd = new JButton("Add");	
	JTextField passengerIDField = new JTextField();
	JTextField passengerTicketIDField = new JTextField();
	JTextField passengerFirstNameField = new JTextField();
	JTextField passengerLastNameField = new JTextField();

	//Query Panel stuff

    JTextArea queryArea = new JTextArea();
    JButton queryButton = new JButton("Query Database");
    JDialog queryPopout = new JDialog();
    JPanel queryFramePanel = new JPanel();

	// setting up panels

	void init() {
		
		// combobox setups
		
		cargoTypeDrop.addItem("Select From...");
		trainModelsDrop.addItem("Select From...");
		trainDrop.addItem("Select From...");
		scheduleDrop.addItem("Select From...");
		routeDrop.addItem("Select From...");
		stationDrop.addItem("Select From...");
		locationDrop.addItem("Select From...");
		ticketDrop.addItem("Select From...");
		ticketTypesDrop.addItem("Select From...");
		passengerDrop.addItem("Select From...");
		
				// change the last word in each for loop to determine what shows up
				// in the dropdown (may need to change the itemlistener for the
				// corresponding dropdown a little)
		
		for (int i = 0; i < cargoTypeLibrary.cargoTypeList.size(); ++i) 
			cargoTypeDrop.addItem(cargoTypeLibrary.cargoTypeList.get(i).Type);
		for (int i = 0; i < trainModelsLibrary.trainModelsList.size(); ++i)
			trainModelsDrop.addItem(trainModelsLibrary.trainModelsList.get(i).Name);
		for (int i = 0; i < trainLibrary.trainList.size(); ++i)
			trainDrop.addItem(trainLibrary.trainList.get(i).name);
		for (int i = 0; i < scheduleLibrary.scheduleList.size(); ++i)
			scheduleDrop.addItem(scheduleLibrary.scheduleList.get(i).ID);
		for (int i = 0; i < routeLibrary.routeList.size(); ++i)
			routeDrop.addItem(routeLibrary.routeList.get(i).Name);
		for (int i = 0; i < stationLibrary.stationList.size(); ++i)
			stationDrop.addItem(stationLibrary.stationList.get(i).Name);
		for (int i = 0; i < locationLibrary.locationList.size(); ++i)
			locationDrop.addItem(locationLibrary.locationList.get(i).Address);
		for (int i = 0; i < ticketLibrary.ticketList.size(); ++i)
			ticketDrop.addItem(ticketLibrary.ticketList.get(i).ID);
		for (int i = 0; i < ticketTypesLibrary.ticketTypesList.size(); ++i)
			ticketTypesDrop.addItem(ticketTypesLibrary.ticketTypesList.get(i).Type);
		for (int i = 0; i < passengerLibrary.passengerList.size(); ++i)
			passengerDrop.addItem(passengerLibrary.passengerList.get(i).ID);
		
		// cargoType panel setup
		
		cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel("ID", JLabel.CENTER)); cargoTypePanel.add(new JLabel("Type", JLabel.CENTER)); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel());
		cargoTypePanel.add(new JLabel("Cargo Type", JLabel.CENTER)); cargoTypePanel.add(cargoTypeIDField); cargoTypePanel.add(cargoTypeTypeField); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(new JLabel()); cargoTypePanel.add(cargoTypeDrop); cargoTypePanel.add(cargoTypeSearch); cargoTypePanel.add(cargoTypeAdd);		
		
		cargoTypeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		cargoTypeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargoType c = new CargoType((Integer.parseInt(cargoTypeIDField.getText())), cargoTypeTypeField.getText());
				cargoTypeLibrary.cargoTypeList.add(c);
				try {
					cargoTypeLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		cargoTypeDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if (cargoTypeDrop.getSelectedItem().equals("Select From...")) {
						//cargoTypeIDField.setText("");
						//cargoTypeTypeField.setText("");
					}
					else {
						for (int i = 0; i < cargoTypeLibrary.cargoTypeList.size(); ++i) {
							if (cargoTypeLibrary.cargoTypeList.get(i).Type.equals(cargoTypeDrop.getSelectedItem())) {
								cargoTypeIDField.setText(Integer.toString(cargoTypeLibrary.cargoTypeList.get(i).ID));
								cargoTypeTypeField.setText(cargoTypeLibrary.cargoTypeList.get(i).Type);
							}
						}
					}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		// trainModels panel setup
				
		trainModelsPanel.add(new JLabel()); trainModelsPanel.add(new JLabel("ID", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Name", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Weight", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Cargo ID", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Number of Cars", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Capacity", JLabel.CENTER)); trainModelsPanel.add(new JLabel("Weight Limit", JLabel.CENTER)); trainModelsPanel.add(new JLabel()); trainModelsPanel.add(new JLabel()); trainModelsPanel.add(new JLabel());
		trainModelsPanel.add(new JLabel("Train Models", JLabel.CENTER)); trainModelsPanel.add(trainModelsIDField); trainModelsPanel.add(trainModelsNameField); trainModelsPanel.add(trainModelsWeightField); trainModelsPanel.add(trainModelsCargoIDField); trainModelsPanel.add(trainModelsNumberOfCarsField); trainModelsPanel.add(trainModelsCapacityField); trainModelsPanel.add(trainModelsWeightLimitField); trainModelsPanel.add(trainModelsDrop); trainModelsPanel.add(trainModelsSearch); trainModelsPanel.add(trainModelsAdd);
		
		trainModelsSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		trainModelsAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainModels tm = new TrainModels((Integer.parseInt(trainModelsIDField.getText())), trainModelsNameField.getText(), (Integer.parseInt(trainModelsWeightField.getText())), (Integer.parseInt(trainModelsCargoIDField.getText())), (Integer.parseInt((trainModelsNumberOfCarsField.getText()))), (Integer.parseInt(trainModelsCapacityField.getText())), (Integer.parseInt(trainModelsCapacityField.getText())));
				trainModelsLibrary.trainModelsList.add(tm);
				
				try {
					trainModelsLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		trainModelsDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(trainModelsDrop.getSelectedItem() != null) {
					if (trainModelsDrop.getSelectedItem().equals("Select From...")) {
						//trainModelsIDField.setText("");
						//trainModelsNameField.setText("");
						//trainModelsWeightField.setText("");
						//trainModelsCargoIDField.setText("");
						//trainModelsNumberOfCarsField.setText("");
						//trainModelsCapacityField.setText("");
						//trainModelsWeightLimitField.setText("");
					}
					else {
						for (int i = 0; i < trainModelsLibrary.trainModelsList.size(); ++i) {
							if (trainModelsLibrary.trainModelsList.get(i).Name.equals(trainModelsDrop.getSelectedItem())) {
								trainModelsIDField.setText(Integer.toString(trainModelsLibrary.trainModelsList.get(i).ID));
								trainModelsNameField.setText(trainModelsLibrary.trainModelsList.get(i).Name);
								trainModelsWeightField.setText(Integer.toString(trainModelsLibrary.trainModelsList.get(i).Weight));
								trainModelsCargoIDField.setText(Integer.toString(trainModelsLibrary.trainModelsList.get(i).CargoID));
								trainModelsNumberOfCarsField.setText(Integer.toString(trainModelsLibrary.trainModelsList.get(i).NumberOfCars));
								trainModelsCapacityField.setText(Integer.toString(trainModelsLibrary.trainModelsList.get(i).Capacity));
								trainModelsWeightLimitField.setText(Integer.toString(trainModelsLibrary.trainModelsList.get(i).WeightLimit));	
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Train t = new Train(Integer.parseInt(trainIDField.getText()),trainNameField.getText(), Integer.parseInt(trainModelIDField.getText()));
				trainLibrary.trainList.add(t);
				
				try {
					trainLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		trainDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(trainDrop.getSelectedItem() != null) {
					if (trainDrop.getSelectedItem().equals("Select From...")) {
						//trainIDField.setText("");
						//trainNameField.setText("");
						//trainModelIDField.setText("");
						
					}
					else {
						for (int i = 0; i < trainLibrary.trainList.size(); ++i) {
							if (trainLibrary.trainList.get(i).name.equals(trainDrop.getSelectedItem())) {
								trainIDField.setText(Integer.toString(trainLibrary.trainList.get(i).ID));
								trainNameField.setText(trainLibrary.trainList.get(i).name);
								trainModelIDField.setText(Integer.toString(trainLibrary.trainList.get(i).modelID));
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Schedule s = new Schedule(Integer.parseInt(scheduleIDField.getText()), Integer.parseInt(scheduleTrainIDField.getText()), Integer.parseInt(scheduleRouteIDField.getText()), scheduleDepartTimeField.getText(), scheduleArriveTimeField.getText());
				
				scheduleLibrary.scheduleList.add(s);
				try {
				scheduleLibrary.ExportJSON();
				}catch(JSONException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		scheduleDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(scheduleDrop.getSelectedItem() != null) {
					if (scheduleDrop.getSelectedItem().equals("Select From...")) {
						//scheduleIDField.setText("");
						//scheduleTrainIDField.setText("");
						//scheduleRouteIDField.setText("");
						//scheduleDepartTimeField.setText("");
						//scheduleArriveTimeField.setText("");

					}
					else {
						for (int i = 0; i < scheduleLibrary.scheduleList.size(); ++i) {
							if (scheduleLibrary.scheduleList.get(i).ID == (Integer) scheduleDrop.getSelectedItem()) {
								scheduleIDField.setText(Integer.toString(scheduleLibrary.scheduleList.get(i).ID));
								scheduleTrainIDField.setText(Integer.toString(scheduleLibrary.scheduleList.get(i).getTrainID()));
								scheduleRouteIDField.setText(Integer.toString(scheduleLibrary.scheduleList.get(i).getRouteID()));
								scheduleDepartTimeField.setText(scheduleLibrary.scheduleList.get(i).departTime);
								scheduleArriveTimeField.setText(scheduleLibrary.scheduleList.get(i).arriveTime);
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Route r = new Route(Integer.parseInt(routeIDField.getText()), routeNameField.getText(), Integer.parseInt(routeSrcStationIDField.getText()), Integer.parseInt(routeDestStationIDField.getText()));
				routeLibrary.routeList.add(r);
				
				try {
					routeLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		routeDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(routeDrop.getSelectedItem() != null) {
					if (routeDrop.getSelectedItem().equals("Select From...")) {
						//routeIDField.setText("");
						//routeNameField.setText("");
						//routeSrcStationIDField.setText("");
						//routeDestStationIDField.setText("");
						
						
					}
					else {
						for (int i = 0; i < routeLibrary.routeList.size(); ++i) {
							if (routeLibrary.routeList.get(i).Name.equals(routeDrop.getSelectedItem())) {
								routeIDField.setText(Integer.toString(routeLibrary.routeList.get(i).ID));
								routeNameField.setText(routeLibrary.routeList.get(i).Name);
								routeSrcStationIDField.setText(Integer.toString(routeLibrary.routeList.get(i).SrcStationID));
								routeDestStationIDField.setText(Integer.toString(routeLibrary.routeList.get(i).DestStationID));
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Station st = new Station(Integer.parseInt(stationIDField.getText()), stationNameField.getText());
				stationLibrary.stationList.add(st);
				
				try {
					stationLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		stationDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(stationDrop.getSelectedItem() != null) {
					if (stationDrop.getSelectedItem().equals("Select From...")) {
						//stationIDField.setText("");
						//stationNameField.setText("");

						
						
					}
					else {
						for (int i = 0; i < stationLibrary.stationList.size(); ++i) {
							if (stationLibrary.stationList.get(i).Name.equals(stationDrop.getSelectedItem())) {
								stationIDField.setText(Integer.toString(stationLibrary.stationList.get(i).ID));
								stationNameField.setText(stationLibrary.stationList.get(i).Name);
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Location l = new Location(locationAddressField.getText(), Integer.parseInt(locationStationIDField.getText()));
				locationLibrary.locationList.add(l);
				
				try {
					locationLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		
		locationDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(locationDrop.getSelectedItem() != null) {
					if (locationDrop.getSelectedItem().equals("Select From...")) {
						//locationAddressField.setText("");
						//locationStationIDField.setText("");

						
						
					}
					else {
						for (int i = 0; i < locationLibrary.locationList.size(); ++i) {
							if (locationLibrary.locationList.get(i).Address.equals(locationDrop.getSelectedItem())) {
								locationStationIDField.setText(Integer.toString(locationLibrary.locationList.get(i).StationID));
								locationAddressField.setText(locationLibrary.locationList.get(i).Address);
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Ticket ti = new Ticket(Integer.parseInt(ticketIDField.getText()), Integer.parseInt(ticketTypeIDField.getText()), Integer.parseInt(ticketScheduleIDField.getText()));
				ticketLibrary.ticketList.add(ti);
				
				try {
					ticketLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		

		ticketDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(ticketDrop.getSelectedItem() != null) {
						if (ticketDrop.getSelectedItem().equals("Select From...")) {
							//ticketIDField.setText("");
							//ticketTypeIDField.setText("");
							//ticketScheduleIDField.setText("");
					}

				
					else {
						for (int i = 0; i < ticketLibrary.ticketList.size(); ++i) {
							if (ticketLibrary.ticketList.get(i).ID == (Integer) ticketDrop.getSelectedItem()) {
								ticketIDField.setText(Integer.toString(ticketLibrary.ticketList.get(i).ID));
								ticketTypeIDField.setText(Integer.toString(ticketLibrary.ticketList.get(i).getTypeID()));
								ticketScheduleIDField.setText(Integer.toString(ticketLibrary.ticketList.get(i).ScheduleID));
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				TicketTypes tt = new TicketTypes(Integer.parseInt(ticketTypesIDField.getText()), ticketTypesTypeField.getText(), Integer.parseInt(ticketTypesPriceField.getText()));
				ticketTypesLibrary.ticketTypesList.add(tt);
				
				try {
					ticketTypesLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		ticketTypesDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(ticketTypesDrop.getSelectedItem()!= null) {
						if (ticketTypesDrop.getSelectedItem().equals("Select From...")) {
							//ticketTypesIDField.setText("");
							//ticketTypesTypeField.setText("");
							//ticketTypesPriceField.setText("");
						
						}

					
					else {
						for (int i = 0; i < ticketTypesLibrary.ticketTypesList.size(); ++i) {
							if (TicketTypesLibrary.ticketTypesList.get(i).Type.equals(ticketTypesDrop.getSelectedItem())) {
								ticketTypesIDField.setText(Integer.toString(ticketTypesLibrary.ticketTypesList.get(i).ID));
								ticketTypesPriceField.setText(Integer.toString(ticketTypesLibrary.ticketTypesList.get(i).Price));
								ticketTypesTypeField.setText(ticketTypesLibrary.ticketTypesList.get(i).Type);
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
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
				Passenger p = new Passenger(Integer.parseInt(passengerIDField.getText()), Integer.parseInt(passengerTicketIDField.getText()), passengerFirstNameField.getText(), passengerLastNameField.getText());
				passengerLibrary.passengerList.add(p);
				try {
					passengerLibrary.ExportJSON();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		});

		
		
		
		passengerDrop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if(passengerDrop.getSelectedItem() != null) {
						if (passengerDrop.getSelectedItem().equals("Select From...")) {
							//passengerIDField.setText("");
							//passengerTicketIDField.setText("");
							//passengerFirstNameField.setText("");
							//passengerLastNameField.setText("");
						}

					
					else {
						for (int i = 0; i < passengerLibrary.passengerList.size(); ++i) {
							if (passengerLibrary.passengerList.get(i).ID == (Integer) passengerDrop.getSelectedItem()) {
								passengerIDField.setText(Integer.toString(passengerLibrary.passengerList.get(i).ID));
								passengerTicketIDField.setText(Integer.toString(passengerLibrary.passengerList.get(i).TicketID));
								passengerFirstNameField.setText(passengerLibrary.passengerList.get(i).FirstName);
								passengerLastNameField.setText(passengerLibrary.passengerList.get(i).LastName);
							}
						}
					}
				}
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		
		
		
		
		
		
		// buffer panel setup
		
		bufferPanel.add(new JLabel());
		
		//Query panel setup

        JScrollPane scrollPane = new JScrollPane(queryArea);
        queryArea.setFont(new Font("Serif", Font.BOLD, 20));
        queryArea.setText("Query the Railway database here.\n" +
                "Remember to prefix table names with the name of the schema (Railway)");
        queryArea.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (queryArea.getText().equals("Query the Railway database here.\n" +
                        "Remember to prefix table names with the name of the schema (Railway)")) queryArea.setText("");
            }

            public void focusLost(FocusEvent e) {
            	if (queryArea.getText().equals("")) queryArea.setText("Query the Railway database here.\n" +
                        "Remember to prefix table names with the name of the schema (Railway)");
            }
        });

        queryPanel.add(scrollPane);
        queryPanel.add(queryButton);

        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database.query(queryArea.getText());
                
		        queryPopout.setTitle("Query Results");
		        queryFramePanel.removeAll();

		        queryFramePanel.add(new JLabel("Stuff Here")); //TODO: Display Query instead of this
                queryPopout.getContentPane();
                queryPopout.add(queryFramePanel);
                
                queryPopout.addWindowListener(new WindowAdapter() {
                	@Override
                	public void windowClosing(WindowEvent e) {
                		queryPopout.dispose();
                		queryFramePanel.removeAll();
                	}
                });
                
                queryPopout.pack();
                queryPopout.setVisible(true);
                queryPopout.setSize(500, 500); //TODO: Im not sure if this is the optimal size for the query so change this what you need
                queryPopout.setLocation(700, 200);
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
		panel.add(bufferPanel);

		wholePanel.add(panel, BorderLayout.NORTH);
		wholePanel.add(queryPanel, BorderLayout.CENTER);

		frame.add(wholePanel);
		
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
		bufferPanel.setVisible(true);
		panel.setVisible(true);
		
		// frame setup

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
        frame.setTitle("SER322 Team 6 - Railway System");
		frame.setVisible(true);
		frame.getContentPane().add(wholePanel);
		frame.pack();
		frame.setSize(1500,800);
		frame.setLocation(200, 100);
	}
}
