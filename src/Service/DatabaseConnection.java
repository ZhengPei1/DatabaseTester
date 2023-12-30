package Service;

import Model.UserConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    UserConnection userConnection;
    Connection connection;

    //
    public DatabaseConnection(String url, String username, String password) {
        userConnection = new UserConnection(url, username, password);
    }

    //
    public boolean establishConnection(){
        try{
            Class.forName ("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(userConnection.getUrl(), userConnection.getUsername(), userConnection.getPassword());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //

    public UserConnection getUserConnection() {
        return userConnection;
    }

    public void setUserConnection(UserConnection userConnection) {
        this.userConnection = userConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
