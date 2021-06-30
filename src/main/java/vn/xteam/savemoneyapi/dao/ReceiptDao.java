package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class ReceiptDao implements IBaseDao<ReceiptEntity> {
    private static final String TABLE_NAME = "receipts";
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptDao.class.getName());

    @Override
    public List<ReceiptEntity> findAll() {
        List<ReceiptEntity> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT * FROM %s", TABLE_NAME);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                ReceiptEntity user = ReceiptEntity.builder()
                        .id(rs.getString("id"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .amount(rs.getDouble("amount"))
                        .createdBy(rs.getString("created_by"))
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
    public ReceiptEntity findOne(String id) {
        return null;
    }

    @Override
    public boolean updateOne(ReceiptEntity entity) {
        return false;
    }

    @Override
    public boolean create(ReceiptEntity entity) {
        return false;
    }

    @Override
    public boolean removeOne(String id) throws SQLException {
        return false;
    }
}
