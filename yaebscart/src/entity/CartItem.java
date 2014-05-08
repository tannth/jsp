package entity;

public class CartItem {
	private int id;
	private String title;
	private String author;
	private float price;
	private int qtyOrder;

	public CartItem(int id, String title, String author, float price2,
			int qtyOrder) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price2;
		this.qtyOrder = qtyOrder;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public float getPrice() {
		return price;
	}

	public void setQtyOrder(int qtyOrder) {
		this.qtyOrder = qtyOrder;
	}

	public int getQtyOrder() {
		return qtyOrder;
	}

}
