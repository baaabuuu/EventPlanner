package whiteBoxTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import workerLogic.*;

import org.junit.Before;
import org.junit.Test;

import application.ProjectPlanningApp;
import taskManagement.Project;
import taskManagement.Task;

public class projectTest {
	//S164147
	Project project;
	ProjectPlanningApp database;
	//s164166
	@Before
	public void setup() throws WorkerNameError
	{
		database	=	new ProjectPlanningApp();
		database.addNewWorker("Bob");
		database.addNewWorker("Jessica");
		database.addNewWorker("Tylor");
		database.addNewWorker("Hubert");
	}
	//Create project and check if name/date/worker getts worker 164166
	@Test
	public void testCreateProject() throws WorkerNameError
	{		
		//This is going to be converted to a gregorian calendar object.
		int[] completionDate = {2017, 3, 27};
		ArrayList<Worker> workers = new ArrayList<Worker>();
		workers.add(database.getWorker(0));
		workers.add(database.getWorker(1));
		workers.add(database.getWorker(3));
		project = new Project(workers, "testname",completionDate);
		assertEquals("Checking if Name constructor and getter works ", "testname", project.getName());
		assertEquals("Checking if Date constructor and getter works ", new GregorianCalendar(completionDate[0],completionDate[1],completionDate[2]),project.getDeadline());
		assertEquals("Checking if Worker constructor and getter works ", workers.size(),project.getWorkers().size());
		project = new Project(null, "testname",completionDate);
		assertNotNull("Check if constructor is null, that getWorkers is not null",project.getWorkers());
		
	}
	//Tests if the search worker function works s164166
	@Test
	public void searchWorker()
	{
		ArrayList<Worker> workers = new ArrayList<Worker>();
		workers.add(database.getWorker(0));
		workers.add(database.getWorker(1));
		workers.add(database.getWorker(3));
		int[] completionDate = {2017, 3, 27};
		project = new Project(workers, "Test_project",completionDate);
		
		Worker worker = project.getWorkerByID(-1);
		assertNull("Checking if worker is equal to null incase of invalid ID.",worker);
		worker = project.getWorkerByID(3);
		assertEquals("Checking if worker is equal to the one we were searching for.",worker,database.getAllWorkers().get(3));
		assertNotNull("Checking if worker is not equal to null.",worker);
	}
	//Test if adding a worker works to a project s164166
	@Test
	public void addWorker()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "Test_project",completionDate);
		assertEquals("Worker count is 0",project.getWorkers().size(),0);
		project.addWorker(database.getWorker(0));
		assertEquals("Worker count is 1",project.getWorkers().size(),1);
	}
	
	//See if it possible to remove workers easily s164166
	@Test
	public void removeWorker()
	{
		int[] completionDate = {2017, 3, 27};
		ArrayList<Worker> workers = new ArrayList<Worker>();
		workers.add(database.getWorker(0));
		workers.add(database.getWorker(1));
		workers.add(database.getWorker(3));
		project = new Project(workers, "Test_project",completionDate);
		assertEquals("Worker count is 3",project.getWorkers().size(),3);
		project.removeWorker(2);
		assertEquals("Worker count is 2",project.getWorkers().size(),2);
		Worker worker = project.getWorkerByID(0);
		project.removeWorker(worker);
		assertEquals("Worker count is 2",project.getWorkers().size(),1);
	}
	//See if it possible to add tasks s164166
	@Test
	public void addTask()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "Test_project",completionDate);
		assertEquals("Testing that there are no tasks currently", project.getTasks().size(),0);
		project.addTask(new Task("Test Task","This is a test description",completionDate,project));
		assertEquals("Testing that 1 task has been added to the tasklist", project.getTasks().size(),1);
	}
	
	//See if it possible to add tasks s164166
	@Test
	public void removeTask()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "Test_project",completionDate);
		project.addTask(new Task("Test Task","This is a test description",completionDate,project));
		Task dummyTask = new Task("Test Task2","",completionDate,project);
		project.addTask(dummyTask);
		project.removeTask(0);
		assertEquals("Testing removal by index", project.getTasks().size(),1);
		project.removeTask(dummyTask);
		assertEquals("Testing removal by object", project.getTasks().size(),0);
	}
	//Check the search function to get Tasks s164166
	@Test
	public void getTask()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "Test_project",completionDate);
		project.addTask(new Task("Test Task","This is a test description",completionDate,project));
		Task dummyTask = new Task("Test Task2","",completionDate,project);
		project.addTask(dummyTask);
		assertEquals("Testing by using search by name",project.getTask(dummyTask.getName()),dummyTask);
		assertEquals("Testing by using search by index",project.getTask(1),dummyTask);
	}
	
	//Check the get and set name functionality s164166
	@Test
	public void getSetName()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "Name_part_1",completionDate);
		assertEquals("Testing name getter part 1",project.getName(),"Name_part_1");
		project.setName("Name_part_2");
		assertEquals("Testing name getter part 1",project.getName(),"Name_part_2");
	}
	
	//Check the ID functions s164166
	@Test
	public void idFunctionality()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "Name_part_1",completionDate);
		assertEquals("Testing the  getter",project.getID(),0);
		project.setID(-5);
		assertEquals("Testing the setter.",project.getID(),-5);
	}
	
	//Check the work time update functionality
	//This function does not test for WorkerMissing Task
	//TODO FIX THIS s164166
	@Test
	public void workTimeUpdate() throws WorkerMissingTask
	{
		int[] completionDate = {2017, 3, 27};
		ArrayList<Worker> workers = new ArrayList<Worker>();
		workers.add(database.getWorker(0));
		workers.add(database.getWorker(1));
		workers.add(database.getWorker(3));
		project = new Project(workers, "",completionDate);
		assertEquals("Zero time has been spent",project.getWorkTime(),0);
		Task dummyTask = new Task("Test Task2","",completionDate,project);
		Worker tempWorker = workers.get(0);
		tempWorker.getCurrWeek().updAssignments(dummyTask);
		tempWorker.getCurrWeek().updWorkTime(0, 5);
		assertEquals("5 half hours has been spent",project.getWorkTime(),5);
	}
	
	//Check the completion functionality s164166
	@Test
	public void completeTest()
	{
		int[] completionDate = {2017, 3, 27};
		project = new Project(null, "",completionDate);
		assertEquals("Completed tasks 0/0","0/0",project.getStatus());
		Task dummyTask = new Task("Test Task2","",completionDate,project);
		project.addTask(dummyTask);
		dummyTask = new Task("Test Task2","",completionDate,project);
		project.addTask(dummyTask);
		assertEquals("Completed tasks 0/2","0/2",project.getStatus());
		dummyTask.changeCompletion();
		assertEquals("Completed tasks 1/2","1/2",project.getStatus());		
	}
	
	//Check the set leader functionality s164166
	@Test
	public void testLeader()
	{
		int[] completionDate = {2017, 3, 27};
		ArrayList<Worker> workers = new ArrayList<Worker>();
		workers.add(database.getWorker(0));
		workers.add(database.getWorker(1));
		workers.add(database.getWorker(3));
		project = new Project(workers, "",completionDate);
		assertNull("No leader",project.getLeader());
		project.setLeader(database.getWorker(2));
		assertEquals("Leader is equal to workers 2",database.getWorker(2),project.getLeader());
	}
}
