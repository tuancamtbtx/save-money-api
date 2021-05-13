package vn.xteam.savemoneyapi.common.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.xteam.savemoneyapi.common.connector.MysqlConnector;
import vn.xteam.savemoneyapi.config.MainConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MysqlPoolManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlPoolManager.class.getName());

    private static final LinkedList<Connection> availableConnections = new LinkedList<>();

    private final int maxConnection;

    public MysqlPoolManager(int maxConnection) {
        this.maxConnection = maxConnection;
        initializeConnectionPool();
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("uri", MainConfig.MYSQL_URI);
        properties.setProperty("username", MainConfig.MYSQL_USERNAME);
        properties.setProperty("password", MainConfig.MYSQL_PASSWORD);
        properties.setProperty("max_connections", "4");
        properties.setProperty("min_connections", "2");
        LOGGER.info(properties.toString());
        return properties;
    }

    private synchronized void initializeConnectionPool() {
        try {
            while (!checkIfConnectionPoolIsFull()) {
                Properties props = getProperties();
                MysqlConnector connector = new MysqlConnector(props);
                Connection newConnection = connector.getConnection();
                availableConnections.add(newConnection);
            }
            notifyAll();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private boolean checkIfConnectionPoolIsFull() {
        return availableConnections.size() >= maxConnection;
    }

    public  Connection getConnection() {
        while (availableConnections.size() == 0) {
            // Wait for an existing connection to be freed up.
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        // Retrieves and removes the first element of this list
        return availableConnections.poll();
    }

    public boolean releaseConnection(Connection connection) {
        try {
            if (connection.isClosed()) {
                initializeConnectionPool();
            } else {
                //                notifyAll();
                // Adds the specified element as the last element of this list
                return availableConnections.offer(connection);
                // Wake up threads that are waiting for a connection
//                return isReleased;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public synchronized String toString() {
        Properties properties = this.getProperties();
        StringBuilder sb = new StringBuilder().append("Max=").append(properties.getProperty("max_connections")).append(" | Available=").append(availableConnections.size()).append(" | Busy=").append(Integer.parseInt(properties.getProperty("max_connections")) - availableConnections.size());
        return sb.toString();
    }
}
