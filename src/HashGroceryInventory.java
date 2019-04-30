import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HashGroceryInventory {
	public static TreeMap<Integer, Item> hgi = new TreeMap<Integer, Item>();
	
	public HashGroceryInventory() {
		
	} // default constructor
	
	/*
	 * HashGroceryInventory get, put, delete, and containsKey
	 * methods that call the TreeMap object's get, put, remove
	 * and containsKey methods respectively
	 */
	public static Item get(int key) {
		return hgi.get(key);
	}
	public static void put(int key, Item p) {
		hgi.put(key, p);
	}
	public void delete(int key) {
		hgi.remove(key);
	}
	public boolean containsKey(int in) {
		return hgi.containsKey(in);
	}
	
	/*
	 * prints out every Product mapped in the TreeMap according
	 * to the format given in Product's print method to a file
	 */
	public void write(PrintWriter outWriter) {
		for (Entry<Integer, Item> entry : hgi.entrySet()) {
		    Item value = entry.getValue();
		    
			outWriter.println(value.print());
			outWriter.flush();
		}	
	}
	
	/*
	 * prints out every Product mapped in the TreeMap according
	 * to the format given in Product's print method to the console
	 */
	public void writeToConsole() {
		System.out.println("Inventory Number | Item Description | Quantity | Price " + 
				"| Date Purchased | Timestamp");
		for (Entry<Integer, Item> entry : hgi.entrySet()) {
		    Item value = entry.getValue();
		    
		    System.out.println(value.print());
		}	
	}
	

	/*
	 * a method that modifies the inventory number
	 * by taking the key and new inventoryNumber (key)
	 * as arguments:
	 * retrieves the Product mapped to that number,
	 * modifies the number for said Product and puts
	 * it in its appropriate spot in the map, then 
	 * deletes the original copy
	 */
	public static void modIN(int key, int newIN) {
		Item modProd = hgi.get(key);
		modProd.setInventoryNumber(newIN);
		hgi.put(newIN, modProd);
		hgi.remove(key);
	}

	/*
	 * a method that modifies the item description 
	 * by taking the key and new itemDescription as 
	 * arguments:
	 * retrieves the Product mapped to that number,
	 * modifies the description for said Product
	 */
	public static void modID(int key, String newID) {
		Item modProd = hgi.get(key);
		modProd.setItemDescription(newID);
	}

	/*
	 * a method that modifies the quantity by taking
	 * the key and new quantity as arguments:
	 * retrieves the Product mapped to that number,
	 * modifies the quantity for said Product
	 */
	public static void modQ(int key, int newQ) {
		Item modProd = hgi.get(key);
		modProd.setQuantity(newQ);
	}

	/*
	 * a method that modifies the price by taking 
	 * the key and new price as arguments:
	 * retrieves the Product mapped to that number,
	 * modifies the price for said Product
	 */
	public static void modP(int key, double newP) {
		Item modProd = hgi.get(key);
		modProd.setPrice(newP);
	}

	/*
	 * a method that modifies the date of purchase by
	 * taking the key and new new date of purchase as 
	 * arguments:
	 * retrieves the Product mapped to that number,
	 * modifies the date of purchase for said Product
	 */
	public static void modDP(int key, String newDP) {
		Item modProd = hgi.get(key);
		modProd.setDatePurchased(newDP);
	}

	
}
