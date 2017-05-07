package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.Project;
import application.ProjectInvalidInput;
import application.Settings;
import application.Task;
import application.TaskInvalidInput;
import application.Worker;
import application.WorkerNameError;

public class BlackBoxTestsMatter {

	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createProjectWorking() throws ProjectInvalidInput, WorkerNameError
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
	}
	
	@Test //s164166
	public void createInvalidDateProject() throws ProjectInvalidInput, WorkerNameError
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2002);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		thrown.expect(ProjectInvalidInput.class);
		Project project =	new Project("Test_Project",cal.getTime(),worker, new Settings());
	}
	
	
	@Test //s164166
	public void createInvalidNameProject() throws ProjectInvalidInput, WorkerNameError
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2008);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		thrown.expect(ProjectInvalidInput.class);
		Project project =	new Project("",cal.getTime(),worker, new Settings());
	}
	
	@Test //s164166
	public void addTaskToProject() throws ProjectInvalidInput, WorkerNameError, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
	}
	
	@Test //s164166
	public void addTaskToProjectDateError() throws ProjectInvalidInput, WorkerNameError, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2019);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		thrown.expect(TaskInvalidInput.class);
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
	}
	
	@Test //s164166
	public void changeTaskComplete1() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		assertFalse("Hasn't been changed yet",testTask.getStatus());
	}
	
	@Test //s164166
	public void changeTaskComplete2() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		testTask.changeCompletion();
		assertTrue("Has been changed",testTask.getStatus());
	}
	
	@Test //s164166
	public void projectCompletion1() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		assertEquals("Project has not yet been completed",project.getStatus(),"0/1");
	}
	
	@Test //s164166
	public void projectCompletion2() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		testTask.changeCompletion();
		assertEquals("Project has been completed",project.getStatus(),"1/1");
	}
	
	@Test //s164166
	public void taskRemoval1() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		project.removeTask(0);
		assertEquals("Properly removed the project",project.getTasks().size(),0);
	}
	
	@Test //s164166
	public void taskRemoval2() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		thrown.expect(ProjectInvalidInput.class);
		project.removeTask(-1);
	}
	
	@Test //s164166
	public void taskRemoval3() throws WorkerNameError, ProjectInvalidInput, TaskInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		thrown.expect(ProjectInvalidInput.class);
		project.removeTask(2);
	}
	
	//In case a name is empty it should fail. s164166
	@Test
	public void createWorkerNameException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
		Worker worker = new Worker("",new Settings());
	}
	
	//s164166
	@Test
	public void createWorkerNoeException() throws WorkerNameError
	{
		Worker worker = new Worker("Test",new Settings());
	}
	
	@Test //s164166
	public void setLegalDate() throws TaskInvalidInput, WorkerNameError, ProjectInvalidInput
	{

		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 7);
		testTask.setDeadline(cal.getTime());
	}
	
	@Test //s164166
	public void setIllegalDate1() throws TaskInvalidInput, WorkerNameError, ProjectInvalidInput
	{

		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		cal.set(Calendar.YEAR, 2019);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 7);
		
		thrown.expect(TaskInvalidInput.class);
		testTask.setDeadline(cal.getTime());
	}
	
	@Test //s164166
	public void setIllegalDate2() throws TaskInvalidInput, WorkerNameError, ProjectInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		cal.set(Calendar.YEAR, 1941);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 7);
		thrown.expect(TaskInvalidInput.class);
		testTask.setDeadline(cal.getTime());
	}
	
	@Test //s164166
	public void changeTaskNameIllegal() throws TaskInvalidInput, WorkerNameError, ProjectInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		thrown.expect(TaskInvalidInput.class);
		testTask.setName("");
	}
	
	@Test //s164166
	public void changeTaskNameLegal() throws TaskInvalidInput, WorkerNameError, ProjectInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		testTask.setName("Test_new");
		assertEquals("Name is now Test_new",testTask.getName(),"Test_new");
	}
	
	@Test //s164166
	public void changeDescLegal() throws TaskInvalidInput, WorkerNameError, ProjectInvalidInput
	{
		ArrayList<Worker> workers1 = new ArrayList<Worker>();
		ArrayList<Worker> workers2 = new ArrayList<Worker>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		Worker worker = new Worker("Adam", new Settings());
		Project project =	new Project("Test Project",cal.getTime(),worker, new Settings());
		Task testTask = new Task("Test task", "Test description", workers1, workers2, cal.getTime(), project);
		testTask.setDescription("Test");
		assertEquals("Description is now Test",testTask.getDescription(),"Test");
	}
	
}
