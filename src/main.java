import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class main {
    public static void main(String args[]) {
        Connection c = null;
        String sql;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/SER322_DB",
                            "postgres", "322");

            Statement stmt = c.createStatement();

            sql = "DROP SCHEMA public CASCADE";
            stmt.executeUpdate(sql);

            sql = "CREATE SCHEMA public";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE CARGO_TYPES(" +
                    "ID INT NOT NULL," +
                    "Type VARCHAR(30) NOT NULL," +
                    "UNIQUE (Type)," +
                    "PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE MODELS " +
                    "(ID INT NOT NULL," +
                    " Name VARCHAR(15) NOT NULL," +
                    " Weight INT NOT NULL," +
                    " CargoID INT NOT NULL," +
                    " NumCars INT," +
                    " Capacity INT," +
                    " WeightLim INT," +
                    "UNIQUE (Name)," +
                    "PRIMARY KEY (ID)," +
                    "FOREIGN KEY (CargoID) REFERENCES CARGO_TYPES(ID))";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
