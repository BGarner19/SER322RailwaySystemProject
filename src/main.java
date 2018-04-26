import java.sql.Connection;
import java.sql.DriverManager;

public class main {
    public static void main(String args[]) {
        Connection c = null;
        String sql;
        Database db = new Database();

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/SER322_DB",
                            "postgres", "322");

            db.createTables(c);
            db.fillTables(c);

            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
