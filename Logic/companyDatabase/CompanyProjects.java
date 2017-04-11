package companyDatabase;

import java.util.ArrayList;
import java.util.List;
import taskManagement.Project;

/* This class contains info regarding what projects the company is working on.
* @author s160902
*/
public class CompanyProjects {
	private static ArrayList<Project> companyProjects = new ArrayList<Project>();
	/**
	 * Adds a new project
	 * @author s160902
	 */
	public static void addNewProject(String name)
	{
		Project newProject = new Project(null, name, null);
		newProject.setID(companyProjects.size());
		companyProjects.add(newProject);
	}
	/**
	 * removes a project from the company database using a specific object.
	 * @author s160902
	 * @param project
	 */
	public static void removeProject(Project project)
	{
		companyProjects.remove(project);
	}
	/**
	 * removes a worker from the company database using its index.
	 * @author s160902
	 * @param index.
	 */
	public static void removeProject(int index)
	{
		companyProjects.remove(index);
	}
	
	/**
	 * gets a specific project by their index number.
	 * @author s160902
	 * @param index.
	 */
	public static Project getProject(int index)
	{
		return companyProjects.get(index);
	}
	/**
	 * Returns a list of projects.
	 * @author s160902
	 * @return All projects.
	 */
	public static List<Project> getAllProjects()
	{
		return companyProjects;
	}
}
