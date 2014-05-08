package mvc;

import java.io.Serializable;

public class BookDTO implements Serializable {
	private String title;
	private int quantity;

	public BookDTO() {
		// TODO Auto-generated constructor stub
	}

	public BookDTO(String title) {
		super();
		this.title = title;
		this.quantity = 1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
