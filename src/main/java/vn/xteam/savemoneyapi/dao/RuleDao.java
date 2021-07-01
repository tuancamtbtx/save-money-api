package vn.xteam.savemoneyapi.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import vn.xteam.savemoneyapi.common.datasource.MysqlDatasource;
import vn.xteam.savemoneyapi.entities.v1.CustomerEntity;
import vn.xteam.savemoneyapi.entities.v1.IdentityCardEntity;
import vn.xteam.savemoneyapi.entities.v1.RuleEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public RuleEntity findOne(String id) {
        return null;
    }

    @Override
    public boolean updateOne(RuleEntity entity) {
        return false;
    }

    @Override
    public Long create(RuleEntity entity) {
        return null;
    }

    @Override
    public boolean removeOne(String id) throws SQLException {
        return false;
    }
}
