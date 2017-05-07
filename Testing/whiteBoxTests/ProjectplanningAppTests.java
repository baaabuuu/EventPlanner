package whiteBoxTests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.ProjectPlanningApp;
import application.Task;
import application.Worker;
import application.WorkerNameError;

public class ProjectplanningAppTests 
{
	ProjectPlanningApp planningApp;
	
	Task task = mock(Task.class);
	Worker worker = mock(Worker.class);
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
}
