package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import workerLogic.*;

import org.junit.Before;
import org.junit.Test;

import companyDatabase.CompanyWorkers;
import taskManagement.Project;
import taskManagement.Task;

public class projectTest {
	//S164147
	Project project;
	@Before

	public void setUp() throws WorkerNameError
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "testname", completionDate);
		CompanyWorkers.addWorker(new Worker("Jeff"));
		CompanyWorkers.addWorker(new Worker("Bridgett"));
		CompanyWorkers.addWorker(new Worker("Adam"));
	}
	//Create project with name and three workers
	@Test
	public void testCreateProject() throws WorkerNameError
	{
		int[] completionDate2 = {20171, 3, 27};
		ArrayList<Worker> workers = new ArrayList<Worker>();
		for (int i = 0; i<3;i++)
		{
			workers.add(new Worker("a"));
		}
		Project project2 = new Project(workers, "test project", completionDate2);
		assertEquals("Check the name of the project.","test project",project2.getName());
		assertEquals("Test that the dates are configured properly.",project2.getDeadline(),new GregorianCalendar(20171,3,27));
		assertEquals("Test that there are 3 workers.",project2.getWorkers(),workers);		
	}
	
	//Maybe remove
	@Test
	public void searchWorker()
	{
		project.addWorker(CompanyWorkers.getAllWorkers().get(0));
		project.addWorker(CompanyWorkers.getAllWorkers().get(1));
		project.addWorker(CompanyWorkers.getAllWorkers().get(2));
		
		List<Worker> workerSearch = project.getWorkerbyID(-1);
		assertEquals("Testing that if there is nothing in the list",0,workerSearch.size());
		workerSearch = project.getWorkerbyID(3);
		assertEquals("Checking if there is a worker with the ID 2 in the work list",1,workerSearch.size());
	}
	//Maybe remove
	@Test
	public void addTasktest()
	{
		String taskName = "testTask";
		int[] completionDate = {2017, 3, 27};
		Task testTask = new Task(taskName, "This is a test task for testing purposes", completionDate, project);
		project.addTask(testTask);
		List<Task> projectTasks = project.getTaskbyName(taskName);
		assertEquals(projectTasks.size(),1);
		assertEquals("testTask",projectTasks.get(0).getName());
	}
}
