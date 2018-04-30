package query;

public class DefaultQuery implements QueryBuilder {


    private String sql;

    public DefaultQuery (String sql) {
        this.sql = sql;
    }

    @Override
    public String buildQuery() {
        return sql;
    }
}
