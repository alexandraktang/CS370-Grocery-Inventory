// a class built to keep track of log messages via a String

public class LogFile {
	static String log = "";
	
	public LogFile() {
		
	}
	
	/*
	 * a method that keeps track of log messages by adding
	 * them to a String and returning that String
	 */
	public String append(String s) {
		log = log + s + "\n";
		return log;
	}
	public String toString() {
		return log;
	}
}
