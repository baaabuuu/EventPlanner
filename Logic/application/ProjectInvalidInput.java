package application;
/**
 * Used to signify that a task input is invalid.
 * @author s164166
 */
public class ProjectInvalidInput extends Exception {
	private static final long serialVersionUID = 1L;

	public ProjectInvalidInput(String message) {		
        super(message);
    }
}
