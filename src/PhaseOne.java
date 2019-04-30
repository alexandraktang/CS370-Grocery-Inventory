/*
 * CS 370
 * Phase One
 * @Author Alexandra Tang
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PhaseOne {
	
	/*
	 * initialization of variables needed in main function:
	 * creates a new HashGroceryInventory object for all methods to use,
	 * sets input loaded, output created, log created, and path given conditions to false,
	 * then creates blank file names and blank file paths
	 */
	static HashGroceryInventory mainHGI = new HashGroceryInventory();
	static int i = 0;
	static String arg = "";
	static boolean input = false, output = false, log = false, p = false;
	static String inputfile = "", outputfile = "", logfile = ""; //filenames
	static String path = "", outputpath = "", logpath = ""; //paths to files

	public static void main(String[] args) throws IOException {
		
     /* while (i < args.length) {
        		arg = args[i++];
        		
        		if (arg.equalsIgnoreCase("-i")) {
        			if (i < args.length) {
        				inputfile = path + args[i++];
        				input = true;
        				inputTrue();
        			}
        			else {
        				inputfile = FileMenuHandler.openChosenFile();
        				input = true;
        				inputTrue();
        			}	
        		}
            
        		if (arg.equalsIgnoreCase("-o")) {
            		if (i < args.length) {
            			outputfile = path + args[i++];
            			output = true;
            			outputTrue();
                	}
            		else {
            			outputpath = FileMenuHandler.createOutputFile();
            			outputfile = path + outputpath;
            			output = true;
            			outputTrue();
            		}
            	}

        		if (arg.equalsIgnoreCase("-l")) {
            		if (i < args.length) {
            			logfile = path + args[i++];
            			log = true;
            			logTrue();
                	}
            		else {
            			logpath = EditMenuHandler.createLogFile();
            			logfile = path + logpath;
            			log = true;
            			logTrue();
            		}
            	}
            
        		if (arg.equalsIgnoreCase("-d")) {
        			if (i < args.length) {
        				path = args[i++];
        				p = true;
        				pTrue();
        			}
        			else {
        				System.out.println("Missing directory.");
        			}
            }
        		
        		// opens up a GUI implementation of this code
        		if (arg.equalsIgnoreCase("-g")) {
        			new MainViewGUI();
        		}
        		
        }//while */
		
		
        new MainViewGUI();
	}

	/* 
	 * adds a slash to the path (ex. if given: /Users/alexandratang/Desktop,
	 * it returns, /Users/alexandratang/Desktop/)
	 */
	private static String addSlash(String path) {
		path = path + "/";
		return path;
	}
	
	/*
	 * if the input condition is true, this function will tokenize the input file given
	 * by the user and populate a TreeMap accordingly, then print it for the user
	 */
	private static void inputTrue() throws IOException {
        if (input == true) {
			FileReader fr = new FileReader(inputfile);
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
			mainHGI.writeToConsole();
			System.out.println("\nFile load complete.");
		}
    }
	
	/*
	 * if the output condition is true, it checks to make sure an input file was given
	 * first. if there was an input given, the method goes through the HashGroceryInventory
	 * (TreeMap) and prints it into an output file at the given path
	 */
	private static void outputTrue() throws FileNotFoundException {
	    if (output == true) {
	    		if (input != true)
	    			System.out.println("Please input file first.");
	    		else {
	    			File of = new File(outputfile);
	    			PrintWriter outWriter = new PrintWriter(of);
	    			outWriter.println("Inventory Number | Item Description | Quantity | Price " + 
	    					"| Date Purchased | Timestamp\n");
	    			mainHGI.write(outWriter);
	    			outWriter.flush();
	    			outWriter.close();
	    			System.out.println("Output file created.");
	    		}
	    }
	}

	/*
	 * if the log condition is true, it checks to make sure an input file was given
	 * first. if there was an input given, the method finds a string of log messages saved
	 * by the EditMenuHandler and prints it
	 */
	private static void logTrue() throws FileNotFoundException {
		if (input != true)
			System.out.println("Please input file first.");
		else {
			String logstring = EditMenuHandler.getLogString().toString();
			EditMenuHandler.printLogFile(logfile, logstring);
		}
	}
	
	/*
	 * if the p(ath) condition is true, returns a path with an added slash
	 */
	private static String pTrue() {
		path = addSlash(path);
		return path;
	}
}
