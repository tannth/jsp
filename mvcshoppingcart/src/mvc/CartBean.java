package mvc;

import java.util.HashMap;
import java.util.Hashtable;

public class CartBean extends HashMap {
	public CartBean() {
		// TODO Auto-generated constructor stub
	}

	public void addBook(BookDTO book) {
		String key = book.getTitle();
		if (this.containsKey(key)) {
			int oldQuantity = ((BookDTO) this.get(key)).getQuantity();
			((BookDTO) this.get(key)).setQuantity(oldQuantity + 1);
		} else {
			this.put(book.getTitle(), book);
		}
	}

	public boolean removeBook(String title) {
		if (this.containsKey(title)) {
			this.remove(title);
			return true;
		}
		return false;
	}
}
