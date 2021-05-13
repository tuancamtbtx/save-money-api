package vn.xteam.savemoneyapi.dao;

import java.util.List;

public interface IBaseDao<T> {
    public List<T> findAll();
    public T findOne(String id);
    public boolean updateOne(T entity);
    public boolean create(T entity);
    public boolean removeOne(String  id);
}
