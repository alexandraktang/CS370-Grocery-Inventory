import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileMenuHandler extends Observable implements ActionListener {
	static JFrame fileFrame;
	static PrintWriter outWriter;
	static HashGroceryInventory hgi = PhaseOne.mainHGI;

	public FileMenuHandler(JFrame ff) {
		fileFrame = ff;
	}
	
	/*
	 * an actionPerformed method that determines what to do when a user clicks
	 * on certain menu buttons (ex. Open File..., Save File, View Log) under the File Menu 
	 */
	public void actionPerformed(ActionEvent e) {
		String menuItemName = e.getActionCommand();
		
		if (menuItemName.equals("Open File...")) {
			try {
				openFile(openChosenFile());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "File loaded.");
		}
		
		else if (menuItemName.equals("Save File")) {
			try {
				String o = createOutputFile();
				printOutputFile(hgi, o);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "File saved.");
		}
		
		/*
		 * this method first obtains the String of log messages written by the
		 * EditMenuHandler when a user modifies the inventory, then creates a
		 * log file and calls a method in EditMenuHandler to print it
		 */
		else if (menuItemName.equals("View Log")) {
			String log = EditMenuHandler.getLogString().toString();
			try {
				String logFileName = EditMenuHandler.createLogFile();
				EditMenuHandler.printLogFile(logFileName, log);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Log updates complete.");
		}
	}
	
	/*
	 * this method opens a JFileChooser and obtains the path of whichever file
	 * the user picks, then returns it
	 */
	public static String openChosenFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		String gotFile = file.getPath();
		return gotFile;
	}
	
	/*
	 * this method creates a FileReader and a BufferedReader to read in lines from
	 * the input file, then tokenizes the components of a Product to build Products
	 * and populate a HashGroceryInventory (TreeMap)
	 */
	public static void openFile(String gotFile) throws IOException {
		try {
			FileReader fr = new FileReader(gotFile);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			
			while (line != null) {
				StringTokenizer tokenized = new StringTokenizer(line,"_");
				int inventoryNumber = Integer.parseInt(tokenized.nextToken());
				String itemDescription = tokenized.nextToken();
				int quantity = Integer.parseInt(tokenized.nextToken());
				double price = Double.parseDouble(tokenized.nextToken());
				String datePurchased = tokenized.nextToken();
				String timestamp = tokenized.nextToken();
				
				Item newProduct = new Item(inventoryNumber, itemDescription,
						quantity, price, datePurchased, timestamp);
				HashGroceryInventory.put(inventoryNumber, newProduct);
				
				line = br.readLine();
			}// while line != null
			
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} // try block
	} // open file
	
	//this method asks the user for an output filename and returns it 
	public static String createOutputFile() {
		String output = JOptionPane.showInputDialog(null, "Please name your file (ex. out.txt):");
		return output;
	}
	
	/*
	 * this method takes a HashGroceryInventory and an output filename as arguments,
	 * then creates a file with said filename and writes the first line to show what the
	 * different components are, then prints every Product by calling the Product write
	 * method
	 */
	public static void printOutputFile(HashGroceryInventory h, String output) throws FileNotFoundException {
		File out = new File(output);
		outWriter = new PrintWriter(out);
		outWriter.println("Inventory Number | Item Description | Quantity | Price " + 
				"| Date Purchased | Timestamp\n");
		h.write(outWriter);
		outWriter.flush();
		outWriter.close();
	}
	
}
