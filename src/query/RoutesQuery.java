package query;

public class RoutesQuery implements QueryBuilder {
    
    private String departingStation;
    private String arrivingStation;
    
    public RoutesQuery(String departingStation, String arrivingStation) {
        this.departingStation = departingStation;
        this.arrivingStation = arrivingStation;
    }
    
    @Override
    public String buildQuery() {
        
        String sql;
        
        if (departingStation.equals("") && arrivingStation.equals("")) {
            sql = "SELECT Name FROM Railway.ROUTES";
        } else if (departingStation.equals("")) {
            sql = "SELECT Name FROM Railway.ROUTES " +
                    "WHERE destid = (" +
                    "SELECT id FROM Railway.STATIONS WHERE name = '" + arrivingStation + "')";
        } else if (arrivingStation.equals("")) {
            sql = "SELECT Name FROM Railway.ROUTES " +
                    "WHERE sourceid = (" +
                    "SELECT id FROM Railway.STATIONS WHERE name = '" + departingStation + "')";
        } else {
            sql = "SELECT Name FROM Railway.ROUTES " +
                    "WHERE sourceid = (" +
                    "SELECT id FROM Railway.STATIONS WHERE name = '" + departingStation + "')" +
                    "AND destid = (" +
                    "SELECT id FROM Railway.STATIONS WHERE name = '" + arrivingStation + "')";
        }
        
        return sql;
    }
}
