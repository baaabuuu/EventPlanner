package taskManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import workerLogic.*;

public class Task {
	String name;
	boolean finished = false;
	String description;
	List<Worker> assignedWorkers = new ArrayList<Worker>();
	Calendar deadline;
	
	
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
}
