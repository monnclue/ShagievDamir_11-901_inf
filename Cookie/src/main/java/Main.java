import java.sql.*;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ишыщкщ";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) {
        try {
            Connection connection =
                    DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM users;");

            Statement statement1 = connection.createStatement();
            ResultSet result1 = statement1.executeQuery("SELECT * FROM users;");

            Long count = 0L;
            while (result.next()) {
                count = result.getLong(1);
            }

            for (int i = 0; i < count; i++) {

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
