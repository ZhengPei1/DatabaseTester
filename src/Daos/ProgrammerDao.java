package Daos;

import java.sql.Connection;

public interface ProgrammerDao<T> {
    public T runQuery(String[] queryList,  Connection connection);
}
