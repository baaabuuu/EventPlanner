package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Task {
	private String name;
	private boolean finished = false;
	private String description;
	private List<Worker> assignedWorkers = new ArrayList<Worker>();
	private List<Worker> assistingWorkers = new ArrayList<Worker>();
	private	int		workedTime	=	0;
	private int		lastWorkedTime;
	private Calendar deadline	=	new GregorianCalendar();
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

	/**
	 * Creates a new task with the following input.
	 * @param name
	 * @param description
	 * @param assignedWorkers
	 * @param assistingWorkers
	 * @param date
	 * @param project
	 * @throws TaskInvalidInput
	 * @throws WorkerMissingTask
	 * @author s160902 & s164166 & s164147
	 */
	public Task(String name, String description, List<Worker> assignedWorkers,
            List<Worker> assistingWorkers, Date date, Project project) throws TaskInvalidInput, WorkerMissingTask
    {
        this.project    =    project;
        project.addTask(this);
        setName(name);
        this.description = description;
        setDeadline(date);
        setAssignedWorkers(assignedWorkers);
        this.assistingWorkers	=	assistingWorkers;
    }

	/**
	 * Returns the name of a task
	 * @return String
	 * @author s160902
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Sets the name of a task, can throw an invalid input in case name is empty.
	 * @throws TaskInvalidInput
	 * @return String
	 * @author s164166
	 */
	public void setName(String newName) throws TaskInvalidInput
	{
		if (newName.equals(""))
			throw new TaskInvalidInput("Invalid name");
		name = newName;
	}
	
	/**
	 * Returns the status of a user
	 * @return boolean
	 * @author s160902
	 */
	public boolean getStatus()
	{
		return finished;
	}
	
	/**
	 * Changes the completion status
	 * @return
	 */
	public void changeCompletion()
	{
		finished = !finished;
	}
	
	/**
	 * Sets the deadline of a project, if a task is set to before first initial boot of program
	 * or after a project is supposed to be completed, it will return an invalid input.
	 * @param date
	 * @throws TaskInvalidInput
	 * @author s164166
	 */
	public void setDeadline(Date date) throws TaskInvalidInput
	{
		if (date.after(getProject().getDeadline().getTime()) ||
				date.before(getProject().getSettings().getCurrDate().getTime()))
					throw new TaskInvalidInput("Invalid date range.");
		deadline.setTime(date);
	}
	
	/**
	 * Returns the deadline for a project.
	 * @return
	 * @author s164166
	 */
	public Calendar getDeadline()
	{
		return deadline;
	}
	
	
	/**
	 * Gets the description of the project
	 * @return String
	 * @author s164147
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Sets the description of the project
	 * @author s164147
	 */
	public void setDescription(String newDesc)
	{
		description = newDesc;
	}
	
	/**
	 *  Adds a worker to the assigned list, if a worker has already been added
	 *  it will not add the worker again.
	 *  Also updates the current week for the worker to reflect that it has been added.
	 * @author s164147
	 */
	public void addWorker(Worker worker)
	{
		if (!assignedWorkers.contains(worker))
		{
			assignedWorkers.add(worker);
			worker.getCurrWeek().updAssignments(this);
		}
	}
	/**
	 * Removes a worker form the list using the worker ID. 
	 * @param idToRemove
	 * @author s164147 && s160902 & s164166
	 */
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
	 * @throws WorkerMissingTask 
	 */
	public void setAssignedWorkers(List<Worker> assignedWorkers) throws WorkerMissingTask
	{
		boolean found;
		this.assignedWorkers = assignedWorkers;
		for(Worker worker : assignedWorkers)
		{
			found = false;
			for (int i = 0; i<worker.getCurrWeek().getCurrTaskAmm();i++)
				if (worker.getCurrWeek().getWorkTask(i) != null)
					if (worker.getCurrWeek().getWorkTask(i).equals(this))
						found = true;
			if (!found)
				worker.getCurrWeek().updAssignments(this);
		}
	}
	

	/**
	 * Returns a list of all workers assigned to the task.
	 * @author s160902
	 */
	public List<Worker> getAssistingWorkers(){
		return this.assistingWorkers;
	}
	
	/**
	 * Returns total workTime of all who have worked on this task week.
	 * @author s160902
	 */
	public int getWorkTime() throws WorkerMissingTask
    {
        ArrayList<Worker> illegals = new ArrayList<Worker>();
        int totalWorkTime = 0;
        if (getAssignedWorkers().size()>0)
            for(int i = 0; i < getAssignedWorkers().size(); i++)
                totalWorkTime += getAssignedWorkers().get(i).timeSpentOnTaskThisWeek(this);
        if (getAssistingWorkers().size()>0)
            for(int i = 0; i < getAssistingWorkers().size(); i++)
                if (!getAssignedWorkers().contains(getAssistingWorkers().get(i))
                        && !illegals.contains(getAssistingWorkers().get(i)))
                {
                    illegals.add(getAssistingWorkers().get(i));
                    totalWorkTime += getAssistingWorkers().get(i).timeSpentOnTaskThisWeek(this);

                }
        lastWorkedTime = totalWorkTime;
        return totalWorkTime + workedTime;
    }
	

	/**
	 * Adds an assisting worker to task.
	 * @param worker
	 * @author s164166
	 */
	public void addAssistingWorker(Worker worker)
	{
		assistingWorkers.add(worker);
	}	
	
	/**
	 * Should only be called once a week, Removes the workers and saves the time in the workedTime variable.
	 * @author s164166
	 * @throws WorkerMissingTask
	 */
	public void saveLastWeeksTime() throws WorkerMissingTask
	{
		System.out.println("update");
		workedTime	= lastWorkedTime;
		assignedWorkers.clear();
		assistingWorkers.clear();
	}
}