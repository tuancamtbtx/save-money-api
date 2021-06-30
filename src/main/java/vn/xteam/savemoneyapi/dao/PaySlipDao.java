package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;
import vn.xteam.savemoneyapi.entities.v1.ReceiptEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component

@Repository
public class PaySlipDao implements IBaseDao<PaySlipEntity> {
    private static final String TABLE_NAME = "payslips";

    private static final Logger LOGGER = LoggerFactory.getLogger(PaySlipDao.class.getName());

    @Override
    public List<PaySlipEntity> findAll() {
        List<PaySlipEntity> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT * FROM %s", TABLE_NAME);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                PaySlipEntity user = PaySlipEntity.builder()
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
    public PaySlipEntity findOne(String id) {
        return null;
    }

    @Override
    public boolean updateOne(PaySlipEntity entity) {
        return false;
    }

    @Override
    public boolean create(PaySlipEntity entity) {
        return false;
    }

    @Override
    public boolean removeOne(String id) throws SQLException {
        return false;
    }
}
