package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SavingBookDao implements IBaseDao<SavingBookEntity> {
    private static final String TABLE_NAME = "saving_books";
    private static final Logger LOGGER = LoggerFactory.getLogger(SavingBookDao.class.getName());


    @Override
    public List<SavingBookEntity> findAll() {
        List<SavingBookEntity> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT s.id,s.type, c.id as customer_id, c.full_name, s.created_by, s.created_at, c.address, s.amount " +
                    "FROM `saving_books` as s " +
                    "join customers as c " +
                    "on c.id = s.customer_id;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                SavingBookEntity saving = SavingBookEntity.builder()
                        .id(rs.getString("id"))
                        .type(rs.getInt("type"))
                        .customerName(rs.getString("full_name"))
                        .amount(rs.getLong("amount"))
                        .customerId(rs.getString("customer_id"))
                        .customerAddress(rs.getString("address"))
                        .createdBy(rs.getString("created_by"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                results.add(saving);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            boolean isRelease = MysqlDatasource.releaseConnection(conn);
            LOGGER.info("Mysql release connection: " + isRelease);
        }
        return  results;
    }

    @Override
    public SavingBookEntity findOne(String id) {
        return null;
    }

    @Override
    public boolean updateOne(SavingBookEntity entity) {
        return false;
    }

    @Override
    public boolean create(SavingBookEntity entity) {
        return false;
    }

    @Override
    public boolean removeOne(String id) throws SQLException {
        return false;
    }
}
