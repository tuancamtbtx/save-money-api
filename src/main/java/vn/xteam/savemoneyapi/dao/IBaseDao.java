package vn.xteam.savemoneyapi.dao;

import java.util.List;

public interface IBaseDao<T> {
    public List<T> getAll();
    public T getById(String id);
    public T updateById(String id);
    public boolean removeById(String id);
}
