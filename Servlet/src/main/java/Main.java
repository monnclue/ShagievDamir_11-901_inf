import java.sql.*;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ишыщкщ";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from test");
        while (result.next()) {
            System.out.println(result.getInt("id"));
        }
    }
}
