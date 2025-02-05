import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClientsManager {
	private final DatabaseConnection databaseConnection;

	public ClientsManager(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public List<Client> getClients() {
		List<Client> clients = new LinkedList<>();
		String query = "SELECT * FROM Clients";

		try {
			Connection connection = databaseConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int nif = resultSet.getInt("nif");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");

				clients.add(new Client(nif, name, address, email));
			}

			databaseConnection.closeConnection();
		} catch (SQLException ex) {
			System.out.println("Error getting clients: " + ex.getMessage());
		}

		return clients;
	}

	public List<ClientsProductsView> getClientsProducts(){
		List<ClientsProductsView> clientsProductsViewList = new LinkedList<>();
		String query =  """
                SELECT c.name as client_name, p.name as product_name
                FROM Clients c
                LEFT JOIN Orders o ON c.Id = o.ClientId
                LEFT JOIN OrdersProducts op ON o.Id = op.OrderId
                LEFT JOIN Products p ON op.ProductId = p.Id
                """;
		try{
			Connection connection = databaseConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while(resultSet.next()){
				String clientName = resultSet.getString("client_name");
				String productName = resultSet.getString("product_name");
				clientsProductsViewList.add(new ClientsProductsView(clientName,productName));

			}
			databaseConnection.closeConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return clientsProductsViewList;
	}

}

