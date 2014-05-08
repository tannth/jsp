package s9.ex1;

public class Billing {
	private int registrationcost = 0;
	private int quantity = 0;

	public Billing() {
		// TODO Auto-generated constructor stub
	}

	public int getRegistrationcost() {
		return (registrationcost * quantity);
	}

	public void setRegistrationcost(int prod) {
		this.registrationcost = prod;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
