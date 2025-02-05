import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		ClientsManager ClientsManager = new ClientsManager(databaseConnection);
		ProductsManager productsManager = new ProductsManager(databaseConnection);

		List<ClientsProductsView> clientsProductsViews = ClientsManager.getClientsProducts();
		List<Client> allClients = ClientsManager.getClients();
		List<Product> products = new ArrayList<>();


		Product iphone1 = new Product("Iphone 1 test", 9999.99);
		Product iphone2 = new Product("Iphone 2 test", 999.99);
		Product iphone3 = new Product("Iphone 3 test", 99.99);
		Product iphone4 = new Product("Iphone 4 test", 9.99);

		products.add(iphone1);
		products.add(iphone2);
		products.add(iphone3);
		products.add(iphone4);

		productsManager.sendInfo(products);

		for(Client client : allClients) {
			System.out.println("Name: " + client.getName());
			System.out.println("Nif: " + client.getNif());
			System.out.println("Address: " + client.getAddress());
			System.out.println("Email: " + client.getEmail());
			System.out.println("-----------------");
		}

		for(ClientsProductsView cp : clientsProductsViews) {
			System.out.println("Client: " + cp.getClientName() +
					" | Product: " + cp.getProductName());
		}
	}


	}
