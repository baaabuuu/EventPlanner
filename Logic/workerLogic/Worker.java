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

	/**
	 * an array consisting of the current assignments, it has a max size of 20.
	 * @author s164166
	 * @return Tasks[]
	 */
	public Tasks[] getAssignments() 
	{
		return assignments;
	}
	/**
	 * Gets
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
