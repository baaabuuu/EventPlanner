package tests;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import application.InvalidTime;
import application.Settings;
import application.Task;
import application.Worker;
import application.WorkerMissingTask;
import application.WorkerNameError;

public class WorkerTests 
{
	Task task = mock(Task.class);
	Settings settings = mock(Settings.class);
	Worker worker;

	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	@Before //s164166
	public void setup() throws WorkerNameError
	{
		when(settings.getMaxAssignments()).thenReturn(20);
		worker = new Worker("Testname",settings);
	}
		
	//In case a name is empty it should fail. s164166 s160902
	@Test
	public void createWorkerNameException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
		worker = new Worker("",settings);
	}
	
	//In case a name is empty it should fail.  s164166 s160902
	@Test
	public void setNameException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
	    worker = new Worker("Yes",settings);
	    worker.setName("");
	}
	
	//Check that setname works without throwing exception if proper rules are followed.  s164166 s160902
	@Test
	public void setNameMorethan4Chars() throws WorkerNameError
	{
		worker.setName("NewName");
		assertEquals("Name has changed NewName",worker.getName(),"NewName");
		assertEquals("Work name has changed New",worker.getWorkName(),"NewN");
	}
	//Check that setname works without throwing exception if proper rules are followed.  s164166 s160902
	@Test
	public void setNameLessthanOrEqualTo4Chars() throws WorkerNameError
	{
		worker.setName("Eva");
		assertEquals("Name has changed test",worker.getName(),"Eva");
		assertEquals("Work name has changed test",worker.getWorkName(),"Eva");
	}
	
	//Test whether or whether not the worker is available this week s164166 s164147
	@Test
	public void checkAvailableWeek()
	{
		when(settings.getMaxAssignments()).thenReturn(20);
		assertTrue("Check that the worker is availabile in the current week",worker.isAvailableCurrWeek());
		for (int i = 0; i < 20; i++)
		{
			worker.getCurrWeek().updAssignments(task);
		}
		assertFalse("Check that the worker is not availabile in the current week",worker.isAvailableCurrWeek());
	}
	
	//Test whether or whether not the worker is available next week s164166 s164147
	@Test
	public void checkMultipleWeeks()
	{
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

	//Test whether a worker can be fired, and if they are fired they are not available in the next couple of weeks s164166 s164147
	@Test
	public void fireWorker()
	{
		worker.fireWorker();
		assertFalse("Check that the worker is not available this week after being fired",worker.isAvailableCurrWeek());
		assertFalse("Check that the worker is not available 2 weeks after being fired",worker.isAvailableXweek(2));
	}
	
	//Test the timeSpentOnAtask s164166 s164147
	@Test
	public void timeSpentOnTaskTest() throws WorkerMissingTask, InvalidTime
	{
		worker.getCurrWeek().updAssignments(task);		
		worker.getCurrWeek().updWorkTime(0, 2);		
		assertEquals("Checking that 2 hours has been worked on the task", worker.timeSpentOnTask(task),2);
		worker.getCurrWeek().uppHelpedTasks(10, task);
		assertEquals("Checking that 12 hours has been worked on the task where 10 has been \"helped\"", worker.timeSpentOnTask(task),12);
	}
	//Test the timeSpentOnAtask s164166 s164147
	@Test
	public void timeSpentThisWeekOnTask() throws WorkerMissingTask, InvalidTime
	{
		worker.getCurrWeek().updAssignments(task);		
		worker.getCurrWeek().updWorkTime(0, 2);		
		assertEquals("Checking that 2 hours has been worked on the task", worker.timeSpentOnTaskThisWeek(task),2);
		worker.getCurrWeek().uppHelpedTasks(10, task);
		assertEquals("Checking that 12 hours has been worked on the task where 10 has been \"helped\"", worker.timeSpentOnTaskThisWeek(task),12);
	}
}
