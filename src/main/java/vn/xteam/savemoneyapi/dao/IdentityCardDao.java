package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.IdentityCardEntity;

import java.sql.*;
import java.util.List;

@Repository
public class IdentityCardDao implements IBaseDao<IdentityCardEntity> {
    private static final String TABLE_NAME = "identity_cards";

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityCardDao.class.getName());

    @Override
    public List<IdentityCardEntity> findAll() {
        return null;
    }

    @Override
    public IdentityCardEntity findOne(String where) {
        return null;
    }

    @Override
    public IdentityCardEntity findById(int id) {
        return null;
    }

    @Override
    public boolean updateOne(IdentityCardEntity entity) {
        return false;
    }

    @Override
    public Long create(IdentityCardEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("INSERT INTO %s (code, customer_id,provided_at) VALUES (?, ?, ?)", TABLE_NAME);
            LOGGER.info("query:" + query);
            PreparedStatement statement = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getCode());
            statement.setLong(2, entity.getCustomerId());
            statement.setTimestamp(3, entity.getProvidedAt());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating IdentityCardEntity failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating IdentityCardEntity failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return 0L;
        } finally {
            boolean isRelease = MysqlDatasource.releaseConnection(conn);
            LOGGER.info("Release connection: " + isRelease);
        }
    }

    @Override
    public boolean removeOne(String id) throws SQLException {
        return false;
    }
}
