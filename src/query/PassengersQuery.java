package query;

public class PassengersQuery implements QueryBuilder {

    private String ticketType;
    private String tripID;

    public PassengersQuery(String ticketType, String tripID) {
        this.ticketType = ticketType;
        this.tripID = tripID;
    }

    @Override
    public String buildQuery() {
        String sql;

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

        return sql;
    }
}
