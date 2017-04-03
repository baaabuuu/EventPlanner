package taskManagement;

import java.util.ArrayList;
import java.util.List;
import workerLogic.*;

public class Project {
	List<Worker> workers = new ArrayList<Worker>();
	List<Task> tasks = new ArrayList<Task>();
	String name;
	
	public Project(List<Worker> workers, String name)
	{
		this.name = name;
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
	public List<Task> getallTasks()
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

	public void addTask(Task task) {
		tasks.add(task);
		
	}
	
}
