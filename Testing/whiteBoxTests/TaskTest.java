package whiteBoxTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import application.Project;
import application.Task;
import application.WorkWeek;
import application.Worker;
import application.WorkerMissingTask;

public class TaskTest {
	//s164147
	Project project = mock(Project.class);
	Task task;
	Worker worker = mock(Worker.class);
	
	//s164166 && S164147
	@Before
	public void setup()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		task = new Task("Test","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project);
		when(worker.getCurrWeek()).thenReturn(mock(WorkWeek.class));
	}
	
	@Test
	public void changeCompletionTest()
	{
		task.changeCompletion();
		assertTrue("Completion is now true",task.getStatus());
		task.changeCompletion();
		assertFalse("Completion is now true",task.getStatus());
	}
	
	@Test //s164166
	public void workTime() throws WorkerMissingTask
	{
		assertEquals("No work Time",task.getWorkTime(),0);
		when(worker.timeSpentOnTaskThisWeek(task)).thenReturn(5);
		task.addWorker(worker);
		assertEquals("5 Hours of work time",5,task.getWorkTime());
	}
	
	@Test //s164166
	public void addWorkerTets() throws WorkerMissingTask
	{
		task.addWorker(worker);
		assertEquals("Size is now equal to 1",task.getAssignedWorkers().size(),1);
		task.addWorker(worker);
		assertEquals("When adding workers, a worker can only be added once",task.getAssignedWorkers().size(),1);
	}
	
	@Test //s164166
	public void testRemoveWorkerNon()
	{
		when(worker.getWorkID()).thenReturn(0);
		task.addWorker(worker);
		task.removeWorker(1);
		assertEquals("Size is still equal to 1",task.getAssignedWorkers().size(),1);
	}
	
	@Test //s164166
	public void removeWorker1()
	{
		when(worker.getWorkID()).thenReturn(0);
		task.addWorker(worker);
		task.removeWorker(0);
		assertEquals("Size is now equal to 0",task.getAssignedWorkers().size(),0);
	}
}
