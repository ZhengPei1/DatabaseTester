package Daos;

import Service.DatabaseConnection;

import java.sql.Connection;

public interface NewbieDao<T>{
    public T insert(String tableName, String[] columnNames, String[] values, Connection connection);
    public T delete();
    public T modify();
    public T filter();
}
