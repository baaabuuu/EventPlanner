package whiteBoxTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import application.Project;
import application.ProjectPlanningApp;
import application.Task;
import application.Worker;
import application.WorkerMissingTask;

public class ProjectTest {
	//s164147
	Project project;
	ProjectPlanningApp database;
	Task task = mock(Task.class);
	Worker leader = mock(Worker.class);
	

	
	
	
	
	//s164166 && S164147
	@Before
	public void setup()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		project =	new Project("Test Project",cal.getTime(),leader);
	}
	
	@Test //s164166
	public void getTaskByIndexNull()
	{
		assertNull(project.getTask(0));
	}
	
	@Test //s164166
	public void getTaskByIndex()
	{
		when(task.getTaskID()).thenReturn(0);
		project.addTask(task);
		assertNotNull(project.getTask(0));
	}
	
	@Test //s164166
	public void getTaskByNameNull()
	{
		assertNull(project.getTask("Test_task"));
	}
	
	@Test //s164166
	public void getTaskByName()
	{
		when(task.getName()).thenReturn("Test_task");
		project.addTask(task);
		assertNotNull(project.getTask("Test_task"));
	}
	
	@Test //s164166
	public void getStatusUpd()
	{
		when(task.getStatus()).thenReturn(false);
		project.addTask(task);
		assertEquals("No completed tasks.",project.getStatus(),"0/1");
		when(task.getStatus()).thenReturn(true);
		assertEquals("No completed tasks.",project.getStatus(),"1/1");
	}
	
	@Test //s164166
	public void getWorkTime() throws WorkerMissingTask
	{
		when(task.getWorkTime()).thenReturn(2);
		project.addTask(task);
		assertEquals("Half hour spent should be 2.",project.getWorkTime(),2);
	}
	

	
	
}
