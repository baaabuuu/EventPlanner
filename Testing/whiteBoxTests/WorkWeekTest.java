package whiteBoxTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.Settings;
import application.Task;
import application.WorkWeek;
import application.Worker;
import application.WorkerMissingTask;

public class WorkWeekTest {
	Task task = mock(Task.class);
	Worker worker = mock(Worker.class);
	Settings settings = mock(Settings.class);
	WorkWeek week;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before //s164166
	public  void setup()
	{
		when(settings.getMaxAssignments()).thenReturn(20);
		week = new WorkWeek(settings);
	}
		
	//s164166
	@Test
	public void getWorkTaskErrork() throws WorkerMissingTask
	{
		thrown.expect(WorkerMissingTask.class);
		week.getWorkTask(0);
	}
	//s164166
	@Test
	public void getWorkTaskNoError() throws WorkerMissingTask
	{
		week.updAssignments(task);
		assertNotNull("We have added a new thing, therefor it will not return null",week.getWorkTask(0));
	}
	
	//s164166
	@Test
	public void getWorkTimeExpception() throws WorkerMissingTask
	{
		thrown.expect(WorkerMissingTask.class);
		week.getWorkTime(0);
	}
	
	//s164166
	@Test
	public void updWorkTimeException() throws WorkerMissingTask
	{
		thrown.expect(WorkerMissingTask.class);
		week.updWorkTime(0, 2);
	}
		
	//s164166
	@Test
	public void updWorkTimeNoException() throws WorkerMissingTask
	{
		week.updAssignments(task);
		week.updWorkTime(0, 2);
		assertEquals("Time spent 2 hours",week.getWorkTime(0),2);
	}
	
	//s164166
	@Test
	public void getWorkTimeNoException() throws WorkerMissingTask
	{
		week.updAssignments(task);
		week.getWorkTime(0);
		assertEquals("Time spent 2 hours",week.getWorkTime(0),0);
	}
	
	//s164166
	@Test
	public void isLegalThisWeekTrue()
	{
		when(worker.isFired()).thenReturn(false);
		assertTrue("Works is not fired, week is not bussy, and there are less assignments than the maximum.",week.isLegalThisweek(worker));
	}
	//s164166
	@Test
	public void isLegalThisWeekFired()
	{
		when(worker.isFired()).thenReturn(true);
		assertFalse("Worker is  fired, project not bussy, less assignments than max",week.isLegalThisweek(worker));
	}
	//s164166
	@Test
	public void isLegalThisWeekIsBussy()
	{
		when(worker.isFired()).thenReturn(false);
		week.setBussy();
		assertFalse("Worker is not fired, project bussy, less assignments than max",week.isLegalThisweek(worker));
	}
	//s164166
	@Test
	public void isLegalThisWeekFiredAndBussy()
	{
		when(worker.isFired()).thenReturn(true);
		week.setBussy();
		assertFalse("Worker is  fired, project bussy, less assignments",week.isLegalThisweek(worker));
	}
	//s164166
	@Test
	public void isNotLegalThisWeek()
	{
		when(worker.isFired()).thenReturn(true);
		week.setBussy();
		for (int i = 0; i<21;i++)
			week.updAssignments(task);
		assertFalse("Worker is  fired, project bussy, max assignments reached",week.isLegalThisweek(worker));
	}
	
	//s164166
	@Test
	public void NotLegalThisWeekfilledSchedule()
	{
		when(worker.isFired()).thenReturn(false);
		for (int i = 0; i<20;i++)
			week.updAssignments(task);
		assertFalse("Worker is not  fired, project is not bussy, max assignments reached",week.isLegalThisweek(worker));
	}
}