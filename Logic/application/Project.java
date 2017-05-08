package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Creates a Project, a project consists of smaller tasks and can have a worker assigned.
 * Please type in valid input only. Thanks.
 * @author  s164166 & s164147 & s160902
 */
public class Project {
	private List<Task> tasks = new ArrayList<Task>();
	private String name;
	private Calendar deadline = new GregorianCalendar();
	private Worker leader;
	private int projectID;
	private int taskCount;
	private Settings settings;
	private String description;
	
	/**
	 * Creates a project with the following parameters
	 * @param name
	 * @param description
	 * @param date
	 * @param leader
	 * @param settings
	 * @throws ProjectInvalidInput
	 * @author s164166 & s164147 & s160902
	 */
	public Project(String name, String description, Date date, Worker leader, Settings settings) throws ProjectInvalidInput {
		this.settings	=	settings;
		setDescription(description);
		setName(name);
		setDeadline(date);
		setLeader(leader);
	}
	/**
	 * Returns the description of a project.
	 * @return String
	 * @author s164166
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Sets the description of a task.
	 * @param description
	 * @author s164166
	 */
	public void setDescription(String description)
	{
		this.description	=	description;
	}
	
	/**
	 * Returns the settings object so that it may be passing along for date purposes.
	 * @return Settings
	 * @author s164166
	 */
	public Settings getSettings()
	{
		return settings;
	}
	
	/**
	 * Sets the id of a project.
	 * @author s164147
	 */
	public void setID(int id)
	{
		projectID=id;
	}
	
	/**
	 * Gets the id of a project.
	 * @return integer
	 * @author s164147
	 */
	public int getID()
	{
		return projectID;
	}
	
	/**
	 * Returns the name of the project.
	 * @return String
	 * @author s164166
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name of the project, in case of an empty name, ex. "" it will throw 
	 * ProjectInvalidInput Error
	 * @return String
	 * @author s164166
	 */
	public void setName(String newName) throws ProjectInvalidInput
	{
		if (newName.equals(""))
			throw new ProjectInvalidInput("Wrong name Input");
		name = newName;
	}
	/**
	 * Returns a task from the tasks list by its index or name.
	 * If the index is wrong, it will return null.
	 * @author s164147
	 */
	public List<Task> getTasks()
	{
		return tasks;
	}
	
	/**
	 * Returns a task from the tasks list by its index or name.
	 * If the index is wrong, it will return null.
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
	 * @author s160902 & s164166
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
	 * @author s160902 & s164166
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
