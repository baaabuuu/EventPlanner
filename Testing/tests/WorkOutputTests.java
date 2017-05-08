package tests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import application.WorkOutput;
import application.WorkWeek;
import application.Worker;
import application.WorkerMissingTask;

public class WorkOutputTests 
{
	ProjectPlanningApp planningApp;
	
	Task		task			=	mock(Task.class);
	Worker		worker			=	mock(Worker.class);
	Settings	settings 		=	mock(Settings.class);
	Calendar	cal				=	mock(Calendar.class);
	WorkWeek	week			=	mock(WorkWeek.class);
	Project		project			=	mock(Project.class);
	WorkOutput	outputter;

	@Rule //s164166 
    public ExpectedException thrown = ExpectedException.none();
	
	@Before //s164166 s164147
	public void setup() 
	{		
		outputter	=	new WorkOutput();
	}
	
	@Test //s164166 s164147
	public void throwInvalidDateRange1() throws InvalidDateRange, WorkerMissingTask, IOException
	{
		thrown.expect(InvalidDateRange.class);
		outputter.generateText(worker, -1, 1, settings);
	}
	
	@Test //s164166 s164147
	public void throwInvalidDateRange2() throws InvalidDateRange, WorkerMissingTask, IOException
	{
		thrown.expect(InvalidDateRange.class);
		outputter.generateText(worker, 3, 1, settings);
	}
	
	@Test //s164166 s164147
	public void noErrors() throws InvalidDateRange, WorkerMissingTask, IOException
	{
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
		
		outputter.generateText(worker, 0, 0, settings);
		File f = new File("WorkerInfo\\Test#0.txt"); 
		
		if (!f.exists()) {
			fail();
		}
	}
}
