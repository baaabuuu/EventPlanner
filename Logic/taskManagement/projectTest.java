package taskManagement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import workerLogic.*;

import org.junit.Before;
import org.junit.Test;

public class projectTest {
	
	Project project = new Project(null, "testname");
	//@Test
	@Before
	//Create project with name and two workers
	public void testCreateProject() throws WorkerNameError
	{
		project.addWorker(new Worker("aegfuhr"));
		project.addWorker(new Worker("aeg"));
		assertEquals("testname",project.getName());
	}
	//@Test
	public void searchWorker()
	{
		List<Worker> workerSearch = project.getWorkerbyID(5);
		assertEquals(workerSearch.size(), 1);
	}
	@Test
	public void addTasktest()
	{
		String taskName = "testTask";
		int[] completionDate = {2017, 3, 27};
		Task testTask = new Task(taskName, "This is a test task for testing purposes", null,completionDate);
		project.addTask(testTask);
		List<Task> projectTasks = project.getTaskbyName(taskName);
		assertEquals(1,projectTasks.size());
		assertEquals("testTask",projectTasks.get(0).getName());
	}
}
