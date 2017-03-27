package workerLogic;
/**
 * 
 * @author s164166
 * Used to signify that the name had an error for a specific worker.
 */
public class WorkerNameError extends Exception {
	private static final long serialVersionUID = 1L;

	public WorkerNameError(String message) {
        super(message);
    }
}
