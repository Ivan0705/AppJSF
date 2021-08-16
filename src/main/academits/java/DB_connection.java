import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {

    public static void main(String[] args) {
        DB_connection connection = new DB_connection();
        System.out.println(connection.getConnection());
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String userName = "root";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/jsftest?serverTimezone=UTC&useSSL=false";
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
