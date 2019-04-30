
public class Item {
	int inventoryNumber;
	String itemDescription;
	int quantity;
	double price;
	String datePurchased;
	String timestamp;
	Item next;
	
	public Item() {
		inventoryNumber = 0;
		itemDescription = null;
		quantity = 0;
		price = 0;
		datePurchased = null;
		timestamp = null;
		next = null;
	} // default constructor
	
	public Item(int in, String id, int q, double p, String dp, String ts) {
		inventoryNumber = in;
		itemDescription = id;
		quantity = q;
		price = p;
		datePurchased = dp;
		timestamp = ts;
		next = null;
	} // constructor with arguments
	
	
	public int getInventoryNumber() {
		return inventoryNumber;
	}
	public void setInventoryNumber(int in) {
		inventoryNumber = in;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String id) {
		itemDescription = id;
	}
	

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int q) {
		quantity = q;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double p) {
		price = p;
	}
	
	public String getDatePurchased() {
		return datePurchased;
	}
	public void setDatePurchased(String dp) {
		datePurchased = dp;
	}
	
	/*
	 * a print method that prints the different components of a Product
	 * in a format like so:
	 * Inventory Number | Item Description | Quantity | Price | Date Purchased | Timestamp
	 */
	public String print() {
		String printedProduct = inventoryNumber + " | " + itemDescription + " | " + 
				quantity + " | " + price + " | "  + datePurchased + " | " + timestamp;
		return printedProduct;
	} // prints product for output file

	
}
