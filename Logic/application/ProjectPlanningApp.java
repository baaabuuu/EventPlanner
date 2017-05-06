package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import taskManagement.Project;
import workerLogic.Worker;
import workerLogic.WorkerNameError;

public class ProjectPlanningApp {
	
	
	private Settings 			settings		= new Settings();
	private ArrayList<Worker>	companyWorkers	= new ArrayList<Worker>();
	private ArrayList<Project>	companyProjects = new ArrayList<Project>();
	
	/**
	 * Adds a new project
	 * @author s160902
	 */
	public  void addNewProject(String name, Date date, Worker leader)
	{
		Project project = new Project(name, date, leader);
		project.setID(companyProjects.size());
		companyProjects.add(project);
	}
	/**
	 * removes a project from the company database using a specific object.
	 * THIS SHOULD NEVER BE CALLED ON A PROJECT THAT HAS BEEN WORKED
	 * It would result in a project being deleted making it hard for a user to find something they've worked previously on.
	 * @author s160902
	 * @param project
	 */
	public  void removeProject(Project project)
	{
		companyProjects.remove(project);
	}
	/**
	 * removes a worker from the company database using its index.
	 * @author s160902
	 * @param index.
	 */
	public  void removeProject(int index)
	{
		companyProjects.remove(index);
	}
	/**
	 * gets a specific project by their index number.
	 * @author s160902
	 * @param index.
	 */
	public  Project getProject(int index)
	{
		return companyProjects.get(index);
	}
	/**
	 * Returns a list of projects.
	 * @author s160902
	 * @return All projects.
	 */
	public  List<Project> getAllProjects()
	{
		return companyProjects;
	}
	/**
	 * Adds a worker to the company database.
	 * @author s164166
	 * @param worker
	 */
	public void addNewWorker(String name) throws WorkerNameError
	{
		Worker worker = new Worker(name, settings);
		worker.setWorkID(companyWorkers.size());
		companyWorkers.add(worker);
	}
	/**
	 * removes a worker from the company database using a specific object.
	 * @author s164166
	 * @param worker
	 */
	public void removeWorker(Worker worker)
	{
		companyWorkers.remove(worker);
	}
	/**
	 * removes a worker from the company database using the ID.
	 * @author s164166
	 * @param index, the index.
	 */
	public void removeWorker(int index)
	{
		companyWorkers.remove(index);
	}
	/**
	 * gets a specific worker by their index number.
	 * @author s164166
	 * @param index, the index.
	 */
	public Worker getWorker(int index)
	{
		return companyWorkers.get(index);
	}
	/**
	 * Returns a list of workers that can work.
	 * @author s164166
	 * @return the workers than can work on a task at the moment
	 */
	public ArrayList<Worker> getEligibleWorkers()
	{	
		return (ArrayList<Worker>) companyWorkers.stream().filter(Worker -> Worker.isAvailableCurrWeek()).collect(Collectors.toList());		
	}
	/**
	 * Returns a list of workers that are not fired.
	 * "You're fired." - Donald J. Trump, President of the United States of America.
	 * @author s164166
	 * @return the workers than can work on a task at the moment
	 */
	public List<Worker> getAllWorkers()
	{
		return (ArrayList<Worker>) companyWorkers.stream().filter(Worker -> !Worker.isFired()).collect(Collectors.toList());		
	}
}
