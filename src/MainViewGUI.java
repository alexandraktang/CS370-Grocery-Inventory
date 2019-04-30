import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class MainViewGUI extends JFrame {
	
	public static JFrame mainFrame = new JFrame();
	public static JTextArea mainTextArea = new JTextArea();
	public static JMenuBar mainMenuBar = new JMenuBar();
	
	/*
	 * a default constructor that sets the GUI's title to
	 * "Grocery Inventory", sets the size and location,
	 * builds a menu bar and creates File and Edit menus on
	 * said menu bar
	 */
	public MainViewGUI() {
		mainFrame.setTitle("Grocery Inventory");
		mainFrame.setSize(600, 450);
		mainFrame.setLocation(311, 188);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mainFrame.setJMenuBar(mainMenuBar);
		fileMenu();
		editMenu();
		
		mainFrame.setVisible(true);
	}
	
	/*
	 * a method that builds the file menu and inserts different
	 * buttons and assigns ActionListeners to them, which allows
	 * FileMenuHandler to determine a user's actions
	 */
	private void fileMenu() {
		JMenu fMenu = new JMenu("File");
		FileMenuHandler fileMH = new FileMenuHandler(this);
		
		JMenuItem open = new JMenuItem("Open File...");
		open.addActionListener(fileMH);
		fMenu.add(open);
		fMenu.addSeparator();
		
		JMenuItem view = new JMenuItem("Save File");
		view.addActionListener(fileMH);
		fMenu.add(view);
		fMenu.addSeparator();
		
		JMenuItem log = new JMenuItem("View Log");
		log.addActionListener(fileMH);
		fMenu.add(log);
		
		mainMenuBar.add(fMenu);
	}
	
	/*
	 * a method that builds the edit menu and inserts different
	 * buttons and assigns ActionListeners to them, which allows
	 * EditMenuHandler to determine a user's actions
	 */
	private void editMenu() {
		JMenu eMenu = new JMenu("Edit");
		EditMenuHandler editMH = new EditMenuHandler(this);
		
		JMenuItem insert = new JMenuItem("Add Product");
		insert.addActionListener(editMH);
		eMenu.add(insert);
		eMenu.addSeparator();
		
		JMenuItem edit = new JMenuItem("Edit Product");
		edit.addActionListener(editMH);
		eMenu.add(edit);
		eMenu.addSeparator();
		
		JMenuItem delete = new JMenuItem("Remove Product");
		delete.addActionListener(editMH);
		eMenu.add(delete);
		
		mainMenuBar.add(eMenu);
	}
	
}
