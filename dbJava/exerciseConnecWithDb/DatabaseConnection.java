import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private Connection connection;

	public Connection getConnection() {
		String dbURL = "jdbc:mysql://localhost:3306/EcomExercise";
		String username = "root";
		String password = "";

		try {
			connection = DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException ex) {
			System.out.println("Connection failed: " + ex.getMessage());
		}
		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException ex) {
			System.out.println("Failure to close: " + ex.getMessage());
		}
	}
}
