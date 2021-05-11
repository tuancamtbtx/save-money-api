package vn.xteam.savemoneyapi.dao;

import java.util.List;

public interface IBaseDao<T> {
    public List<T> findAll();
    public T findById(String id);
    public boolean updateById(T entity);
    public boolean create(T entity);
    public boolean removeById(String  id);
}
