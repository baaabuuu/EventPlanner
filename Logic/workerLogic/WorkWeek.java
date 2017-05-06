package workerLogic;

import java.util.ArrayList;

import application.Settings;
import taskManagement.Task;
/**
 * Used to contain data for a WorkWeek for a user.
 * @author s164166
 */
public class WorkWeek
{
	private Settings			settings;
	private int[]				workTime;
	private Task[]				assignments;
	private int[][]				timeWorkedOnTask;
	private ArrayList<Task>		helpedTasks			=	new ArrayList<Task>();
	private ArrayList<Integer>	helpedTasksTime		=	new ArrayList<Integer>();
	private boolean				isBussy				=	false;

		
	/**
	 * Creates a workweek object
	 * @author s164166
	 * @param settings 
	 */
	public WorkWeek(Settings settings)
	{
		this.settings	 =	settings;
		workTime		 =	new int[settings.getMaxAssignments()];
		assignments		 =	new Task[settings.getMaxAssignments()];
		timeWorkedOnTask = new int[8][settings.getMaxAssignments()];
	}
	
	/**
	 * Denotes whether the user is sick or perhaps having a vacation this week.
	 * Once this trigger, the user is unable to work for the remainder of this week.
	 * If calld again, it will return false and then do scenario 1 once the code is rerun.
	 * @author s160902
	 */
	public void setBussy()
	{
		isBussy = !isBussy;
	}
	/**
	 * Returns a specific task
	 * @param index
	 * @return task
	 * @throws WorkerMissingTask
	 * @author s164166
	 */
	public Task getWorkTask(int index) throws WorkerMissingTask
	{
		if (assignments[index] == null)
			throw new WorkerMissingTask("Could not get workTime.");
		return assignments[index];
	}
	/**
	 * Returns the work time for a single project.
	 * @param index
	 * @return amount of half hours worked on a single project.
	 * @throws WorkerMissingTask 
	 * @author s164166
	 */
	public int getWorkTime(int index) throws WorkerMissingTask
	{
		if (assignments[index] == null)
			throw new WorkerMissingTask("Could not get workTime.");
		return workTime[index];
	}
	/**
	 * Adds a task to the helped list with an amount of time, if the user works on the same tasks multiple times a week, each will be logged as something different.
	 * @param time - in half hours
	 * @param name - name of the task
	 * @author s164166
	 */
	public void uppHelpedTasks(int time, Task task)
	{
		helpedTasks.add(task);
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
	public ArrayList<Task> getHelpedTasks()
	{
		return helpedTasks;
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
		timeWorkedOnTask[settings.getDay()][index] += time;
		workTime[index]	+=	time;
	}
	/**
	 * Adds a task to the worker.
	 * @author s164166
	 * @param newTask
	 */
	public void updAssignments(Task newTask)
	{
		for (int i = 0; i<settings.getMaxAssignments();i++)
			if (assignments[i] == null)
			{
				assignments[i] = newTask;
				break;
			}
	}
	/**
	 * an array consisting of the current assignments, it has a max size of 20.
	 * @author s164166
	 * @return Tasks[]
	 */
	public Task[] getAssignments() 
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
		for (Task task : getAssignments())
			if (task != null)
				count++;
		return count;	
	}

	/**
	 * @author s164166
	 * @return whether or whether not the user can take on more tasks currently.
	 */
	public boolean isLegalThisweek(Worker worker)
	{		
		return !isBussy && !worker.isFired() && (getCurrTaskAmm() < settings.getMaxAssignments());
	}
}
