package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.RuleEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RuleDao implements IBaseDao<RuleEntity> {

    private static final String TABLE_NAME = "rules";
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleDao.class.getName());

    @Override
    public List<RuleEntity> findAll() {
        List<RuleEntity> results = new ArrayList<>();
        Connection conn = MysqlDatasource.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = String.format("select * from %s", TABLE_NAME);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LOGGER.info("check: " + rs.getString("id"));

                RuleEntity rule = RuleEntity.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .interestRate(rs.getFloat("interest_rate"))
                        .minAmount(rs.getDouble("min_amount"))
                        .period(rs.getInt("period"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
                results.add(rule);
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
    public RuleEntity findOne(String whereClause) {
        return null;
    }

    @Override
    public RuleEntity findById(int id) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME);
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return RuleEntity.builder()
                        .id(rs.getLong("id"))
                        .period(rs.getInt("period"))
                        .minAmount(rs.getDouble("min_amount"))
                        .interestRate(rs.getFloat("interest_rate"))
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

    @Override
    public boolean updateOne(RuleEntity entity) {

        return false;
    }

    @Override
    public Long create(RuleEntity entity) {
        Connection conn = MysqlDatasource.getConnection();
        try {
            String query = String.format("INSERT INTO %s (name, interest_rate,period,created_by, min_amount) VALUES (?, ?, ?, ?,?)", TABLE_NAME);
            LOGGER.info("query:" + query);
            PreparedStatement statement = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setFloat(2, entity.getInterestRate());
            statement.setInt(3, entity.getPeriod());
            statement.setString(4, "tuan.nguyen15");
            statement.setDouble(5, entity.getMinAmount());
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
