import java.sql.Connection;
import java.sql.Statement;

public class Database {

    public void createTables(Connection c) {

        String sql;
        Statement stmt;

        try {
            stmt = c.createStatement();

            sql = "DROP SCHEMA Railway CASCADE";
            stmt.executeUpdate(sql);

            sql = "CREATE SCHEMA Railway";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.CARGO_TYPES(" +
                    "ID INT NOT NULL," +
                    "Type VARCHAR(30) NOT NULL," +
                    "UNIQUE (Type)," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.MODELS " +
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

            sql = "CREATE TABLE Railway.TRAINS(" +
                    "ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "ModelID INT NOT NULL," +
                    "UNIQUE (Name)," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (ModelID) REFERENCES Railway.MODELS(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.STATIONS(" +
                    "ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "Latitude DECIMAL(10,2) NOT NULL," +
                    "Longitude DECIMAL(10,2) NOT NULL," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.ROUTES(" +
                    "ID INT NOT NULL," +
                    "Name VARCHAR(15) NOT NULL," +
                    "SourceID INT NOT NULL," +
                    "DestID INT NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (SourceID) REFERENCES Railway.STATIONS(ID)," +
                    "FOREIGN KEY (DestID) REFERENCES Railway.STATIONS(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.SCHEDULE(" +
                    "ID INT NOT NULL," +
                    "TrainID INT NOT NULL," +
                    "RouteID INT NOT NULL," +
                    "DepartTime TIME NOT NULL," +
                    "ArriveTime TIME NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (TrainID) REFERENCES Railway.TRAINS(ID)," +
                    "FOREIGN KEY (RouteID) REFERENCES Railway.ROUTES(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.TICKET_TYPES(" +
                    "ID INT NOT NULL," +
                    "Type VARCHAR(30) NOT NULL," +
                    "Price DECIMAL(10,2) NOT NULL," +
                    "UNIQUE (Type)," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.TICKETS(" +
                    "ID INT NOT NULL," +
                    "ScheduleID INT NOT NULL," +
                    "TypeID INT NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (ScheduleID) REFERENCES Railway.SCHEDULE(ID)," +
                    "FOREIGN KEY (TypeID) REFERENCES Railway.TICKET_TYPES(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Railway.PASSENGERS(" +
                    "ID INT NOT NULL," +
                    "FName VARCHAR(15) NOT NULL," +
                    "MI CHAR," +
                    "LName VARCHAR(15) NOT NULL," +
                    "BDate DATE," +
                    "TicketID INT NOT NULL," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (TicketID) REFERENCES Railway.TICKETS(ID))";
            stmt.executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public void fillTables(Connection c) {
        String sql;
        Statement stmt;

        try {
            stmt = c.createStatement();

            sql = "INSERT INTO Railway.CARGO_TYPES (ID, Type) VALUES" +
                    "('1', 'Freight')," +
                    "('2', 'Passenger')";
            stmt.executeUpdate(sql);

//            sql = "INSERT INTO Railway.MODELS (ID, Name, Weight, CargoID, NumCars, Capacity, WeightLim) VALUES " +
//                    "('1', 'ModelX-F', '125', '1', '20', '', '1000')," +
//                    "('2', 'ModelT-F', '125', '1', '10', '', '500')," +
//                    "('3', 'ModelX-P', '100', '2', '20', '200', '')," +
//                    "('4', 'ModelT-P', '100', '2', '10', '100', '')";
//            stmt.executeUpdate(sql);

//            sql = "INSERT INTO Railway.TRAINS (ID, Name, Model) VALUES " +
//                    "('1', 'Train1', '1')," +
//                    "('2', 'Train2', '3')," +
//                    "('3', 'Train3', '4')";
//            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.STATIONS (ID, Name, Latitude, Longitude) VALUES " +
                    "('1', 'Station1', '100.00', '100.00')," +
                    "('2', 'Station2', '200.00', '200.00')," +
                    "('3', 'Station3', '300.00', '300.00')";
            stmt.executeUpdate(sql); //works

            sql = "INSERT INTO Railway.ROUTES (ID, Name, SourceID, DestID) VALUES " +
                    "('1', 'Route1', '1', '2'), " +
                    "('2', 'Route2', '3', '1')";
            stmt.executeUpdate(sql); //Works

//            sql = "INSERT INTO Railway.SCHEDULE (ID, TrainID, RouteID, DepartTime, ArriveTime) VALUES " +
//                    "('1', '1', '1', '00:00', '1:00')";
//            stmt.executeUpdate(sql);

//            sql = "INSERT INTO Railway.TICKET_TYPES (ID, Type, Price) VALUES " +
//                    "('1', 'Adult', '15.99')," +
//                    "('2', 'Child', '6.99')," +
//                    "('3', 'Senior', '9.99')";
//            stmt.executeUpdate(sql); //Works

            sql = "INSERT INTO Railway.TICKETS (ID, ScheduleID, TypeID) VALUES " +
                    "('1001', '1', '1')," +
                    "('1002', '1', '2')," +
                    "('1003', '2', '3')," +
                    "('1004', '3', '1')," +
                    "('1005', '3', '2')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Railway.PASSENGERS (ID, FName, MI, LName, BDate, TicketID) VALUES " +
                    "('1001', 'John', 'M', 'Lawrence', '06/19/1984', '1001')," +
                    "('1002', 'Sarah', '', 'Smith', '01/21/08', '1005')," +
                    "('1003', 'Mary', 'L', 'Johnson', '', '1004')," +
                    "('1004', 'David', '', 'Jones', '05/12/1952', '1003')," +
                    "('1005', 'Jessie', 'J', 'Lyons', '12/15/12', '1002')";
            stmt.executeUpdate(sql);






        }
        catch (Exception e) {

        }
    }
}
