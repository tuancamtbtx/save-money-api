package vn.xteam.savemoneyapi.common.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnector extends AbstractBaseConnector{
    public  Connection connection;

    public MysqlConnector(Properties _properties) {
        super(_properties);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(
                this.properties.getProperty("uri"),
                this.properties.getProperty("username"),
                this.properties.getProperty("password")
        );
        return this.connection;
    }
    @Override
    public void shutdown() throws SQLException {

    }
}
