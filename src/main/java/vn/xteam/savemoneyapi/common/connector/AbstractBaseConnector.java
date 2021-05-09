package vn.xteam.savemoneyapi.common.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractBaseConnector {
    public Properties properties;
    protected  static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseConnector.class.getName());

    public AbstractBaseConnector(Properties _properties) {
        this.properties = _properties;
    }
    public abstract void shutdown() throws SQLException;
}
