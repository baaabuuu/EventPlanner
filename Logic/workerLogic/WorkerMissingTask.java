package workerLogic;
/**
 * Used to signify that something went wrong with a task.
 * @author s164166
 */
public class WorkerMissingTask extends Exception {
	private static final long serialVersionUID = 1L;

	public WorkerMissingTask(String message) {		
        super(message);
    }
}
