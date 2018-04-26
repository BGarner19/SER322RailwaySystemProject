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

    }
}
