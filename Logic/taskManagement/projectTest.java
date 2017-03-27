package taskManagement;

import java.util.ArrayList;
import java.util.List;
import workerLogic.*;

public class projectTest {
	
	@test
	//Create project with name and two workers
	public void testCreateProject()
	{
		List<Worker> workers = new ArrayList<Worker>();
		workers.add(new Worker("aegfuhr",2));
		workers.add(new Worker("aeg",5));
		Project project = new Project(workers);
		
	}
}
