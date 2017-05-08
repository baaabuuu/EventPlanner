package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.Project;
import application.ProjectInvalidInput;
import application.ProjectPlanningApp;
import application.Settings;
import application.Task;
import application.Worker;
import application.WorkerMissingTask;

/**
 * Each test was written with s164166 and whoever thought it was necessary  to test it.
 */
public class ProjectTest {
	Project project;
	ProjectPlanningApp database;
	Task task = mock(Task.class);
	Worker leader = mock(Worker.class);
	
	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();

	
	//s164166 & S164147
	@Before
	public void setup() throws ProjectInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		project =	new Project("Test Project","Test",cal.getTime(),leader,new Settings());
	}
	
	
	@Test //s164166 s160902
	public void getTaskByIndexNull()
	{
		assertNull(project.getTask(0));
	}
	
	@Test //s164166 & S164147
	public void createInvalidName() throws ProjectInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		thrown.expect(ProjectInvalidInput.class);
		project =	new Project("","Test",cal.getTime(),leader, new Settings());
	}
	
	
	@Test //s164166 & S164147
	public void setInvalidName() throws ProjectInvalidInput
	{
		thrown.expect(ProjectInvalidInput.class);
		project.setName("");
	}	
	
	
	@Test //s164166 & s160902
	public void createInvalidDate() throws ProjectInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		thrown.expect(ProjectInvalidInput.class);
		project =	new Project("Test","Test",cal.getTime(),leader, new Settings());
	}
	
	
	@Test //s164166 & s160902
	public void setInvalidDate() throws ProjectInvalidInput
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		project =	new Project("Test","Test",cal.getTime(),leader, new Settings());
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		thrown.expect(ProjectInvalidInput.class);
		project.setDeadline(cal.getTime());
	}

	
	@Test //s164166 & s164147
	public void getTaskByIndex()
	{
		when(task.getTaskID()).thenReturn(0);
		project.addTask(task);
		assertNotNull(project.getTask(0));
	}
	
	@Test //s164166  s164147
	public void getTaskByNameNull()
	{
		assertNull(project.getTask("Test_task"));
	}
	
	@Test //s164166  s164147
	public void getTaskByName()
	{
		when(task.getName()).thenReturn("Test_task");
		project.addTask(task);
		assertNotNull(project.getTask("Test_task"));
	}
	
	@Test //s164166 & s160902
	public void getStatusUpd()
	{
		when(task.getStatus()).thenReturn(false);
		project.addTask(task);
		assertEquals("No completed tasks.",project.getStatus(),"0/1");
		when(task.getStatus()).thenReturn(true);
		assertEquals("No completed tasks.",project.getStatus(),"1/1");
	}
	
	@Test //s164166 & s160902
	public void getWorkTime() throws WorkerMissingTask
	{
		when(task.getWorkTime()).thenReturn(2);
		project.addTask(task);
		assertEquals("Half hour spent should be 2.",project.getWorkTime(),2);
	}
	
	@Test //s164166 & s160902
	public void removeTaskError1() throws ProjectInvalidInput
	{
		thrown.expect(ProjectInvalidInput.class);
		project.removeTask(-1);
	}
	
	@Test //s164166 & s160902
	public void removeTaskErrorr() throws ProjectInvalidInput
	{
		thrown.expect(ProjectInvalidInput.class);
		project.removeTask(1);
	}
	
	@Test //s164166 & s160902
	public void removeTaskNoError() throws ProjectInvalidInput
	{
		project.addTask(task);
		project.removeTask(0);
	}
	
}
