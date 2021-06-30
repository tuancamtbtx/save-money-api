package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class CustomerDao implements IBaseDao<CustomerEntity> {
    private static final String TABLE_NAME = "customers";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDao.class.getName());

    @Override
    public List<CustomerEntity> findAll() {
        List<CustomerEntity> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT * FROM %s", TABLE_NAME);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                CustomerEntity user = CustomerEntity.builder()
                        .id(rs.getString("id"))
                        .fullName(rs.getString("full_name"))
                        .address(rs.getString("address"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
                results.add(user);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            boolean isRelease = MysqlDatasource.releaseConnection(conn);
            LOGGER.info("Mysql release connection: " + isRelease);
        }
        return results;
    }

    @Override
    public CustomerEntity findOne(String id) {
        return null;
    }

    @Override
    public boolean updateOne(CustomerEntity entity) {
        return false;
    }

    @Override
    public boolean create(CustomerEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("INSERT INTO %s (id,full_name,address )" +
                    " VALUES ('%s','%s', '%s')", TABLE_NAME,'2',entity.getFullName(), entity.getAddress());
            LOGGER.info("query:" + query);
            return stmt.execute(query);

        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return false;
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
