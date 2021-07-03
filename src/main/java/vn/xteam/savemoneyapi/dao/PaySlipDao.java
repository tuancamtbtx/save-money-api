package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.PaySlipEntity;

import java.sql.*;
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
            String query = String.format("SELECT p.id, p.debit_money, p.created_at, p.updated_at, p.customer_code,p.created_by, c.full_name, c.address ,s.code FROM `%s` as p LEFT JOIN customers as c ON p.customer_id = c.id LEFT JOIN saving_books as s ON p.saving_book_id = s.id;", TABLE_NAME);
            LOGGER.info("query: ");
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));
                PaySlipEntity user = PaySlipEntity.builder()
                        .id(rs.getLong("id"))
                        .customerName(rs.getString("full_name"))
                        .customerCode(rs.getString("customer_code"))
                        .savingBookCode(rs.getString("code"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .debitMoney(rs.getDouble("debit_money"))
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
    public PaySlipEntity findOne(String whereClause) {
        return null;
    }

    @Override
    public PaySlipEntity findById(int id) {
        return null;
    }

    @Override
    public boolean updateOne(PaySlipEntity entity) {
        return false;
    }

    @Override
    public Long create(PaySlipEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("INSERT INTO %s (saving_book_id, customer_id,debit_money, created_by,customer_code) VALUES (?, ?, ?, ?, ?)", TABLE_NAME);
            LOGGER.info("query:" + query);
            PreparedStatement statement = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getSavingBookId());
            statement.setLong(2, entity.getCustomerId());
            statement.setDouble(3, entity.getDebitMoney());
            statement.setString(4, "tuan.nguyen15");
            statement.setString(5, entity.getCustomerCode());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating PaySlipDao failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating PaySlipDao failed, no ID obtained.");
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
