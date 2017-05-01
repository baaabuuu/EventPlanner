package Tests;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static  org.junit.Assert.assertFalse;
import static  org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import companyDatabase.CompanyWorkers;
import taskManagement.Task;
import workerLogic.Worker;
import workerLogic.WorkerNameError;

public class TaskTest 
{
	//Setup to ensure our tests work properly
	@Before
	public void setUp() throws WorkerNameError
	{
		//Adds a lot of workers to the list.
		CompanyWorkers.addWorker(new Worker("Jeff"));
		CompanyWorkers.addWorker(new Worker("Bridgett"));
		CompanyWorkers.addWorker(new Worker("Adam"));
		CompanyWorkers.addWorker(new Worker("dip"));
		CompanyWorkers.addWorker(new Worker("Keplan"));
		CompanyWorkers.addWorker(new Worker("Jeff"));
	}
	
	//Check whether a worker is eligible after a 
	@Test
	public void eligibleWorkers() {	
		ArrayList <Worker> eligibleWorkers = CompanyWorkers.getEligibleWorkers();
		assertEquals(eligibleWorkers.size(), CompanyWorkers.getAllWorkers().size());
		Worker randomWorker = eligibleWorkers.get(0);
		int[] i = {5,9,11};
		Task dummyTask = new Task("test", "test", i, null);
		while (randomWorker.isAvailableCurrWeek())
		{
			randomWorker.getCurrWeek().updAssignments(dummyTask);
		}
		assertTrue(eligibleWorkers.size() > CompanyWorkers.getEligibleWorkers().size());
	}

	
}
