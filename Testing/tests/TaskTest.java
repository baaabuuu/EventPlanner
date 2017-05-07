package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.Project;
import application.Settings;
import application.Task;
import application.TaskInvalidInput;
import application.WorkWeek;
import application.Worker;
import application.WorkerMissingTask;

public class TaskTest {
	//s164147
	Project project = mock(Project.class);
	Task task;
	Worker worker = mock(Worker.class);
	Settings settings = mock(Settings.class);
	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	//s164166 && S164147
	@Before
	public void setup() throws TaskInvalidInput
	{
		//All projects in this test has to be created after this date.
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		
		when(settings.getCurrDate()).thenReturn((GregorianCalendar) cal);

		cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 3100);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		
		when(project.getDeadline()).thenReturn(cal);
		when(project.getSettings()).thenReturn(settings);
		
		cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		
		task = new Task("Test","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project);
		when(worker.getCurrWeek()).thenReturn(mock(WorkWeek.class));
	}
	
	@Test //s164166
	public void createInvalidTaskName() throws TaskInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		thrown.expect(TaskInvalidInput.class);
		task = new Task("","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project);
	}
	
	@Test //s164166
	public void createInvalidDate1() throws TaskInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2008);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		thrown.expect(TaskInvalidInput.class);
		task = new Task("","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project);
	}
	
	@Test //s164166
	public void createInvalidDate2() throws TaskInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 3199);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		when(project.getDeadline()).thenReturn(cal);
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 3200);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		thrown.expect(TaskInvalidInput.class);
		task = new Task("Test","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project);
	}
	

	
	@Test //s164166
	public void setInvalidDate1() throws TaskInvalidInput
	{

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 3200);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		
		thrown.expect(TaskInvalidInput.class);
		
		task.setDeadline(cal.getTime());
	}
	
	@Test //s164166
	public void setInvalidDate2() throws TaskInvalidInput
	{

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		
		thrown.expect(TaskInvalidInput.class);
		task.setDeadline(cal.getTime());
	}
	
	@Test //s164166
	public void setInvalidName() throws TaskInvalidInput
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, 2017);
		cal1.set(Calendar.MONTH, Calendar.MARCH);
		cal1.set(Calendar.DAY_OF_MONTH, 31);
		when(project.getDeadline()).thenReturn(cal1);
		task = new Task("Test","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal1.getTime(),project);
		thrown.expect(TaskInvalidInput.class);
		task.setName("");
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
	public void workTimeAssisted() throws WorkerMissingTask
	{
		assertEquals("No work Time",task.getWorkTime(),0);
		when(worker.timeSpentOnTaskThisWeek(task)).thenReturn(5);
		task.addWorker(worker);
		task.addAssistingWorker(worker);
		Worker worker2 = mock(Worker.class);
		task.addAssistingWorker(worker2);
		when(worker2.timeSpentOnTaskThisWeek(task)).thenReturn(5);
		assertEquals("10 Hours of work time, 5 in each.",10,task.getWorkTime());
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
