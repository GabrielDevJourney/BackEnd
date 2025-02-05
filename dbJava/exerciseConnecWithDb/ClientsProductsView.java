public class ClientsProductsView {
	private final String clientName;
	private final String productName;

	public ClientsProductsView(String clientName, String productName) {
		this.clientName = clientName;
		this.productName = productName;
	}

	public String getClientName() {
		return clientName;
	}

	public String getProductName() {
		return productName;
	}
}
