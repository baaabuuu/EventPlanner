package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains a list of all companyWorkers, the settings, the project and how many projects have been created
 * @author s164166 & s160902 & s164147
 */
public class ProjectPlanningApp {
	
	private Settings 			settings		=	new Settings();
	private ArrayList<Worker>	companyWorkers	=	new ArrayList<Worker>();
	private ArrayList<Project>	companyProjects =	new ArrayList<Project>();
	private int					projectCounter	=	0;
	private	WorkOutput			outputter		=	new WorkOutput();
	
	/**
	 * Advances the week by a day, in case new week is entered, the project removes all workers from all tasks.
	 * @author s164166 & s160902
	 * @throws WorkerMissingTask 
	 */
	public void advanceDay() throws WorkerMissingTask
	{
		settings.updateDay();
		if (settings.getDay() == 1)
		{
			for (Project project : companyProjects)
				for (Task task : project.getTasks())
					task.saveLastWeeksTime();
			for (Worker worker : companyWorkers)
				if (!worker.isFired())
					worker.fillMissingWorkWeeks(settings.getWeekNumber());
		}
	}
	
	/**
	 * Adds a new project
	 * @author s160902 & s164166
	 * @throws ProjectInvalidInput 
	 */
	public  void addNewProject(String name, String description, Date date, Worker leader) throws ProjectInvalidInput
	{
		Project project = new Project(name, description, date, leader, settings);
		project.setID(Integer.valueOf(settings.getCurrDate().get(Calendar.YEAR) + "" + projectCounter));
		projectCounter++;
		companyProjects.add(project);
	}
	
	/**
	 * removes a project from the company database using a specific project.
	 * THIS SHOULD NEVER BE CALLED ON A PROJECT THAT HAS BEEN WORKED ON.
	 * It would result in a project being deleted making it hard for a user to find something they've worked previously on.
	 * @author s160902 & s164147
	 * @param project
	 */
	public  void removeProject(Project project)
	{
		companyProjects.remove(project);
	}
	
	/**
	 * removes a project from the company database using its index in the list.
	 * THIS SHOULD NEVER BE CALLED ON A PROJECT THAT HAS BEEN WORKED ON.
	 * It would result in a project being deleted making it hard for a user to find something they've worked previously on.
	 * @author s160902 & s164166
	 * @param index.
	 */
	public  void removeProject(int index)
	{
		if (index >= 0 && index<companyProjects.size())
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
	 * @param name
	 */
	public void addNewWorker(String name) throws WorkerNameError
	{
		Worker worker = new Worker(name, settings);
		worker.setWorkID(companyWorkers.size());
		companyWorkers.add(worker);
	}
	
	/**
	 * Adds a worker to the company database.
	 * @author s160902
	 * @param worker
	 */
	public void addNewWorker(Worker worker) throws WorkerNameError
	{
		worker.setWorkID(companyWorkers.size());
		companyWorkers.add(worker);
	}
	
	/**
	 * removes a worker from the company database using a specific object.
	 * @author s160902
	 * @param worker
	 */
	public void removeWorker(Worker worker)
	{
		companyWorkers.remove(worker);
	}
	/**
	 * removes a worker from the company database using the ID.
	 * @author s160902
	 * @param index, the index.
	 */
	public void removeWorker(int index)
	{
		if (index >= 0 && index < companyWorkers.size())
			companyWorkers.remove(index);
	}
	/**
	 * gets a specific worker by their index number.
	 * @author s160902
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
	 * @author s164166
	 * @return the workers than can work on a task at the moment
	 */
	public List<Worker> getAllWorkers()
	{
		return (ArrayList<Worker>) companyWorkers.stream().filter(Worker -> !Worker.isFired()).collect(Collectors.toList());		
	}
	
	/**
	 * Returns a settings object.
	 * @return settings.
	 * @author s164166
	 */
	public Settings getSettings()
	{
		return settings;
	}
	
	/**
	 * Print WorkWeek Info
	 * @throws InvalidDateRange 
	 * @throws IOException 
	 * @throws WorkerMissingTask
	 * @author s164166
	 */
	public void printWorkWeeks(Worker worker, int from, int till) throws WorkerMissingTask, IOException, InvalidDateRange
	{
		outputter.generateText(worker, from, till, settings);
	}
}
