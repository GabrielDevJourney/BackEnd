import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductsManager {
	private final DatabaseConnection databaseConnection;

	public ProductsManager(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public void sendInfo(List<Product> products) {
		try {
			Connection connection = databaseConnection.getConnection();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();

			for (Product product : products) {
				statement.executeUpdate(product.insert());
			}

			connection.commit();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
