package whiteBoxTests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

	@Rule //s164166
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup() 
	{
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
}
