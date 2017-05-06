package whiteBoxTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import application.ProjectPlanningApp;
import application.Settings;
import taskManagement.Project;
import taskManagement.Task;
import workerLogic.Worker;
import workerLogic.WorkerNameError;

public class WorkerTests 
{
	Project project = mock(Project.class);
	ProjectPlanningApp database = mock(ProjectPlanningApp.class);
	Task task = mock(Task.class);
	Settings settings = mock(Settings.class);
	Worker worker;

	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	//Worker Creation. s164166
	@Test
	public void createWorkers() throws WorkerNameError
	{
		worker = new Worker("Testname",settings);
		assertEquals("Name is equal to Testname",worker.getName(),"Testname");
		assertEquals("Work name is equal to ",worker.getWorkName(),"Test");
		assertNotNull(worker.getCurrWeek());
	}
	
	//In case a name is empty it should fail. s164166
	@Test
	public void createWorkerException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
        //you can test the exception message like s164166
        thrown.expectMessage("Names cannot be empty strings.");
		worker = new Worker("",settings);
	}
	
	//In case a name is empty it should fail. s164166
	@Test
	public void setNameException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
	    //you can test the exception message like
	    thrown.expectMessage("Names cannot be empty strings.");
	    worker = new Worker("Yes",settings);
		worker.setName("");
	}
	
	//Check that setname and gamename works without throwing exception if proper rules are followed. s164166
	@Test
	public void setName() throws WorkerNameError
	{
		worker = new Worker("Test_Worker",settings);
		assertEquals("Name has not changed test",worker.getName(),"Test_Worker");
		assertEquals("Work name has not changed test",worker.getWorkName(),"Test");
		worker.setName("NewName");
		assertEquals("Name has changed test",worker.getName(),"NewName");
		assertEquals("Work name has changed test",worker.getWorkName(),"NewN");
	}
	
	//Test whether or whether not the worker is available this week s164166
	@Test
	public void checkAvailableWeek() throws WorkerNameError
	{
		worker = new Worker("Testname",settings);
		assertTrue("Check that the worker is availabile in the current week",worker.isAvailableCurrWeek());
	}
	
	//Test whether or whether not the worker is available next week
	@Test
	public void checkNextWeek() throws WorkerNameError
	{
		worker = new Worker("Testname",settings);
		assertTrue(worker.isAvailableXweek(1));
		assertTrue(worker.isAvailableXweek(2));
		assertTrue(worker.isAvailableXweek(100));
	}

	//Test whether a worker can be fired.
	@Test
	public void fireWorker() throws WorkerNameError
	{
		worker = new Worker("Testname",settings);
		worker.fireWorker();
		assertFalse("Check that the worker is not available this week after being fired",worker.isAvailableCurrWeek());
		assertFalse("Check that the worker is not available 2 weeks after being fired",worker.isAvailableXweek(2));
	}
}
