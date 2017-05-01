package whiteBoxTests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import application.ProjectPlanningApp;
import application.Settings;
import taskManagement.Project;
import taskManagement.Task;
import workerLogic.Worker;
import workerLogic.WorkerNameError;

public class WorkerLogicTests 
{
	Project project;
	ProjectPlanningApp database;
	Task task;
	Worker worker;
	Settings settings;
	
	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	@Before //s164166
	public void setup() throws WorkerNameError
	{
		settings	=	 new Settings();
		worker = new Worker("Testname",settings);
	}
	
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
	@Test
	public void changeID() throws WorkerNameError
	{		
		assertEquals("Worker ID should be 0",worker.getWorkID(),0);
		worker.setWorkID(1);
		assertEquals("Worker ID should be 1",worker.getWorkID(),1);
	}

	
	
	
	

	
	/**	
	//Test whether or whether not the worker is available this week
	@Test
	public void checkThisWeekAgain()
	{
		Worker worker = CompanyWorkers.getWorker(1);
		assertTrue(worker.isAvailableCurrWeek());
	}
	
	//Check whether a worker is eligible after a 
	@Test
	public void eligibleWorkers() {	
		ArrayList <Worker> eligibleWorkers = CompanyWorkers.getEligibleWorkers();
		assertEquals(eligibleWorkers.size(), CompanyWorkers.getAllWorkers().size());
		Worker randomWorker = eligibleWorkers.get(0);
		int[] i = {5,9,11};
		Task dummyTask = new Task("test", "test", i, null);
		while (randomWorker.isAvailableCurrWeek())
		{
			randomWorker.getCurrWeek().updAssignments(dummyTask);
		}
		assertTrue(eligibleWorkers.size() > CompanyWorkers.getEligibleWorkers().size());
	}
	
	//Test whether or whether not the worker is available next week
	@Test
	public void checkNextWeek()
	{
		Worker worker = CompanyWorkers.getWorker(1);
		assertTrue(worker.isAvailableXweek(1));
	}
	
	//Test whether or whether not the worker is available this week
	@Test
	public void checkThisWeek()
	{
		Worker worker = CompanyWorkers.getWorker(1);
		assertTrue(worker.isAvailableCurrWeek());
	}
	
	//Test whether a worker can be fired.
	@Test
	public void fireWorker()
	{
		Worker worker = CompanyWorkers.getWorker(2);
		worker.fireWorker();
		assertFalse(worker.isAvailableCurrWeek());
		assertFalse(worker.isAvailableXweek(2));
	}
	**/
}
