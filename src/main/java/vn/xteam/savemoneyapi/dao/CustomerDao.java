package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.IdentityCardEntity;

import java.sql.*;
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
            String query = String.format("SELECT c.id, c.full_name, c.address, c.created_by, c.created_at, c.updated_at, i.code, i.provided_at FROM %s as c LEFT JOIN identity_cards as i ON c.id = i.customer_id", TABLE_NAME);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                IdentityCardEntity identityCardEntity = IdentityCardEntity
                        .builder()
                        .code(rs.getString("code"))
                        .providedAt(rs.getTimestamp("provided_at"))
                        .build();
                CustomerEntity user = CustomerEntity.builder()
                        .id(rs.getLong("id"))
                        .fullName(rs.getString("full_name"))
                        .address(rs.getString("address"))
                        .idCard(identityCardEntity)
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
    public Long create(CustomerEntity entity) {
        LOGGER.info("entity: " + entity);
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = "INSERT INTO customers (full_name, address) VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFullName());
            statement.setString(2, entity.getAddress());
            LOGGER.info("query: " + query);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
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

    public CustomerEntity getCustomerByIdCard(String idCard) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = "SELECT * FROM `customers` as c WHERE id in (SELECT customer_id from identity_cards WHERE code = ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idCard);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return CustomerEntity.builder()
                        .id(rs.getLong("id"))
                        .fullName(rs.getString("full_name"))
                        .address(rs.getString("address"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            boolean isRelease = MysqlDatasource.releaseConnection(conn);
            LOGGER.info("Release connection: " + isRelease);
        }
        return null;
    }
}
