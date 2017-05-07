package whiteBoxTests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.InvalidDateRange;
import application.Project;
import application.ProjectPlanningApp;
import application.Settings;
import application.Task;
import application.WorkWeek;
import application.Worker;
import application.WorkerMissingTask;
import application.WorkerNameError;

public class ProjectplanningAppTests 
{
	ProjectPlanningApp planningApp;
	
	Task task		 		=	mock(Task.class);
	Worker worker 			=	mock(Worker.class);
	Settings	settings	=	mock(Settings.class);
	Calendar cal;

	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup() 
	{
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		
		planningApp = new ProjectPlanningApp();
	}
	
	@Test
	public void getEligibleWorkersNonAvailableThisWeek() throws WorkerNameError
	{
		when(worker.isAvailableCurrWeek()).thenReturn(false);
		planningApp.addNewWorker(worker);
		assertEquals("There are no workers in this list",planningApp.getEligibleWorkers().size(),0);
	}
	
	@Test
	public void getEligibleWorkersThereIsAvailableWorkers() throws WorkerNameError
	{
		when(worker.isAvailableCurrWeek()).thenReturn(true);
		planningApp.addNewWorker(worker);
		assertEquals("There are workers in this list",planningApp.getEligibleWorkers().size(),1);
	}
	
	@Test
	public void getAllNonFiredWorkersAllAreFired() throws WorkerNameError
	{
		when(worker.isFired()).thenReturn(true);
		planningApp.addNewWorker(worker);
		assertEquals("There are no workers in this list",planningApp.getAllWorkers().size(),0);
	}
	
	@Test
	public void getAllNonFiredWorkersNoneAreFired() throws WorkerNameError
	{
		when(worker.isFired()).thenReturn(false);
		planningApp.addNewWorker(worker);
		assertEquals("There are workers in this list",planningApp.getAllWorkers().size(),1);
	}
	//s164166
	@Test
	public void updHelpedTasksTimeException() throws WorkerNameError
	{
		thrown.expect(WorkerNameError.class);
		planningApp.addNewWorker("");
	}
	//s164166
	@Test
	public void removeProject() throws WorkerNameError
	{
		planningApp.addNewProject("Test", cal.getTime(), worker);
		planningApp.removeProject(-1);
		assertEquals("Project Size is 1",planningApp.getAllProjects().size(),1);
		planningApp.removeProject(1);
		assertEquals("Project Size is 1",planningApp.getAllProjects().size(),1);
		planningApp.removeProject(0);
		assertEquals("Project Size is 0",planningApp.getAllProjects().size(),0);
	}
	
	//s164166
	@Test
	public void removeWorker() throws WorkerNameError
	{
		planningApp.addNewWorker("Test");
		planningApp.removeWorker(-1);
		assertEquals("Worker Size is 1",planningApp.getAllWorkers().size(),1);
		planningApp.removeWorker(1);
		assertEquals("Worker Size is 1",planningApp.getAllWorkers().size(),1);
		planningApp.removeWorker(0);
		assertEquals("Worker Size is 0",planningApp.getAllWorkers().size(),0);
	}
	
	//s164166
	@Test
	public void workWeekPrintFailRange1() throws WorkerMissingTask, IOException, InvalidDateRange
	{
		thrown.expect(InvalidDateRange.class);
		planningApp.printWorkWeeks(worker,-1,0,settings);
	}
	
	//s164166
	@Test
	public void workWeekPrintFailRange2() throws WorkerMissingTask, IOException, InvalidDateRange
	{
		thrown.expect(InvalidDateRange.class);
		planningApp.printWorkWeeks(worker,1,0,settings);
	}
	
	@Test //s164166
	public void noErrors() throws InvalidDateRange, WorkerMissingTask, IOException
	{
		WorkWeek week = mock(WorkWeek.class);
		Project	project	=	mock(Project.class);
		when(settings.getMaxAssignments()).thenReturn(20);
		when(settings.getStartupDate()).thenReturn(new GregorianCalendar(2017, Calendar.APRIL, 10));

		
		when(task.getName()).thenReturn("Test_task");
		when(task.getTaskID()).thenReturn(0);
		
		when(week.getCurrTaskAmm()).thenReturn(1);
		when(week.getWorkTask(0)).thenReturn(task);
		
		when(week.getTimeWorkedDay(0, 0)).thenReturn(2);
		when(week.getTimeWorkedDay(1, 0)).thenReturn(5);
		when(week.getTimeWorkedDay(2, 0)).thenReturn(9);
		when(week.getTimeWorkedDay(3, 0)).thenReturn(12);
		when(week.getTimeWorkedDay(4, 0)).thenReturn(5);
		when(week.getTimeWorkedDay(5, 0)).thenReturn(1);
		when(week.getTimeWorkedDay(6, 0)).thenReturn(0);
		when(week.getTimeWorkedDay(7, 0)).thenReturn(24);
		
		when(week.getHelpedTaskCount()).thenReturn(1);
		
		ArrayList<Task> testList = new ArrayList<Task>();
		testList.add(task);
		
		when(week.getHelpedTasks()).thenReturn(testList);
		
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(5);
		
		when(week.getHelpedTasksTime()).thenReturn(intList);
		
		when(worker.getName()).thenReturn("Test");
		when(worker.getWorkName()).thenReturn("Test");
		when(worker.getWorkID()).thenReturn(0);
		
		when(worker.getWeekFromStart(0)).thenReturn(week);
		when(worker.getWeekFromStart(1)).thenReturn(week);
		
		when(project.getName()).thenReturn("Test Project");
		when(task.getProject()).thenReturn(project);
		
		planningApp.printWorkWeeks(worker, 0, 1, settings);
		File f = new File("WorkerInfo\\Test#0.txt"); 
		
		if (!f.exists()) {
			fail();
		}
	}
	//s164166
	@Test
	public void advanceDayTest() throws WorkerMissingTask
	{
		ArrayList<Worker> communism = new ArrayList<Worker>();
		communism.add(worker);
		when(task.getAssignedWorkers()).thenReturn(communism);
		planningApp.addNewProject("Test", planningApp.getSettings().getCurrDate().getTime(), worker);
		task.addWorker(worker);
		planningApp.getAllProjects().get(0).addTask(task);
		assertEquals("There is 1 worker assigned to the project", planningApp.getAllProjects().get(0).getTask(0).getAssignedWorkers().size(),1);
		
		planningApp.advanceDay();
		planningApp.advanceDay();
		planningApp.advanceDay();
		planningApp.advanceDay();
		planningApp.advanceDay();
		planningApp.advanceDay();
		
		assertEquals("There is 1 worker assigned to the project", planningApp.getAllProjects().get(0).getTask(0).getAssignedWorkers().size(),1);
		planningApp.advanceDay();
		when(task.getAssignedWorkers()).thenReturn(new ArrayList<Worker>());
		assertEquals("There is 0 workers assigned to the project", planningApp.getAllProjects().get(0).getTask(0).getAssignedWorkers().size(),0);
	}
	
	
}
