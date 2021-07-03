package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class UserDao implements IBaseDao<UserEntity> {
    private static final String TABLE_NAME = "users";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class.getName());

    @Override
    public List<UserEntity> findAll() {
        Connection conn = MysqlDatasource.getConnection();
        List<UserEntity> results = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT * FROM %s", TABLE_NAME);
            LOGGER.info("query:" + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                UserEntity user = UserEntity.builder()
                        .id(rs.getLong("id"))
                        .userName(rs.getString("username"))
                        .fullName(rs.getString("full_name"))
                        .email(rs.getString("email"))
                        .createdBy(rs.getString("created_by"))
                        .updatedBy(rs.getString("updated_by"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
                results.add(user);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            MysqlDatasource.releaseConnection(conn);
        }
        return results;
    }

    @Override
    public boolean updateOne(UserEntity entity) {
        return false;
    }

    @Override
    public UserEntity findOne(String whereClause) {
        Connection conn = MysqlDatasource.getConnection();
        UserEntity.UserEntityBuilder userBuilder = UserEntity.builder();
        try {
            Statement stmt = conn.createStatement();
            String query = "";
            LOGGER.info("query:" + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userBuilder
                        .id(rs.getLong("id"))
                        .userName(rs.getString("username"))
                        .fullName(rs.getString("full_name"))
                        .email(rs.getString("email"))
                        .createdBy(rs.getString("created_by"))
                        .updatedBy(rs.getString("updated_by"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            boolean isRelease = MysqlDatasource.releaseConnection(conn);
            LOGGER.info("Release connection: " + isRelease);
        }
        return userBuilder.build();
    }

    @Override
    public UserEntity findById(int id) {
        return null;
    }

    @Override
    public Long create(UserEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("INSERT INTO %s (id,password, username, email)" +
                    " VALUES ('%s','%s', '%s', '%s')", TABLE_NAME, entity.getId(), entity.getPassword(), entity.getUserName(), entity.getEmail());
            LOGGER.info("query:" + query);
            int affectedRows = stmt.executeUpdate(query);
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
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
