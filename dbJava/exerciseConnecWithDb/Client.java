
public class Client {
	private final int nif;
	private final String name;
	private final String address;
	private final String email;

	public Client(int nif, String name, String address, String email) {
		this.nif = nif;
		this.name = name;
		this.address = address;
		this.email = email;
	}

	public int getNif() {
		return nif;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
}
