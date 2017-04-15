package taskManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import workerLogic.*;


public class Project {
	private List<Worker> workers = new ArrayList<Worker>();
	private List<Task> tasks = new ArrayList<Task>();
	private String name;
	private Calendar deadline;
	private Worker leader;
	private int projectID;
	
	public void setID(int id)
	{
		projectID=id;
	}
	
	public int getID()
	{
		return projectID;
	}
	
	
	public Project(List<Worker> workers, String name, int[] deadline)
	{
		this.name = name;
		this.deadline = new GregorianCalendar(deadline[0],deadline[1],deadline[2]);
		if(workers != null)
			this.workers = workers;
	}
	public void addWorker(Worker worker)
	{
		workers.add(worker);
	}
	public List<Worker> getWorkerbyID(int searchTerm)
	{
		List<Worker> foundWorkers = new ArrayList<Worker>();
		for(Worker worker : workers)
		{
			if(worker.getWorkID() == searchTerm)
				foundWorkers.add(worker);
		}
		return foundWorkers;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public List<Task> getTasks()
	{
		return tasks;
	}
	public List<Task> getTaskbyName(String searchTerm)
	{
		List<Task> foundTasks = new ArrayList<Task>();
		for(Task task : tasks)
		{
			if(task.getName().equals(searchTerm))
				foundTasks.add(task);
		}
		return foundTasks;
	}
	/**
	 * Returns a task from the tasks list by its index.
	 * @author s160902
	 */
	public Task getTaskbyIndex(int index)
	{
		return this.tasks.get(index);
	}
	public void addTask(Task task) {
		tasks.add(task);
		
	}
	public void setDeadline(int[] newDeadline)
	{
		deadline = new GregorianCalendar(newDeadline[0],newDeadline[1],newDeadline[2]);
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
		return this.leader;
	}
	/**
	 * Updates the project leader.
	 * @author s160902
	 */
	public void setLeader(Worker newLeader)
	{
		this.leader = newLeader;
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
