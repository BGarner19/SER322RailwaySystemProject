package query;

public class TrainQuery implements QueryBuilder {

    private String cargoType;
    private String routeID;

    public TrainQuery(String cargoType, String routeID) {
        this.cargoType = cargoType;
        this.routeID = routeID;
    }

    @Override
    public String buildQuery() {

        String sql;

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

        return sql;

    }
}
