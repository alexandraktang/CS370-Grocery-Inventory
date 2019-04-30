import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EditMenuHandler extends Observable implements ActionListener {
	static JFrame fileFrame;
	static LogFile logString = new LogFile();
	static PrintWriter logWriter = null;
	static HashGroceryInventory hgi = PhaseOne.mainHGI;
	
	public EditMenuHandler(JFrame ff) {
		fileFrame = ff;
	}
	
	public static PrintWriter getLogWriter() {
		return logWriter;
	}
	
	public static String getLogString() {
		return logString.toString();
	}
	
	/*
	 * an actionPerformed method that determines what to do when a user clicks
	 * on certain menu buttons (ex. Add Product, Edit Product, Remove Product) 
	 * under the Edit Menu 
	 */
	public void actionPerformed(ActionEvent e) {
		String menuItemName = e.getActionCommand();
		Date time = new Date();
		
		/*
		 * if a user presses "Add Product", they are asked to input the six different
		 * components of a Product. if the inventory number is already in use, the method
		 * informs the user and quits. otherwise, a new Product is made and added to the
		 * HashGroceryInventory (TreeMap) and the log string is updated
		 */
		if (menuItemName.equals("Add Product")) {
			int inventoryNumber = Integer.parseInt(JOptionPane.showInputDialog
					(null, "Please enter a six-digit inventory number: "));
			if (hgi.containsKey(inventoryNumber) == true) {
				JOptionPane.showMessageDialog(null, "This number is currently in use. "
						+ "Please try again.");
			}
			else {
				String itemDescription = JOptionPane.showInputDialog
						(null, "Please enter the item's description (ex. Apples, Eggs, etc.): ");
				int quantity = Integer.parseInt(JOptionPane.showInputDialog
						(null, "Please enter the quantity: "));
				double price = Double.parseDouble(JOptionPane.showInputDialog
						(null, "Please enter the price (ex. 2.99, 18.99, etc.): "));
				String datePurchased = JOptionPane.showInputDialog
						(null, "Please enter the date it was purchased: ");
				String timestamp = time.toString();
				
				Item newProduct = new Item(inventoryNumber, itemDescription,
						quantity, price, datePurchased, timestamp);
				hgi.put(inventoryNumber, newProduct);	
				
				logString.append("Item added: Product #" + inventoryNumber + " (" + itemDescription + 
						") on " + time.toString() + System.getProperty("line.separator"));

				JOptionPane.showMessageDialog(null, "Item successfully added.");
			}
		}
		
		/*
		 * if a user presses "Edit Product", they are asked to input one of five different
		 * possible edits. for each, the user is asked the Product's current inventory number
		 * to retrieve the Product and create edits. if there is no Product with the inputted
		 * inventory number in existence, the user is informed and the method quits.
		 * otherwise, the method will complete the modifications.
		 */
		else if (menuItemName.equals("Edit Product")) {
			String userInput = JOptionPane.showInputDialog(null, "Please input the number "
					+ "of the component you would like to edit. \n" + "1: Inventory Number \n"
					+ "2: Item Description \n3: Quantity \n4: Price \n5: Date of Purchase");
			int ui = Integer.parseInt(userInput);
			
			/*
			 * the method checks if the Product exists first, then asks for a new inventory
			 * number. if the new inventory number is already mapped to another Product, the
			 * user is informed and asked to try again.
			 * otherwise, the number is modified via HashGroceryInventory's modIN method.
			 * the log string is updated and the user receives a button informing them that
			 * the changes have been made.
			 */
			if (ui == 1) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
						+ "item's current inventory number: "));
				if (hgi.containsKey(in) == false) {
					JOptionPane.showMessageDialog(null, "No product currently has this number. "
							+ "Please try again.");
				}
				else {
					int newIN = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
						+ "item's new inventory number: "));
					if (hgi.containsKey(newIN) == true) {
						JOptionPane.showMessageDialog(null, "This number is currently in use. Please "
								+ "try again.");
					}
					else {
						String name = hgi.get(in).getItemDescription();
						hgi.modIN(in, newIN);
						int revisedIN = (hgi.get(newIN)).getInventoryNumber();
						
						logString.append("Inventory number changed: Product #" + in + " (" + name  + 
								") is now Product #" + revisedIN + " (" + name + ") as of " + 
								time.toString() + System.getProperty("line.separator"));
						
						JOptionPane.showMessageDialog(null, "Inventory number successfully changed.");
					}
				}
			} // inventory number
			
			/*
			 * the method checks if the Product exists first, then asks for a new item
			 * description. the description is modified via HashGroceryInventory's modID method.
			 * the log string is updated and the user receives a button informing them that
			 * the changes have been made.
			 */
			else if (ui == 2) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
						+ "item's inventory number: "));
				if (hgi.containsKey(in) == false) {
					JOptionPane.showMessageDialog(null, "This product does not exist. "
							+ "Please try again.");
				}
				else {
					String newID = JOptionPane.showInputDialog(null, "Please input the "
						+ "new item description: ");

						String origName = hgi.get(in).getItemDescription();
						hgi.modID(in, newID);
						String name = (hgi.get(in)).getItemDescription();
						
						logString.append("Item description changed: Product #" + in + " (" + 
								origName + ") is now Product #" + in + " (" + name + ")"
								+ " as of " + time.toString() + System.getProperty("line.separator"));
						
						JOptionPane.showMessageDialog(null, "Item description successfully changed.");
				}
			} // item description
			
			/*
			 * the method checks if the Product exists first, then asks for a new quantity.
			 * the quantity is modified via HashGroceryInventory's modQ method.
			 * the log string is updated and the user receives a button informing them that
			 * the changes have been made.
			 */
			else if (ui == 3) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
						+ "item's inventory number: "));
				if (hgi.containsKey(in) == false) {
					JOptionPane.showMessageDialog(null, "This product does not exist. "
							+ "Please try again.");
				}
				else {
					int newQ = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
							+ "item's new quantity: "));
					String name = (hgi.get(in)).getItemDescription();
					hgi.modQ(in, newQ);
					
					int q = (hgi.get(in)).getQuantity();
					logString.append("Inventory number changed: Product #" + in + " (" +
							name + ")'s quantity is now " + q + " as of " + 
							time.toString() + System.getProperty("line.separator"));
					
					JOptionPane.showMessageDialog(null, "Quantity successfully changed.");
				}
			} // quantity
			
			/*
			 * the method checks if the Product exists first, then asks for a new price.
			 * the price is modified via HashGroceryInventory's modP method.
			 * the log string is updated and the user receives a button informing them that
			 * the changes have been made.
			 */
			else if (ui == 4) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
						+ "item's inventory number: "));
				if (hgi.containsKey(in) == false) {
					JOptionPane.showMessageDialog(null, "This product does not exist. "
							+ "Please try again.");
				}
				else {
					double newP = Double.parseDouble(JOptionPane.showInputDialog(null, "Please input "
							+ "the item's new price (ex. 2.99, 18.99, etc.): "));
					String name = (hgi.get(in)).getItemDescription();
					hgi.modP(in, newP);
					
					double p = (hgi.get(in)).getPrice();
					logString.append("Inventory number changed: Product #" + in + " (" + name +
							")'s price is now " + p + " as of " + time.toString() + 
							System.getProperty("line.separator"));
					
					JOptionPane.showMessageDialog(null, "Price successfully changed.");
				}
			} // price
			
			/*
			 * the method checks if the Product exists first, then asks for a new date
			 * of purchase. the date is modified via HashGroceryInventory's modDP method.
			 * the log string is updated and the user receives a button informing them that
			 * the changes have been made.
			 */
			else if (ui == 5) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the "
						+ "item's inventory number: "));
				if (hgi.containsKey(in) == false) {
					JOptionPane.showMessageDialog(null, "This product does not exist. "
							+ "Please try again.");
				}
				else {
					String newDP = JOptionPane.showInputDialog(null, "Please input the "
							+ "item's new date of purchase: ");
					String name = (hgi.get(in)).getItemDescription();
					hgi.modDP(in, newDP);
					
					String dp = (hgi.get(in)).getDatePurchased();
					logString.append("Inventory number changed: Product #" + in + " (" +  name +
							")'s date of purchase is now " + dp + " as of " + time.toString() 
							+ System.getProperty("line.separator"));
					
					JOptionPane.showMessageDialog(null, "Date of purchase successfully changed.");
				}
			} // date of purchase
			
			else JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
		}
		
		/*
		 * if a user presses "Remove Product", they are asked to input the inventory
		 * number of said Product. if the inventory number is not in use, the method
		 * informs the user and quits. otherwise, the method retrieves said Product
		 * and deletes it, then updates the log string
		 */
		else if (menuItemName.equals("Remove Product")) {
			int inventoryNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter "
					+ "the item's inventory number: "));
			if (hgi.containsKey(inventoryNumber) == false) {
				JOptionPane.showMessageDialog(null, "This product does not exist. "
						+ "Please try again.");
			}
			else {
				String name = (hgi.get(inventoryNumber)).getItemDescription();
				hgi.delete(inventoryNumber);
				
				logString.append("Item removed: Product #" + inventoryNumber + " (" + name +
						") on " + time.toString() + System.getProperty("line.separator"));
				
				JOptionPane.showMessageDialog(null, "Item successfully removed.");
			}
		}
		
	}// action performed
	
	//this method asks the user for an log filename and returns it
	public static String createLogFile() {
		String log = JOptionPane.showInputDialog(null, "Please name the log file (ex. log.txt): ");
		return log;
	}
	/*
	 * this method takes the log String and a log filename as arguments,
	 * then creates a file with said filename and writes the log string of 
	 * log messages to it
	 */
	public static void printLogFile(String log, String s) throws FileNotFoundException {
		File l = new File(log);
		logWriter = new PrintWriter(l);
		logWriter.println(s);
		logWriter.flush();
		logWriter.close();
	}

	
	
}
