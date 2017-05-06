package whiteBoxTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import workerLogic.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import application.ProjectPlanningApp;
import taskManagement.Project;
import taskManagement.Task;

public class TaskTest {
	//s164147
	Project project;
	ProjectPlanningApp database;
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
		task = new Task("Test","Test",cal.getTime(),null);
	}
	
	@Test //s164166
	public void noWorkTime() throws WorkerMissingTask
	{
		assertEquals("No work Time",task.getWorkTime(),0);
		when(worker.timeSpentOnTask(task)).thenReturn(5);
		task.addWorker(worker);
		assertEquals("No work Time",task.getWorkTime(),5);
	}
	
	
	
	
}
