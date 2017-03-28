package workerLogic;

import java.util.ArrayList;

import Settings.settings;

public class Worker 
{
	private String name, workName;
	private int workID;
	private ArrayList<WorkWeek> workWeek = new ArrayList<WorkWeek>();
	
	/**
	 * Constructs a worker object with a set name.
	 * @param NAME
	 * @throws WorkerNameError
	 */
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
	 * returns the workID of the worker.
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
	public ArrayList<WorkWeek> getWorkWeeks()
	{
		return workWeek;
	}
	
	/**
	 * Returns the current workweek.
	 * @return
	 * @author s164166
	 *
	 */
	public WorkWeek getCurrWeek()
	{
		return getWorkWeeks().get(settings.getWeekNumber());
	}
	
	/**
	 * Returns the x week from the current time point.
	 * @param index
	 * @return
	 * @author s164166
	 */
	public WorkWeek getXweek(int index)
	{
		if (getWorkWeeks().size() < settings.getWeekNumber() +index)
			fillMissingWorkWeeks(index);
		return getWorkWeeks().get(settings.getWeekNumber() + index);
	}
	
	/**
	 * Checks whether the worker is available this week.
	 * @author s164166
	 * @return boolean
	 */
	public boolean	isAvailableCurrWeek()
	{
		return getCurrWeek().isLegalThisweek();
	}
	
	/**
	 * Checks whether the user is available x workweek, where x is index amount of weeks after the current week.
	 * @author s164166
	 * @param index
	 * @return  boolean
	 */
	public  boolean isAvailableXweek(int index)
	{
		
		return getXweek(index).isLegalThisweek();
	}
	
	/**
	 * Fills the missing weeks until the index, this ensures that there is no holes.
	 * @param index
	 * @author s164166
	 */
	private void fillMissingWorkWeeks(int index)
	{
		for (int i = getWorkWeeks().size(); i <= index; i++)
			getWorkWeeks().add(new WorkWeek());
	}
}
