package workerLogic;
/**
 * Used to signify that the name had an error for a specific worker.
 * @author s164166
 */
public class WorkerNameError extends Exception {
	private static final long serialVersionUID = 1L;

	public WorkerNameError(String message) {		
        super(message);
    }
}
