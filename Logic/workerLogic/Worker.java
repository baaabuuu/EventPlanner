package workerLogic;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Worker 
{
	private String name, workName;
	private int workID;
	private ArrayList<WorkWeek> WorkWeek = new ArrayList<WorkWeek>();
	private Tasks[] assignments	= new Tasks[20];
	
	public Worker(String NAME) throws WorkerNameError
	{
		setName(NAME);

		setWorkName(NAME);
	}

	/**
	 * returns full name of the worker
	 * @author s164166
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * sets the name of the worker, can throw an error in case of blank name.
	 * @author s164166
	 * @return
	 */
	private void setName(String name) throws WorkerNameError
	{
		if (name.isEmpty())
			throw new WorkerNameError("Name was empty.");
		this.name = name;
	}
	/**
	 * returns the work name of the worker
	 * @author s164166
	 * @return
	 */
	public String getWorkName() 
	{
		return workName;
	}
	
	/**
	 * Author s164166
	 * @param workName - the name for the user.
	 * Since we in our creation take a full "worker name" we only want the first 4 letters as the name.
	 */
	private void setWorkName(String workName)
	{
		this.workName = String.format("%4s", workName).substring(0, 4);
	}

	/**
	 * 	 * returns the workID

	 * @author s164166
	 * @param workID
	 */
	public int getWorkID()
	{
		return workID;
	}
	/**
	 * Sets the work ID of the user to a specific value - used in CompanyDatabase.
	 * @param workID
	 * @author s164166
	 */
	public void setWorkID(int workID)
	{
		this.workID = workID;
	}

	/**
	 * Returns a list of workweeks that the user has.
	 * @author s164166
	 * @return ArrayList<WorkWeek>
	 */
	public ArrayList<WorkWeek> getWorkWeek()
	{
		return WorkWeek;
	}


	
}
