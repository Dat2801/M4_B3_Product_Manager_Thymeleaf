package service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T edit(int id, T t);
    T create(T t);
    T findById(int id);
    List<T> findByName(String name);
    void delete(int id);
}
