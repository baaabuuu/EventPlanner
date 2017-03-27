package workerLogic;

import java.util.ArrayList;

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

	public String getName()
	{
		return name;
	}

	private void setName(String name)
	{
		this.name = name;
	}

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

	public int getWorkID()
	{
		return workID;
	}
	/**
	 * Sets the work ID of the user to a specific value - used in CompanyDatabase.
	 * @param workID
	 */
	public void setWorkID(int workID)
	{
		this.workID = workID;
	}

	/**
	 * @author s164166
	 * @return gets all the workweeks, usefull for scheduling in the future.
	 */
	public ArrayList<WorkWeek> getWorkWeek()
	{
		return WorkWeek;
	}

	/**
	 * @author s164166
	 * @return an array consisting of the current assignments.
	 */
	public Tasks[] getAssignments() 
	{
		return assignments;
	}
	/**
	 * @author s164166
	 * @return the current amount of tasks.
	 */
	public int getCurrTaskAmm()
	{
		return getAssignments().length;
	}
	/**
	 * @author s164166
	 * @return whether or whether not the user can take on more tasks currently.
	 */
	public boolean isLegalWorker()
	{
		return getCurrTaskAmm()<Settings.settings.maxAssignments;
	}
	
}
