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
	
}
