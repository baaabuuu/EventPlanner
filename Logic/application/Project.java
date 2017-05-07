package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;


//S164147
public class Project {
	private List<Task> tasks = new ArrayList<Task>();
	private String name;
	private Calendar deadline = new GregorianCalendar();
	private Worker leader;
	private int projectID;
	private int taskCount;
	private Settings settings;
	
	public Project(String name, Date date, Worker leader, Settings settings) throws ProjectInvalidInput {
		this.settings	=	settings;
		setName(name);
		setDeadline(date);
		setLeader(leader);
	}
	
	public Settings getSettings()
	{
		return settings;
	}
	
	public void setID(int id)
	{
		projectID=id;
	}
	
	public int getID()
	{
		return projectID;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String newName) throws ProjectInvalidInput
	{
		if (newName.equals(""))
			throw new ProjectInvalidInput("Wrong name Input");
		name = newName;
	}
	public List<Task> getTasks()
	{
		return tasks;
	}
	
	/**
	 * Returns a task from the tasks list by its index or name.
	 * @author s160902 && s164166
	 */
	public Task getTask(int index)
	{
		if (index<tasks.size() && index>=0)
			return this.tasks.get(index);
		return null;
	}
	/**
	 * Returns a task from the tasks list by its index or name.
	 * @author s160902 && s164166
	 */
	public Task getTask(String name)
	{
		for(Task task : tasks)
			if(task.getName().equals(name))
				return task;
		return null;
	}
	
	/**
	 * Removes a task from the list, can either use an index or a task object.
	 * @param index
	 * @author s164166
	 */
	public void removeTask(int index) throws ProjectInvalidInput
	{
		if (index < 0 || index >= getTasks().size())
			throw new ProjectInvalidInput("Cannot remove task, invalid ID");
		tasks.remove(index);
	}
	/**
	 * Removes a task from the list, can either use an index or a task object.
	 * @param task
	 * @author s164166 
	 */
	public void removeTask(Task task)
	{
		tasks.remove(task);
	}	
	
	
	public void addTask(Task task) {
		tasks.add(task);
		task.setID(taskCount);
		taskCount++;
	}
	


	/**
	 * Sets the deadline, can thrown ProjectInvalidInput
	 * @author s160902
	 * @throws ProjectInvalidInput 
	 */
	public void setDeadline(Date date) throws ProjectInvalidInput
	{
		if (date.before(settings.getCurrDate().getTime()))
			throw new ProjectInvalidInput("Invalid date format");
		deadline.setTime(date);
	}

	public Calendar getDeadline()
	{
		return deadline;
	}
	/**
	 * Returns the project leader.
	 * @author s160902
	 */
	public Worker getLeader()
	{
		return leader;
	}
	/**
	 * Updates the project leader.
	 * @author s160902 && s164166
	 */
	public void setLeader(Worker newLeader)
	{
		leader = newLeader;
	}
	/**
	 * Returns total workTime of all tasks in this project.
	 * @author s160902
	 */
	public int getWorkTime() throws WorkerMissingTask
	{
		int totalWorkTime = 0;
		for(int i = 0; i < getTasks().size(); i++)
			totalWorkTime += getTasks().get(i).getWorkTime();
		return totalWorkTime;
	}
	/**
	 * Returns String showing how many tasks is completed out of how many there are.
	 * @author s160902
	 */
	public String getStatus()
	{
		int totalCompletion = 0;
		for(int i = 0; i < getTasks().size(); i++){
			if(getTasks().get(i).getStatus())
				totalCompletion++;
		}
		return totalCompletion + "/" + getTasks().size();
	}
}
