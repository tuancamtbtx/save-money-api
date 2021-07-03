package vn.xteam.savemoneyapi.dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDao<T> {
    public List<T> findAll();

    public T findOne(String whereClause);

    public T findById(int id);

    public boolean updateOne(T entity);

    public Long create(T entity);

    public boolean removeOne(String id) throws SQLException;
}
