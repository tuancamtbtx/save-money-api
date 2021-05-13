package vn.xteam.savemoneyapi.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.xteam.savemoneyapi.common.pool.MysqlPoolManager;

import java.sql.Connection;

public class MysqlDatasource {
    public static MysqlPoolManager mPool = new MysqlPoolManager(4);
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlDatasource.class.getName());

    private MysqlDatasource() {
        super();
    }

    public static Connection getConnection() {
        Connection connection = mPool.getConnection();
        LOGGER.info("Mysql Pool Status: " + mPool);
        return connection;
    }

    public static boolean releaseConnection(Connection connection) {
        return mPool.releaseConnection(connection);
    }

}
