package logicTesting;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static  org.junit.Assert.assertFalse;
import static  org.junit.Assert.assertTrue;
import static  org.junit.Assert.fail;

import org.junit.Test;
import companyDatabase.CompanyWorkers;
import workerLogic.Worker;
import workerLogic.WorkerNameError;

public class WorkerLogicTests 
{
		//Setup to ensure our tests work properly
	protected void setUp()
	{
		   //Adds a lot of workers to the list.
		   try {
			   CompanyWorkers.addWorker(new Worker("Jeff"));
			   CompanyWorkers.addWorker(new Worker("Bridgett"));
			   CompanyWorkers.addWorker(new Worker("Adam"));
			   CompanyWorkers.addWorker(new Worker("dip"));
			   CompanyWorkers.addWorker(new Worker("ASODFHAJSHFALSJGHAHGALSKHDFKASHDKLFAMSLDGHAISDHGASDFASDFASDFAS"));
			   CompanyWorkers.addWorker(new Worker("Jeff"));
		} catch (WorkerNameError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void elligbleWorkers() {	
		setUp();
		assertEquals(CompanyWorkers.getElligbleWorkers().size(), CompanyWorkers.getAllWorkers().size());     
	}
}
