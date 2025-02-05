public class Product {
	private String name;
	private double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String insert(){
		return "INSERT INTO Products " +
				"VALUES (NULL,'%s',%f)".formatted(name,price);
	}
}
