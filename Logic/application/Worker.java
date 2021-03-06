package application;

import java.util.ArrayList;

/**
 * A worker holds info about the workweek and so on.
 * @author s164166
 *
 */
public class Worker 
{
	private String 				name, workName;
	private int 				workID;
	private ArrayList<WorkWeek> workWeek = new ArrayList<WorkWeek>();
	private boolean				fired	 = false;
	private Settings			settings;
	
	/**
	 * Constructs a worker object with a set name.
	 * @param settings 
	 * @param Worker Name
	 * @throws WorkerNameError
	 * @author s164166 & s164147 & s160902
	 */
	public Worker(String NAME, Settings settings) throws WorkerNameError
	{
		setName(NAME);
		this.settings	=	settings;
		for (int i = 0; i <= settings.getWeekNumber(); i++)
			workWeek.add(new WorkWeek(settings));
	}
	
	/**
	 * Fires a worker, their work hours can still be access in the database but they cannot be assigned to projects anymore.
	 * @author s160902
	 */
	public void fireWorker()
	{
		fired	=	true;
	}
	
	/**
	 * returns full name of the worker.
	 * @author s164166
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * sets the name of the worker, can throw an error in case of blank name.
	 * @author s164166 & s160902
	 * @return
	 */
	public void setName(String name) throws WorkerNameError
	{
		if (name.isEmpty())
			throw new WorkerNameError("Names cannot be empty strings.");
		this.name = name;
		setWorkName(name);
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
	 * Author s164166 & s160902
	 * @param workName - the name for the user.
	 * Since we in our creation take a full "worker name" we only want the first 4 letters as the name.
	 */
	private void setWorkName(String workName)
	{
		this.workName = (workName.length()<5) ? workName : String.format("%4s", workName).substring(0, 4) ;
	}
	
	/**
	 * returns the workID of the worker.
	 * @author s164166 & s164147
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
	 * @author s160902
	 * @return ArrayList<WorkWeek>
	 */
	public ArrayList<WorkWeek> getWorkWeeks()
	{
		return workWeek;
	}
	/**
	 * Returns the current workweek.
	 * @return
	 * @author s164166 & s164147
	 *
	 */
	public WorkWeek getCurrWeek()
	{
		fillMissingWorkWeeks(settings.getWeekNumber());
		return getWorkWeeks().get(settings.getWeekNumber());
	}
	/**
	 * Returns the x week from the current time point.
	 * @param index
	 * @return
	 * @author s164166 & s164147
	 */
	public WorkWeek getXweek(int index)
	{
		if (getWorkWeeks().size() <= settings.getWeekNumber() + index)
			fillMissingWorkWeeks(index);
		return getWorkWeeks().get(settings.getWeekNumber() + index);
	}
	
	
	/**
	 * Returns the x week from the START time point.
	 * @param index
	 * @return
	 * @author s164166 & s164147
	 */
	public WorkWeek getWeekFromStart(int index)
	{
		if (getWorkWeeks().size() <= index)
			fillMissingWorkWeeks(index);
		return getWorkWeeks().get(index);
	}
	
	
	/**
	 * Checks whether the worker is available this week.
	 * @author s164166 & s164147
	 * @return boolean
	 */
	public boolean	isAvailableCurrWeek()
	{
		return getCurrWeek().isLegalThisweek(this);
	}
	/**
	 * Checks whether the user is available x workweek, where x is index amount of weeks after the current week.
	 * @author s164166
	 * @param index
	 * @return  boolean
	 */
	public boolean isAvailableXweek(int index)
	{
		return getXweek(index).isLegalThisweek(this);
	}
	/**
	 * Fills the missing weeks until the index, this ensures that there is no holes.
	 * @param index
	 * @author s164166
	 */
	public void fillMissingWorkWeeks(int index)
	{
		for (int i = getWorkWeeks().size(); i <= index+1; i++)
			getWorkWeeks().add(new WorkWeek(settings));
	}
	/**
	 * Checks whether the worker is fired or not.
	 * @author s160902
	 */
	public boolean isFired()
	{
		return fired;
	}
	
	/**
	 *  This function takes a task and returns all half hours spent on this specific task this week.
	 * @author s164166 & s160902
	 * @param task
	 * @return the amount of hours worked on a project.
	 * @throws WorkerMissingTask 
	 */	
	public int timeSpentOnTaskThisWeek(Task task) throws WorkerMissingTask
	{
		int totalWorkTime = 0; 
		int taskID = task.getTaskID();
		Task[] tasks;
		ArrayList<Task> helpedTasks;
		
		tasks = getCurrWeek().getAssignments();
		for (int i = 0; i<tasks.length;i++)
		{
			if (tasks[i] == null)
				break;
			if (tasks[i].getTaskID() == taskID)
				totalWorkTime += getCurrWeek().getWorkTime(i);
		}
		helpedTasks = getCurrWeek().getHelpedTasks();
		for (int i = 0; i<helpedTasks.size();i++)
		{
			if (helpedTasks.get(i).getTaskID() == taskID)
				totalWorkTime += getCurrWeek().getHelpedTasksTime().get(i);
		}
		return totalWorkTime;
	}

	
	
	
	/**
	 *  This function takes a task and returns all half hours spent on this specific task.
	 * @author s164166 & s160902
	 * @param task
	 * @return the amount of hours worked on a project.
	 * @throws WorkerMissingTask 
	 */	
	public int timeSpentOnTask(Task task) throws WorkerMissingTask
	{
		int totalWorkTime = 0; 
		int taskID = task.getTaskID();
		Task[] tasks;
		ArrayList<Task> helpedTasks;
		for (int workWeekIndex = 0; workWeekIndex<getWorkWeeks().size();workWeekIndex++)
		{
			tasks = getWorkWeeks().get(workWeekIndex).getAssignments();
			for (int i = 0; i<tasks.length;i++)
			{
				if (tasks[i] == null)
					break;
				if (tasks[i].getTaskID() == taskID)
					totalWorkTime += getWorkWeeks().get(workWeekIndex).getWorkTime(i);
			}
			helpedTasks = getWorkWeeks().get(workWeekIndex).getHelpedTasks();
			for (int i = 0; i<helpedTasks.size();i++)
			{
				if (helpedTasks.get(i).getTaskID() == taskID)
					totalWorkTime += getWorkWeeks().get(workWeekIndex).getHelpedTasksTime().get(i);
			}
		}
		return totalWorkTime;
	}

}
