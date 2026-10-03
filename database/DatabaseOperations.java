package database;

public interface DatabaseOperations<T> {
    void add(T item);
    void update(T item);
    void delete(int id);
    T getById(int id);
}