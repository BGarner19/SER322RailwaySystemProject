package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

import database.Database;
import query.*;


public class RailwaySystem {
    
    private Database database;
    private JTable stationsTable;
    private JTable cargoTypesTable;
    private JTable ticketTypesTable;
    private JTable tripsTable;
    private JTable routesTable;
    
    public RailwaySystem() {
        database = new Database(5432, "Team6RailwayDB", "postgres", "322");
        init();
    }
    
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JFrame frame = new JFrame();
    private JTextArea queryArea;
    private JDialog queryPopout = new JDialog();
    private JPanel queryFramePanel = new JPanel();
    
    private JButton routesSearchButton;
    private JButton trainsSearchButton;
    private JButton passengersSearchButton;
    private JButton ticketsSearchButton;
    
    private JTextField ticketsDepartingStation;
    private JTextField ticketsArrivingStation;
    private JTextField ticketsTime;
    
    private JTextField passengersTicketType;
    private JTextField passengersTripID;
    
    private JTextField trainsCargoType;
    private JTextField trainsRouteID;
    
    private JTextField routesDepartingStation;
    private JTextField routesArrivingStation;
    
    
    private void init() {
        
        
        //Query panel setup
        JPanel queryPanel = new JPanel(new GridLayout(1, 2));
        queryArea = new JTextArea();
        
        JScrollPane scrollPane = new JScrollPane(queryArea);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        queryArea.setFont(new Font("Serif", Font.BOLD, 15));
        queryArea.setText("Query the Railway database here.\n" +
                "Remember to prefix table names with the name of the schema (Railway)");
        
        JPanel quickActions = new JPanel(new BorderLayout());
        
        JButton queryButton = new JButton("Query Database");
        queryPanel.add(scrollPane);
        queryPanel.add(queryButton);
        
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        
        JPanel findTicketsPanel = new JPanel(new GridLayout(1, 7));
        
        actionsPanel.add(new JLabel("Find Tickets..."));
        
        findTicketsPanel.add(new JLabel("Departing Station: "));
        ticketsDepartingStation = new JTextField();
        findTicketsPanel.add(ticketsDepartingStation);
        findTicketsPanel.add(new JLabel(" Arriving Station: "));
        ticketsArrivingStation = new JTextField();
        findTicketsPanel.add(ticketsArrivingStation);
        findTicketsPanel.add(new JLabel(" Time: "));
        ticketsTime = new JTextField();
        findTicketsPanel.add(ticketsTime);
        ticketsSearchButton = new JButton("Search");
        findTicketsPanel.add(ticketsSearchButton);
        actionsPanel.add(findTicketsPanel);
        
        actionsPanel.add(new JLabel("Find Passengers..."));
        
        JPanel findPassengersPanel = new JPanel(new GridLayout(1, 7));
        findPassengersPanel.add(new JLabel("Ticket Type: "));
        passengersTicketType = new JTextField();
        findPassengersPanel.add(passengersTicketType);
        findPassengersPanel.add(new JLabel(" Trip ID: "));
        passengersTripID = new JTextField();
        findPassengersPanel.add(passengersTripID);
        passengersSearchButton = new JButton("Search");
        findPassengersPanel.add(new JLabel());
        findPassengersPanel.add(new JLabel());
        findPassengersPanel.add(passengersSearchButton);
        actionsPanel.add(findPassengersPanel);
        
        actionsPanel.add(new JLabel("Find Trains..."));
        
        JPanel findTrainsPanel = new JPanel(new GridLayout(1, 7));
        findTrainsPanel.add(new JLabel("Cargo Type: "));
        trainsCargoType = new JTextField();
        findTrainsPanel.add(trainsCargoType);
        findTrainsPanel.add(new JLabel(" RouteID: "));
        trainsRouteID = new JTextField();
        findTrainsPanel.add(trainsRouteID);
        findTrainsPanel.add(new JLabel());
        findTrainsPanel.add(new JLabel());
        trainsSearchButton = new JButton("Search");
        findTrainsPanel.add(trainsSearchButton);
        actionsPanel.add(findTrainsPanel);
        
        actionsPanel.add(new JLabel("Find Routes..."));
        
        JPanel findRoutesPanel = new JPanel(new GridLayout(1, 7));
        findRoutesPanel.add(new JLabel("Departing Station: "));
        routesDepartingStation = new JTextField();
        findRoutesPanel.add(routesDepartingStation);
        findRoutesPanel.add(new JLabel(" Arriving Station: "));
        routesArrivingStation = new JTextField();
        findRoutesPanel.add(routesArrivingStation);
        routesSearchButton = new JButton("Search");
        findRoutesPanel.add(new JLabel());
        findRoutesPanel.add(new JLabel());
        findRoutesPanel.add(routesSearchButton);
        actionsPanel.add(findRoutesPanel);
        
        stationsTable = new JTable();
        cargoTypesTable = new JTable();
        ticketTypesTable = new JTable();
        tripsTable = new JTable();
        routesTable = new JTable();
        
        routesSearchButton.addActionListener(new SearchButtonListener());
        trainsSearchButton.addActionListener(new SearchButtonListener());
        passengersSearchButton.addActionListener(new SearchButtonListener());
        ticketsSearchButton.addActionListener(new SearchButtonListener());
        
        JPanel tablesPanel = new JPanel(new GridLayout(2, 2));
        
        JScrollPane stations = new JScrollPane(stationsTable);
        JScrollPane cargo = new JScrollPane(cargoTypesTable);
        JScrollPane ticketTypes = new JScrollPane(ticketTypesTable);
        JScrollPane trips = new JScrollPane(tripsTable);
        JScrollPane routes = new JScrollPane(routesTable);
        
        JTabbedPane stationTab = new JTabbedPane();
        stationTab.add("STATIONS", stations);
        JTabbedPane cargoTab = new JTabbedPane();
        cargoTab.add("CARGO_TYPES", cargo);
        JTabbedPane ticketTab = new JTabbedPane();
        ticketTab.add("TICKET_TYPES", ticketTypes);
        JTabbedPane tripsTab = new JTabbedPane();
        tripsTab.add("TRIPS", trips);
        tripsTab.add("ROUTES", routes);
        
        tablesPanel.add(stationTab);
        tablesPanel.add(cargoTab);
        tablesPanel.add(ticketTab);
        tablesPanel.add(tripsTab);
        
        quickActions.add(queryPanel, BorderLayout.SOUTH);
        quickActions.add(actionsPanel, BorderLayout.NORTH);
        quickActions.add(tablesPanel, BorderLayout.CENTER);
        
        queryButton.addActionListener(new SearchButtonListener());
        
        JTextArea helpText = new JTextArea();
        helpText.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel helpLabel = new JLabel("HOW TO USE THIS PROGRAM");
        helpLabel.setFont(new Font("Serif", Font.BOLD, 40));
        helpLabel.setHorizontalAlignment(JLabel.CENTER);
        helpText.append(getHelpText());
        
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setEditable(true);
        helpText.setBackground(new Color(0, 0, 0, 0));
        
        JPanel helpPanel = new JPanel(new BorderLayout());
        helpPanel.add(helpText, BorderLayout.CENTER);
        helpPanel.add(helpLabel, BorderLayout.NORTH);
        
        JPanel developers = new JPanel();
        JPanel creditsPanel = new JPanel(new BorderLayout());
        JPanel locationPanel = new JPanel();
        
        developers.setLayout(new BoxLayout(developers, BoxLayout.Y_AXIS));
        locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.Y_AXIS));
        
        JLabel credits = new JLabel("Team 6 Developers");
        credits.setFont(new Font("Serif", Font.BOLD, 40));
        JLabel bailey = new JLabel("Bailey Garner");
        bailey.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel rachel = new JLabel("Rachel Knoche");
        rachel.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel canyon = new JLabel("Canyon Schubert");
        canyon.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel johnathan = new JLabel("Johnathan Sinicrope");
        johnathan.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton github = new JButton("GitHub");
        github.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel program = new JLabel("This program was created for SER322 - Database Systems taught by Srividya Bansal");
        program.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel date = new JLabel("Spring 2018 - Arizona State University Polytechnic Campus, Mesa, Arizona");
        date.setFont(new Font("Serif", Font.PLAIN, 20));
        
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        github.setAlignmentX(Component.CENTER_ALIGNMENT);
        bailey.setAlignmentX(Component.CENTER_ALIGNMENT);
        rachel.setAlignmentX(Component.CENTER_ALIGNMENT);
        canyon.setAlignmentX(Component.CENTER_ALIGNMENT);
        johnathan.setAlignmentX(Component.CENTER_ALIGNMENT);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        program.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        developers.add(credits);
        developers.add(bailey);
        developers.add(rachel);
        developers.add(canyon);
        developers.add(johnathan);
        developers.add(github);
        locationPanel.add(program);
        locationPanel.add(date);
        
        creditsPanel.add(new JPanel(), BorderLayout.NORTH);
        creditsPanel.add(developers, BorderLayout.CENTER);
        creditsPanel.add(locationPanel, BorderLayout.SOUTH);
        
        github.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    URI githubURI = new URI("https://github.com/BGarner19/SER322RailwaySystemProject/wiki");
                    Desktop.getDesktop().browse(githubURI);
                }
                catch (Exception ex) {
                    System.out.println("Error with URL");
                }
                
            }
        });
        
        tabbedPane.addTab("Quick Actions", quickActions);
        tabbedPane.addTab("Help", new JScrollPane(helpPanel));
        tabbedPane.addTab("Credits", creditsPanel);
        
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
        frame.setSize(new Dimension(1200, 900));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(0);
        updateGUI();
    }
    
    private void updateGUI() {
        stationsTable.setModel(database.query("SELECT * FROM Railway.STATIONS"));
        cargoTypesTable.setModel(database.query("SELECT * FROM Railway.CARGO_TYPES"));
        ticketTypesTable.setModel(database.query("SELECT * FROM Railway.TICKET_TYPES"));
        tripsTable.setModel(database.query("SELECT * FROM Railway.TRIPS"));
        routesTable.setModel(database.query("SELECT * FROM Railway.ROUTES"));
    }
    
    private String getHelpText() {
        
        String first = "You can run any query against the database by using the field at the bottom of the program and pressing query database. Note that ANY query can be run from this box, so please be conscious of actions that may affect the integrity of the database, such as deletion of tables or schemas.";
        String second = "To find tickets matching certain conditions, use the Find Tickets... text boxes. Use the name of the stations and the time in 24 hour format. You may choose to fill out all 3 text boxes, none of the text boxes, or a mixture to find matching tickets." +
                "This will result in matching ticketID's being returned.";
        String third = "To find passengers matching certain conditions, use the Find Passengers... text boxes. Use the TYPE of the ticket (Adult, Child, Senior, etc.) and the ID of the trip. You may fill out either text box, both, or neither. " +
                "This will result in a list of matching passenger names.";
        String fourth = "To find trains matching certain conditions, use the Find Trains... text boxes. Use the type of cargo (Freight, Passenger, etc.) and the ID of the route. You may fill out one box, both, or neither. " +
                "This will result in a list of train names.";
        String fifth = "To find routes matching certain conditions, use the Find Routes... text boxes. Use the name of the arriving and departing stations. You may choose to fill out both stations, one station, or neither station. " +
                "This will return a list of route names.";
        
        return String.format(" 1. %s\n" +
                " 2. %s\n" +
                " 3. %s\n" +
                " 4. %s\n" +
                " 5. %s\n", first, second, third, fourth, fifth);
    }
    
    private class SearchButtonListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            QueryBuilder query;
            String resultsType;
            
            if (e.getSource().equals(routesSearchButton)) {
                query = new RoutesQuery(routesDepartingStation.getText(), routesArrivingStation.getText());
                resultsType = "ROUTE ";
            } else if (e.getSource().equals(trainsSearchButton)) {
                query = new TrainQuery(trainsCargoType.getText(), trainsRouteID.getText());
                resultsType = "TRAIN ";
            } else if (e.getSource().equals(passengersSearchButton)) {
                query = new PassengersQuery(passengersTicketType.getText(), passengersTripID.getText());
                resultsType = "PASSENGER ";
            } else if (e.getSource().equals(ticketsSearchButton)) {
                query = new TicketsQuery(ticketsDepartingStation.getText(), ticketsArrivingStation.getText(), ticketsTime.getText());
                resultsType = "TICKET ";
            } else {
                query = new DefaultQuery(queryArea.getText());
                resultsType = "QUERY ";
            }
            
            JTable queryOut = new JTable(database.query(query.buildQuery()));
            
            if (queryOut.getColumnCount() != 0) {
                JScrollPane spane = new JScrollPane(queryOut);
                
                queryPopout.setTitle(resultsType + "RESULTS");
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
            }
            
            updateGUI();
            
        }
    }
}
