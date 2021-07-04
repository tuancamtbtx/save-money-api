package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.common.utils.XString;
import vn.xteam.savemoneyapi.entities.v1.TransactionEntity;
import vn.xteam.savemoneyapi.entities.v1.TransactionReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class TransactionDao implements IBaseDao<TransactionEntity> {
    private static final String TABLE_NAME = "transactions";
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDao.class.getName());

    @Override
    public List<TransactionEntity> findAll() {
        List<TransactionEntity> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT * from %s", TABLE_NAME);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));

                TransactionEntity item = TransactionEntity.builder()
                        .customerId(rs.getLong("customer_id"))
                        .savingBookId(rs.getLong("saving_book_id"))
                        .debitMoney(rs.getDouble("debit_money"))
                        .creditMoney(rs.getDouble("credit_money"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                results.add(item);
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
    public TransactionEntity findOne(String whereClause) {
        return null;
    }

    @Override
    public TransactionEntity findById(int id) {
        return null;
    }

    @Override
    public boolean updateOne(TransactionEntity entity) {
        return false;
    }

    @Override
    public Long create(TransactionEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("INSERT INTO %s (saving_book_id,customer_id,rule_id,debit_money, credit_money ) VALUES (?, ?, ?, ?, ?)", TABLE_NAME);
            LOGGER.info("query:" + query);
            PreparedStatement statement = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1,entity.getSavingBookId());
            statement.setLong(2, entity.getCustomerId());
            statement.setLong(3, entity.getRuleId());
            statement.setDouble(4, entity.getDebitMoney());
            statement.setDouble(5, entity.getCreditMoney());
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

    public List<TransactionReport> report() {
        List<TransactionReport> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT r.name, SUM(t.debit_money) as debit_money, SUM(t.credit_money) as credit_money FROM `transactions` t join rules as r on t.rule_id = r.id GROUP BY name";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TransactionReport item = TransactionReport.builder()
                        .name(rs.getString("name"))
                        .creditMoney(rs.getDouble("credit_money"))
                        .debitMoney(rs.getDouble("debit_money"))
                        .diffMoney(rs.getDouble("credit_money") - rs.getDouble("debit_money"))
                        .build();
                results.add(item);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            boolean isRelease = MysqlDatasource.releaseConnection(conn);
            LOGGER.info("Mysql release connection: " + isRelease);
        }
        return results;
    }
}
