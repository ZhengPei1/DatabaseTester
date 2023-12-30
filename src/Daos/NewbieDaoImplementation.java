package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NewbieDaoImplementation implements NewbieDao<String>{
    @Override
    public String insert(String tableName, String[] columnNames, String[] values,  Connection connection) {
        try{

            String[] question = new String[values.length];
            Arrays.fill(question, "?");
            String questionString = Arrays.stream(question).collect(Collectors.joining(","));

            PreparedStatement preparedStatement = connection.prepareStatement(String.format("insert into %s (%s) values (%s)", tableName,
                    String.join(",", columnNames), questionString));

            for(int i = 0; i < values.length; ++i){
                preparedStatement.setString(i+1, values[i]);
            }

            preparedStatement.execute();

            return "execution succeeded :)";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String modify() {
        return null;
    }

    @Override
    public String filter() {
        return null;
    }
}
