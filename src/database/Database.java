package database;

import java.sql.*;

public class Database {

    private int port;
    private String dbName;
    private String username;
    private String password;
    private Connection c;

    /**
     * Constructor to connect to the database. Takes in the port and database name.
     * @param port The port that PostgreSQL is set to use. Default is 5432
     * @param dbName The name of the database to connect to. Default is Team6RailwayDB.
     */

    public Database(int port, String dbName, String username, String password) {
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;

        initDatabase();
    }

    /**
     * Connects to the database specified by the name on the correct port. Uses default user postgres and password 322.
     * After connecting to the database, calls methods to create all of the tables in the database and fill them with
     * data.
     */

    private void initDatabase() {

        try {
            Class.forName("org.postgresql.Driver");

            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:" + port + "/" + dbName,
                            username, password);

            createTables(c);
            fillTables(c);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }

    /**
     * Creates the database relations as tables. Railway is the name of the schema. This method will delete all
     * current data in the schema and recreate it to default state.
     * @param c The connection to the database.
     */

    public void createTables(Connection c) {

        String sql;
        Statement stmt;

        try {
            stmt = c.createStatement();

            sql = "DROP SCHEMA IF EXISTS Railway CASCADE";
            stmt.executeUpdate(sql);

            sql = "CREATE SCHEMA IF NOT EXISTS Railway";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.CARGO_TYPES(" +
                    "ID INT NOT NULL," +
                    "Type VARCHAR(30) NOT NULL," +
                    "UNIQUE (Type)," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.MODELS " +
                    "(ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "Weight INT NOT NULL," +
                    "CargoID INT NOT NULL," +
                    "NumCars INT," +
                    "Capacity INT," +
                    "WeightLim INT," +
                    "UNIQUE (Name)," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (CargoID) REFERENCES Railway.CARGO_TYPES(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.TRAINS(" +
                    "ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "ModelID INT NOT NULL," +
                    "UNIQUE (Name)," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (ModelID) REFERENCES Railway.MODELS(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.STATIONS(" +
                    "ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "Latitude DECIMAL(10,2) NOT NULL," +
                    "Longitude DECIMAL(10,2) NOT NULL," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.ROUTES(" +
                    "ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "SourceID INT NOT NULL," +
                    "DestID INT NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (SourceID) REFERENCES Railway.STATIONS(ID)," +
                    "FOREIGN KEY (DestID) REFERENCES Railway.STATIONS(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.TRIPS(" +
                    "ID INT NOT NULL," +
                    "TrainID INT NOT NULL," +
                    "RouteID INT NOT NULL," +
                    "DepartTime TIME NOT NULL," +
                    "ArriveTime TIME NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (TrainID) REFERENCES Railway.TRAINS(ID)," +
                    "FOREIGN KEY (RouteID) REFERENCES Railway.ROUTES(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.SCHEDULE(" +
                    "ID INT NOT NULL," +
                    "TripID INT NOT NULL," +
                    "Date DATE NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (TripID) REFERENCES Railway.TRIPS(ID))";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS Railway.TICKET_TYPES(" +
                    "ID INT NOT NULL," +
                    "Type VARCHAR(30) NOT NULL," +
                    "Price DECIMAL(10,2) NOT NULL," +
                    "UNIQUE (Type)," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.PASSENGERS(" +
                    "ID INT NOT NULL," +
                    "FName VARCHAR(15) NOT NULL," +
                    "MI CHAR," +
                    "LName VARCHAR(15) NOT NULL," +
                    "BDate DATE," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Railway.TICKETS(" +
                    "ID INT NOT NULL," +
                    "TypeID INT NOT NULL," +
                    "TripID INT NOT NULL," +
                    "PassengerID INT NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (TypeID) REFERENCES Railway.TICKET_TYPES(ID),"+
                    "FOREIGN KEY (TripID) REFERENCES Railway.TRIPS(ID),"+
                    "FOREIGN KEY (PassengerID) REFERENCES Railway.PASSENGERS(ID))";
            stmt.executeUpdate(sql);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Fills the tables in the database with sample data. Railway is the name of the database schema.
     * @param c The connection to the database.
     */

    public void fillTables(Connection c) {
        String sql;
        Statement stmt;

        try {
            stmt = c.createStatement();

            sql = "INSERT INTO Railway.CARGO_TYPES (ID, Type) VALUES" +
                    "('1', 'Freight')," +
                    "('2', 'Passenger')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.MODELS (ID, Name, Weight, CargoID, NumCars, Capacity, WeightLim) VALUES " +
                    "('1', 'ModelX-F', '125', '1', '20', null, '1000')," +
                    "('2', 'ModelT-F', '125', '1', '10', null, '500')," +
                    "('3', 'ModelX-P', '100', '2', '20', '200', null)," +
                    "('4', 'ModelT-P', '100', '2', '10', '100', null)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.TRAINS (ID, Name, ModelID) VALUES " +
                    "('1', 'Train1', '1')," +
                    "('2', 'Train2', '3')," +
                    "('3', 'Train3', '4')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.STATIONS (ID, Name, Latitude, Longitude) VALUES " +
                    "('1', 'Station1', '100.00', '100.00')," +
                    "('2', 'Station2', '200.00', '200.00')," +
                    "('3', 'Station3', '300.00', '300.00')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.ROUTES (ID, Name, SourceID, DestID) VALUES " +
                    "('1', 'Route1', '1', '2'), " +
                    "('2', 'Route2', '3', '1')," +
                    "('3', 'Route3', '2', '3')," +
                    "('4', 'Route4', '1', '3')," +
                    "('5', 'Route5', '3', '2')," +
                    "('6', 'Route6', '2', '1')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.TRIPS (ID, TrainID, RouteID, DepartTime, ArriveTime) VALUES " +
                    "('1', '1', '1', '00:00', '1:00')," +
                    "('2', '1', '6', '1:30', '2:30')," +
                    "('3', '2', '1', '14:00', '15:00')," +
                    "('4', '2', '3', '15:30', '16:30')," +
                    "('5', '2', '5', '17:00', '18:00')," +
                    "('6', '3', '4', '12:00', '14:00')," +
                    "('7', '3', '2', '14:30', '16:30')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.SCHEDULE (ID, TripID, Date) VALUES " +
                    "('1', '1', '2018-05-12')," +
                    "('2', '1', '2018-06-12')," +
                    "('3', '1', '2018-07-12')," +
                    "('4', '1', '2018-08-12')," +
                    "('5', '1', '2018-09-12')," +
                    "('6', '1', '2018-10-12')," +
                    "('7', '2', '2018-05-12')," +
                    "('8', '2', '2018-06-12')," +
                    "('9', '2', '2018-07-12')," +
                    "('10', '2', '2018-08-12')," +
                    "('11', '2', '2018-09-12')," +
                    "('12', '2', '2018-10-12')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.TICKET_TYPES (ID, Type, Price) VALUES " +
                    "('1', 'Adult', '15.99')," +
                    "('2', 'Child', '6.99')," +
                    "('3', 'Senior', '9.99')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.PASSENGERS (ID, FName, MI, LName, BDate) VALUES " +
                    "('1001', 'John', 'M', 'Lawrence', '06/19/1984')," +
                    "('1002', 'Sarah', null, 'Smith', '01/21/08')," +
                    "('1003', 'Mary', 'L', 'Johnson', null)," +
                    "('1004', 'David', null, 'Jones', '05/12/1952')," +
                    "('1005', 'Jessie', 'J', 'Lyons', '12/15/12')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.TICKETS (ID, TypeID, TripID, PassengerID) VALUES " +
                    "('1001', '1', '1', '1001')," +
                    "('1002', '1', '2', '1005')," +
                    "('1003', '2', '3', '1004')," +
                    "('1004', '3', '1', '1004')," +
                    "('1005', '3', '2','1002')";
            stmt.executeUpdate(sql);


        }
        catch (Exception e) {
            
        }
    }

    public Connection getDatabaseConnection() {

        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:" + port + "/" + dbName,
                            username, password);
        }
        catch (SQLException ex) {

        }

        return c;
    }

    public String query(String query) {

        System.out.println(query);
        try {

            ResultSet resultSet = c.createStatement().executeQuery(query);

            ResultSetMetaData rsmd = resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            StringBuilder output = new StringBuilder();

            for (int i = 1; i <= columnsNumber; i++) {

                output.append(String.format("%15s ", rsmd.getColumnName(i)));
            }

            output.append("\n");

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {

                    output.append(String.format("%15s ", resultSet.getString(i)));
                }

                output.append("\n");
            }

            System.out.print("IT WORKS");

            return output.toString();
        }
        catch (SQLException ex) {

        }


        return null;
    }
}
