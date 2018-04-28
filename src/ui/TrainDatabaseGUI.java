package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import database.Database;


public class TrainDatabaseGUI {

    private Database database;
    private JTable stationsTable;
    private JTable cargoTypesTable;
    private JTable ticketTypesTable;
    private JTable tripsTable;
    
    
	public TrainDatabaseGUI() {
	    database = new Database(5432, "Team6RailwayDB", "postgres", "322");
		init();
	}

	JTabbedPane tabbedPane = new JTabbedPane();
	JFrame frame = new JFrame();
	JPanel queryPanel = new JPanel(new GridLayout(1, 2));
	

	//Query Panel stuff

    JTextArea queryArea = new JTextArea();
    JButton queryButton = new JButton("Query Database");
    JDialog queryPopout = new JDialog();
    JPanel queryFramePanel = new JPanel();



	void init() {
		

        //Query panel setup

        JScrollPane scrollPane = new JScrollPane(queryArea);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        queryArea.setFont(new Font("Serif", Font.BOLD, 15));
        queryArea.setText("Query the Railway database here.\n" +
                "Remember to prefix table names with the name of the schema (Railway)");
        JPanel quickActions = new JPanel(new BorderLayout());
        quickActions.setSize(new Dimension(100,100));

        queryPanel.add(scrollPane);
        queryPanel.add(queryButton);

        JTable queryOutput = new JTable();

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

        JPanel findTicketsPanel = new JPanel(new GridLayout(1, 7));


        actionsPanel.add(new JLabel("Find Tickets..."));

        findTicketsPanel.add(new JLabel("Departing Station: "));
        JTextField ticketsDepartingStation = new JTextField();
        findTicketsPanel.add(ticketsDepartingStation);
        findTicketsPanel.add(new JLabel("Arriving Station: "));
        JTextField ticketsArrivingStation = new JTextField();
        findTicketsPanel.add(ticketsArrivingStation);
        findTicketsPanel.add(new JLabel("Time: "));
        JTextField ticketsTime = new JTextField();
        findTicketsPanel.add(ticketsTime);
        JButton ticketsSearchButton = new JButton("Search");
        findTicketsPanel.add(ticketsSearchButton);
        actionsPanel.add(findTicketsPanel);

        actionsPanel.add(new JLabel("Find Passengers..."));

        JPanel findPassengersPanel = new JPanel(new GridLayout(1, 7));
        findPassengersPanel.add(new JLabel("Ticket Type: "));
        JTextField passengersTicketType = new JTextField();
        findPassengersPanel.add(passengersTicketType);
        findPassengersPanel.add(new JLabel("Trip ID: "));
        JTextField passengersTripID = new JTextField();
        findPassengersPanel.add(passengersTripID);
        JButton passengersSearchButton = new JButton("Search");
        findPassengersPanel.add(new JLabel());
        findPassengersPanel.add(new JLabel());
        findPassengersPanel.add(passengersSearchButton);
        actionsPanel.add(findPassengersPanel);

        actionsPanel.add(new JLabel("Find Trains..."));

        JPanel findTrainsPanel = new JPanel(new GridLayout(1, 7));
        findTrainsPanel.add(new JLabel("Cargo Type: "));
        JTextField trainsCargoType = new JTextField();
        findTrainsPanel.add(trainsCargoType);
        findTrainsPanel.add(new JLabel("RouteID: "));
        JTextField trainsRouteID = new JTextField();
        findTrainsPanel.add(trainsRouteID);
        findTrainsPanel.add(new JLabel());
        findTrainsPanel.add(new JLabel());
        JButton trainsSearchButton = new JButton("Search");
        findTrainsPanel.add(trainsSearchButton);

        actionsPanel.add(findTrainsPanel);

        stationsTable = new JTable();
        cargoTypesTable = new JTable();
        ticketTypesTable = new JTable();
        tripsTable = new JTable();

        updateGUI();

        trainsSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String cargoType = trainsCargoType.getText();
                String routeID = trainsRouteID.getText();

                String sql = "";

				if(cargoType.equals("") && routeID.equals("")){
					sql = "SELECT Name FROM Railway.TRAINS";
				} else if (cargoType.equals("")){
					sql = "SELECT Name " +
							"FROM railway.TRAINS " +
							"WHERE ID IN (SELECT trainID FROM railway.TRIPS WHERE routeID = '" + routeID + "')";
				} else if(routeID.equals("")){
					sql = "SELECT Name " +
							"FROM railway.TRAINS " +
							"WHERE modelID IN (SELECT ID FROM railway.Models WHERE CargoID IN " +
							"(SELECT ID FROM railway.CARGO_TYPES WHERE Type = '" + cargoType + "'))";
				} else {
					sql = "SELECT Name " +
							"FROM railway.TRAINS " +
							"WHERE ID IN (SELECT trainID FROM railway.TRIPS WHERE routeID = '" + routeID + "')AND" +
							"(modelID IN (SELECT ID FROM railway.Models WHERE CargoID IN" +
							"(SELECT ID FROM railway.CARGO_TYPES WHERE Type = '" + cargoType + "')))";
				}

                JTable queryOut = new JTable(database.query(sql));
				JScrollPane spane = new JScrollPane(queryOut);


                queryPopout.setTitle("TRAIN RESULTS");
                queryFramePanel.removeAll();

                queryFramePanel.add(spane);
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

                updateGUI();
            }
        });

        passengersSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String ticketType = passengersTicketType.getText();
                String tripID = passengersTripID.getText();

                String sql = "";

				if(ticketType.equals("") && tripID.equals("")){
					sql = "SELECT FName, MI, LName FROM Railway.PASSENGERS";
				} else if (ticketType.equals("")){
					sql = "SELECT FName, MI, LName " +
							"FROM railway.PASSENGERS " +
							"WHERE ID IN (SELECT ID FROM railway.TICKETS WHERE TripID IN " +
							"(SELECT ID FROM railway.TRIPS WHERE ID = '" + tripID + "'))";
				} else if(tripID.equals("")){
					sql = "SELECT FName, MI, LName " +
							"FROM railway.PASSENGERS " +
							"WHERE ID IN (SELECT ID FROM railway.TICKETS WHERE TypeID IN " +
							"(SELECT ID FROM railway.TICKET_TYPES WHERE Type = '" + ticketType + "'))";
				} else {
					sql = "SELECT FName, MI, LName " +
							"FROM railway.PASSENGERS " +
							"WHERE ID IN (SELECT ID FROM railway.TICKETS WHERE TripID IN " +
							"(SELECT ID FROM railway.TRIPS WHERE ID = '" + tripID + "') AND" +
							"(ID IN (SELECT ID FROM railway.TICKETS WHERE TypeID IN" +
							"(SELECT ID FROM railway.TICKET_TYPES WHERE Type = '" + ticketType + "'))))";
				}

                JTable queryOut = new JTable(database.query(sql));
                JScrollPane spane = new JScrollPane(queryOut);

                queryPopout.setTitle("PASSENGER RESULTS");
                queryFramePanel.removeAll();

                queryFramePanel.add(spane);
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


                updateGUI();
            }
        });

        ticketsSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String departingStation = ticketsDepartingStation.getText();
                String arrivingStation = ticketsArrivingStation.getText();
                String departingTime = ticketsTime.getText();
				String sql = "";

				if(departingStation.equals("") && arrivingStation.equals("") && departingTime.equals("")){
					sql = "SELECT ID FROM Railway.TICKETS";
				} else if (arrivingStation.equals("") && departingTime.equals("")) {
					sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN " +
							"(SELECT ID FROM Railway.ROUTES WHERE SourceID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + departingStation + "')))";
				} else if (departingStation.equals("") && departingTime.equals("")) {
					sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN " +
							"(SELECT ID FROM Railway.ROUTES WHERE DestID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + arrivingStation + "')))";
				} else if (departingStation.equals("") && arrivingStation.equals("")) {
					sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN " +
							"(SELECT ID FROM Railway.ROUTES WHERE departTime = '" + departingTime + "'))";
				} else if (departingStation.equals("")) {
					sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN " +
							"(SELECT ID FROM Railway.ROUTES WHERE departTime = '" + departingTime + "') AND (routeID IN" +
							"(SELECT ID FROM Railway.ROUTES WHERE DestID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + arrivingStation + "'))))";
				} else if (arrivingStation.equals("")) {
					sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN " +
							"(SELECT ID FROM Railway.ROUTES WHERE departTime = '" + departingTime + "') AND (routeID IN" +
							"(SELECT ID FROM Railway.ROUTES WHERE SourceID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + departingStation + "'))))";
				} else if (departingTime.equals("")) {
					sql ="SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN" +
							"(SELECT ID FROM Railway.ROUTES WHERE DestID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + arrivingStation + "') AND (routeID IN" +
							"(SELECT ID FROM Railway.ROUTES WHERE SourceID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + departingStation + "'))))";
				} else {
					sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
							"(SELECT ID FROM Railway.TRIPS WHERE routeID IN " +
							"(SELECT ID FROM Railway.ROUTES WHERE departTime = '" + departingTime + "') AND (routeID IN" +
							"(SELECT ID FROM Railway.ROUTES WHERE DestID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + arrivingStation + "') AND (routeID IN" +
							"(SELECT ID FROM Railway.ROUTES WHERE SourceID IN" +
							"(SELECT ID FROM Railway.STATIONS WHERE Name = '" + departingStation + "'))))))";
				}

				JTable queryOut = new JTable(database.query(sql));
                JScrollPane spane = new JScrollPane(queryOut);

                queryPopout.setTitle("TICKET RESULTS");
                queryFramePanel.removeAll();

                queryFramePanel.add(spane);
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


                updateGUI();

            }
        });

        JPanel tablesPanel = new JPanel(new GridLayout(2, 2));


        JScrollPane stations = new JScrollPane(stationsTable);
        JScrollPane cargo = new JScrollPane(cargoTypesTable);
        JScrollPane ticketTypes = new JScrollPane(ticketTypesTable);
        JScrollPane trips = new JScrollPane(tripsTable);

        JTabbedPane stationTab = new JTabbedPane();
        stationTab.add("STATIONS", stations);
        JTabbedPane cargoTab = new JTabbedPane();
        cargoTab.add("CARGO_TYPES", cargo);
        JTabbedPane ticketTab = new JTabbedPane();
        ticketTab.add("TICKET_TYPES", ticketTypes);
        JTabbedPane tripsTab = new JTabbedPane();
        tripsTab.add("TRIPS", trips);

        tablesPanel.add(stationTab);
        tablesPanel.add(cargoTab);
        tablesPanel.add(ticketTab);
        tablesPanel.add(tripsTab);

        quickActions.add(queryPanel, BorderLayout.SOUTH);
        quickActions.add(actionsPanel, BorderLayout.NORTH);
        quickActions.add(tablesPanel, BorderLayout.CENTER);


        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JTable queryOutput = new JTable(database.query(queryArea.getText()));
                JScrollPane spane = new JScrollPane(queryOutput);

                queryPopout.setTitle("Query Results");
                queryFramePanel.removeAll();

                queryFramePanel.add(spane);
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

                updateGUI();

            }
        });



        tabbedPane.addTab("Quick Actions", quickActions);
        //tabbedPane.addTab("Main", panel);

		frame.add(tabbedPane);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
        frame.setTitle("SER322 Team 6 - Railway System");
		frame.setVisible(true);
		frame.getContentPane().add(tabbedPane);
		frame.pack();
		frame.setSize(1000,1000);
		frame.setLocation(200, 100);
	}

	private void updateGUI() {

	    stationsTable = new JTable(database.query("SELECT * FROM Railway.STATIONS"));
        cargoTypesTable = new JTable(database.query("SELECT * FROM Railway.CARGO_TYPES"));
        ticketTypesTable = new JTable(database.query("SELECT * FROM Railway.TICKET_TYPES"));
        tripsTable = new JTable(database.query("SELECT * FROM Railway.TRIPS"));

        stationsTable.updateUI();
        cargoTypesTable.updateUI();
        ticketTypesTable.updateUI();
        tripsTable.updateUI();

    }
}
