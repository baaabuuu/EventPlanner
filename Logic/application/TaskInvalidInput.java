package application;
/**
 * Used to signify that a task input is invalid.
 * @author s164166
 */
public class TaskInvalidInput extends Exception {
	private static final long serialVersionUID = 1L;

	public TaskInvalidInput(String message) {		
        super(message);
    }
}
