package application;
/**
 * Used to signify that a task is missing.
 * @author s164166
 */
public class WorkerMissingTask extends Exception {
	private static final long serialVersionUID = 1L;

	public WorkerMissingTask(String message) {		
        super(message);
    }
}
