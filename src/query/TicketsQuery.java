package query;

public class TicketsQuery implements QueryBuilder {
    
    private String departingStation;
    private String arrivingStation;
    private String departingTime;
    
    public TicketsQuery(String departingStation, String arrivingStation, String departingTime) {
        this.departingStation = departingStation;
        this.arrivingStation = arrivingStation;
        this.departingTime = departingTime;
    }
    
    @Override
    public String buildQuery() {
        
        String sql;
        
        if (departingStation.equals("") && arrivingStation.equals("") && departingTime.equals("")) {
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
            sql = "SELECT ID FROM Railway.TICKETS WHERE tripID IN " +
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
        
        return sql;
    }
}
