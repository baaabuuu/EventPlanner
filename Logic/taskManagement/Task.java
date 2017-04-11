package taskManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import workerLogic.*;

public class Task {
	private String name;
	private boolean finished = false;
	private String description;
	private List<Worker> assignedWorkers = new ArrayList<Worker>();
	private Calendar deadline;
	
	
	Task(String name, String description, List<Worker> assignedWorkers, int[] deadline)
	{
		this.name = name;
		this.description = description;
		this.deadline = new GregorianCalendar(deadline[0],deadline[1],deadline[2]);
		if(assignedWorkers != null)
			this.assignedWorkers = assignedWorkers;
		
	}
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public boolean getStatus()
	{
		return finished;
	}
	public void changeCompletion()
	{
		finished = !finished;
	}
	public void setDeadline(int[] newDeadline)
	{
		deadline = new GregorianCalendar(newDeadline[0],newDeadline[1],newDeadline[2]);
	}
	public Calendar getDeadline()
	{
		return deadline;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String newDesc)
	{
		description = newDesc;
	}
	public void addWorker(Worker worker)
	{
		assignedWorkers.add(worker);
	}
	public void removeWorker(int idToRemove)
	{
		for(Worker worker : assignedWorkers)
		{
			if(worker.getWorkID() == idToRemove)
				assignedWorkers.remove(worker);
		}
	}
	/**
	 * Returns a list of all workers assigned to the task.
	 * @author s160902
	 */
	public List<Worker> getAllAssignedWorkers(){
		return this.assignedWorkers;
	}
}
