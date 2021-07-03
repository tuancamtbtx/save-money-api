package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.common.utils.XString;
import vn.xteam.savemoneyapi.entities.v1.SavingBookEntity;

import java.sql.*;
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
            String query = "SELECT s.id,s.status, " +
                    "s.code,s.type, c.id as customer_id," +
                    " c.full_name, s.created_by, s.created_at, " +
                    " c.address, s.amount, " +
                    " s.period , s.interest_rate " +
                    " FROM `saving_books` as s " +
                    " left join customers as c " +
                    " on c.id = s.customer_id; ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SavingBookEntity saving = SavingBookEntity.builder()
                        .id(rs.getLong("id"))
                        .type(rs.getInt("type"))
                        .customerName(rs.getString("full_name"))
                        .amount(rs.getDouble("amount"))
                        .customerId(rs.getLong("customer_id"))
                        .customerAddress(rs.getString("address"))
                        .status(rs.getInt("status"))
                        .code(rs.getString("code"))
                        .createdBy(rs.getString("created_by"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .period(rs.getInt("period"))
                        .interestRate(rs.getFloat("interest_rate"))
                        .build();
                results.add(saving);
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
    public SavingBookEntity findOne(String whereClause) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("SELECT s.id,s.status, " +
                    "s.code,s.type, c.id as customer_id," +
                    " c.full_name, s.created_by, s.created_at, " +
                    " c.address, s.amount, " +
                    " s.period , s.interest_rate " +
                    " FROM `saving_books` as s " +
                    " left join customers as c " +
                    " on c.id = s.customer_id " +
                    "WHERE %s", whereClause);
            LOGGER.info("query: " + query);
            PreparedStatement statement = conn.prepareStatement(query);
//            statement.set(1, whereClause);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return SavingBookEntity.builder()
                        .id(rs.getLong("id"))
                        .type(rs.getInt("type"))
                        .customerName(rs.getString("full_name"))
                        .amount(rs.getDouble("amount"))
                        .customerId(rs.getLong("customer_id"))
                        .customerAddress(rs.getString("address"))
                        .status(rs.getInt("status"))
                        .code(rs.getString("code"))
                        .createdBy(rs.getString("created_by"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .period(rs.getInt("period"))
                        .interestRate(rs.getFloat("interest_rate"))
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

    @Override
    public SavingBookEntity findById(int id) {
        return null;
    }

    @Override
    public boolean updateOne(SavingBookEntity entity) {
        return false;
    }

    @Override
    public Long create(SavingBookEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("INSERT INTO %s (code, customer_id,amount, type, created_by,period,interest_rate ) VALUES (?, ?, ?, ?, ?, ?,?)", TABLE_NAME);
            LOGGER.info("query:" + query);
            PreparedStatement statement = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            String code = XString.getCodeSaving(entity.getCustomerId(), entity.getType());
            statement.setString(1, code);
            statement.setLong(2, entity.getCustomerId());
            statement.setDouble(3, entity.getAmount());
            statement.setInt(4, entity.getType());
            statement.setString(5, "tuan.nguyen15");
            statement.setInt(6, entity.getPeriod());
            statement.setFloat(7, entity.getInterestRate());

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
