package application;
/**
 * Used to signify that an invalid date range was picked.
 * @author s164166
 */
public class InvalidDateRange extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDateRange(String message) {		
        super(message);
    }
}
