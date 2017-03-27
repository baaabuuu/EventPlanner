package workerLogic;

import java.util.ArrayList;
/**
 * Used to contain data for a WorkWeek for a user.
 * @author s164166
 */
public class WorkWeek
{
	private int[]				workTime		=	new int[20];
	private Tasks[]				assignments		=	new Tasks[20];
	private ArrayList<String>	helpedTasksName	=	new ArrayList<String>();
	private ArrayList<Integer>	helpedTasksTime	=	new ArrayList<Integer>();
	
	public int getWorkTime(int i)
	{
		return workTime[i];
	}
	
	
	/**
	 * Adds a task to the helped list with an amount of time, if the user works on the same tasks multiple times a week, each will be logged as something different.
	 * @param time - in half hours
	 * @param name - name of the task
	 * @author s164166
	 */
	public void uppHelpedTasks(int time, String name)
	{
		helpedTasksName.add(name);
		helpedTasksTime.add(time);
	}
	
	/**
	 * Gets the helpedTasksTime
	 * @author s164166
	 * @return
	 */
	public ArrayList<Integer> getHelpedTasksTime()
	{
		return helpedTasksTime;
	}
	
	/**
	 * Gets the helpedTasksNames
	 * @author s164166
	 * @return
	 */
	public ArrayList<String> getHelpedTasksName()
	{
		return helpedTasksName;
	}
	
	/**
	 * Updates the time for a task, index is the index number of the assignment, 
	 * time is the amount of time added. In case a task is missing it will throw a WorkerMissingTask error.
	 * @author s164166
	 * @param index
	 * @param time
	 * @throws WorkerMissingTask
	 */
	public void updWorkTime(int index, int time) throws WorkerMissingTask
	{
		if (assignments[index] == null)
			throw new WorkerMissingTask("Could not update task time");
		workTime[index]	+=	time;
	}
	
	/**
	 * Adds a task to the worker.
	 * @author s164166
	 * @param newTask
	 * @return true/false WorkerMissingTask
	 */
	public boolean updAssignments(Tasks newTask)
	{
		for (Tasks task : assignments)
			if (task == null)
			{
				task = newTask;
				return true;
			}
		return false;
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
	 * Gets the current amount of tasks
	 * @author s164166
	 * @return the current amount of tasks.
	 */
	public int getCurrTaskAmm()
	{
		int count = 0;
		for (Tasks task : getAssignments())
			if (task != null)
				count++;
		return count;	
	}
	/**
	 * @author s164166
	 * @return whether or whether not the user can take on more tasks currently.
	 */
	public boolean isLegalThisweek()
	{
		return getCurrTaskAmm()<Settings.settings.maxAssignments;
	}
}
