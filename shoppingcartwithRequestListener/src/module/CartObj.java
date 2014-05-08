package module;

import java.io.Serializable;
import java.util.HashMap;

public class CartObj implements Serializable {
	private String owner;
	private HashMap<String, Integer> cart;

	public CartObj() {
		this.owner = "000";
		this.cart = new HashMap<String, Integer>();
	}

	public CartObj(String owner) {
		this.owner = owner;
		this.cart = new HashMap<String, Integer>();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public HashMap<String, Integer> getCart() {
		return cart;
	}

	public void addItem(String title) {
		int quantity = 1;
		if (cart.containsKey(title)) {
			quantity = cart.get(title) + 1;
		}
		cart.put(title, quantity);
	}

	public void removeItem(String title) {
		if (cart.containsKey(title)) {
			cart.remove(title);
		}
	}

	public void updateItem(String title, int quantity) {
		if (cart.containsKey(title)) {
			cart.put(title, quantity);
		}
	}
}
