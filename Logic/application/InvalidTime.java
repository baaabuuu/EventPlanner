package application;
/**
 * Used to signify that an invalid date range was picked.
 * @author s160902
 */
public class InvalidTime extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidTime(String message) {		
        super(message);
    }
}
