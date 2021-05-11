package vn.xteam.savemoneyapi.service;

import java.util.List;

public interface IGeneralService <T>{
    public List<T> getAll();
    public T getById(String id);
    public T updateById(String id);
    public boolean removeById(String id);
}
