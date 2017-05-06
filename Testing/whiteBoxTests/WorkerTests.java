package whiteBoxTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import application.Settings;
import taskManagement.Task;
import workerLogic.Worker;
import workerLogic.WorkerNameError;

public class WorkerTests 
{
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
		worker = new Worker("",settings);
	}
	
	//In case a name is empty it should fail. s164166
	@Test
	public void setNameException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
	    worker = new Worker("Yes",settings);
		worker.setName("");
	}
	
	//Check that setname works without throwing exception if proper rules are followed. s164166
	@Test
	public void setName() throws WorkerNameError
	{
		worker = new Worker("Test_Worker",settings);
		assertEquals("Name has not changed test",worker.getName(),"Test_Worker");
		assertEquals("Work name has not changed",worker.getWorkName(),"Test");
		worker.setName("NewName");
		assertEquals("Name has changed NewName",worker.getName(),"NewName");
		assertEquals("Work name has changed New",worker.getWorkName(),"NewN");
		//Checking what occurs when the name is less than 4 letters
		worker.setName("Eva");
		assertEquals("Name has changed test",worker.getName(),"Eva");
		assertEquals("Work name has changed test",worker.getWorkName(),"Eva");
	}
	
	//Test whether or whether not the worker is available this week s164166
	@Test
	public void checkAvailableWeek() throws WorkerNameError
	{
		when(settings.getMaxAssignments()).thenReturn(20);
		worker = new Worker("Testname",settings);
		assertTrue("Check that the worker is availabile in the current week",worker.isAvailableCurrWeek());
		for (int i = 0; i < 20; i++)
		{
			worker.getCurrWeek().updAssignments(task);
		}
		assertFalse("Check that the worker is not availabile in the current week",worker.isAvailableCurrWeek());
	}
	
	//Test whether or whether not the worker is available next week s164166
	@Test
	public void checkMultipleWeeks() throws WorkerNameError
	{
		when(settings.getMaxAssignments()).thenReturn(20);
		worker = new Worker("Testname",settings);
		assertTrue("Check that the worker is availble in 1 week",worker.isAvailableXweek(1));
		assertTrue("Check that the worker is availble in 2 weeks",worker.isAvailableXweek(2));
		assertTrue("Check that the worker is availble in 100 weeks",worker.isAvailableXweek(100));
		for (int i = 0; i < 20; i++)
		{
			worker.getXweek(1).updAssignments(task);
			worker.getXweek(2).updAssignments(task);
			worker.getXweek(100).updAssignments(task);
		}
		assertFalse("Check that the worker is not availabile in the next week",worker.isAvailableXweek(1));
		assertFalse("Check that the worker is not availabile in 2 weeks from now",worker.isAvailableXweek(2));
		assertFalse("Check that the worker is not availabile in 100 weeks from now",worker.isAvailableXweek(100));
	}

	//Test whether a worker can be fired. s164166
	@Test
	public void fireWorker() throws WorkerNameError
	{
		worker = new Worker("Testname",settings);
		worker.fireWorker();
		assertFalse("Check that the worker is not available this week after being fired",worker.isAvailableCurrWeek());
		assertFalse("Check that the worker is not available 2 weeks after being fired",worker.isAvailableXweek(2));
	}
}
