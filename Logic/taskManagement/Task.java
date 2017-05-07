package taskManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import workerLogic.*;

public class Task {
	private String name;
	private boolean finished = false;
	private String description;
	private List<Worker> assignedWorkers = new ArrayList<Worker>();
	private List<Worker> assistingWorkers = new ArrayList<Worker>();
	private Calendar deadline;
	private int taskID;
	private Project project;
	
	/**
	 * returns the ID number for this task.
	 * @author s164166
	 */
	public int getTaskID()
	{
		return taskID;
	}
	/**
	 * Returns the project
	 * @author s164166
	 */
	public Project getProject()
	{
		return project;
	}
	/**
	 * Sets the ID for this task.
	 * @author s164166
	 * @param id
	 */
	public void setID(int id)
	{
		taskID=id;
	}

	//s164147
	public Task(Project project){
		this.project	=	project;
	}
	public Task(String name, String description, List<Worker> assignedWorkers,
			List<Worker> assistingWorkers, Date date, Project project)
	{
		this.project	=	project;
		project.addTask(this);
		this.name = name;
		this.description = description;
		this.deadline = new GregorianCalendar();
		this.deadline.setTime(date);
		this.assignedWorkers = assignedWorkers;
		this.assistingWorkers = assistingWorkers;
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
	
	public void setDeadline(Date date)
	{
		deadline.setTime(date);;
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
		if (!assignedWorkers.contains(worker))
        {
            assignedWorkers.add(worker);
            worker.getCurrWeek().updAssignments(this);
        }
	}
	public void removeWorker(int idToRemove)
	{		
		assignedWorkers.removeIf(worker -> worker.getWorkID() == idToRemove);
	}
	/**
	 * Returns a list of all workers assigned to the task.
	 * @author s160902
	 */
	public List<Worker> getAssignedWorkers(){
		return assignedWorkers;
	}
	/**
	 * Sets the assignedWorkers list to a given list.
	 * @author s160902
	 */
	public void setAssignedWorkers(List<Worker> assignedWorkers){
		this.assignedWorkers = assignedWorkers;
	}
	/**
	 * Sets the assistingWorkers list to a given list.
	 * @author s160902
	 */
	public void removeAssistingWorker(int idToRemove)
	{		
		assistingWorkers.removeIf(worker -> worker.getWorkID() == idToRemove);
		
	}
	/**
	 * Returns a list of all workers assigned to the task.
	 * @author s160902
	 */
	public List<Worker> getAssistingWorkers(){
		return this.assistingWorkers;
	}
	/**
	 * Returns total workTime of all who have worked on this task.
	 * @author s160902
	 */
	public int getWorkTime() throws WorkerMissingTask
	{
		int totalWorkTime = 0;
		if(getAssignedWorkers() != null && getAssignedWorkers().size() > 0){
			for(int i = 0; i < getAssignedWorkers().size(); i++)
				totalWorkTime += getAssignedWorkers().get(i).timeSpentOnTask(this);
		}
		return totalWorkTime;
	}

	/**
	 * Sets the assistingWorkers list to a given list.
	 * @author s160902
	 */
	public void setAssistingWorkers(List<Worker> assistingWorkers){
		this.assistingWorkers = assistingWorkers;
	}
	public void addAssistingWorker(Worker worker)
	{
		assistingWorkers.add(worker);
	}
}
