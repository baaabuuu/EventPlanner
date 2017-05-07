package application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkOutput {
	
	private	final static	String[] days = {"Monday\t","Tuesday\t","Wednessday","Thursday","Friday\t","Saturday","Sunday\t"};

	
	public static void main(String[] args) throws WorkerNameError, WorkerMissingTask, IOException, InvalidDateRange, InvalidTime
	{
		Settings settings = new Settings();
		Worker testWorker = new Worker("George", settings);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		
		Project project = new Project("Test project", cal.getTime(), testWorker);
		testWorker.getCurrWeek().updAssignments(new Task("Task","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project));
		testWorker.getCurrWeek().updAssignments(new Task("Task","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project));
		testWorker.getCurrWeek().updAssignments(new Task("Task","Test",new ArrayList<Worker>(), new ArrayList<Worker>(), cal.getTime(),project));

		testWorker.getCurrWeek().updWorkTime(0, 5);
		testWorker.getCurrWeek().updWorkTime(1, 5);
		settings.updateDay();
		testWorker.getCurrWeek().updWorkTime(0, 5);
		testWorker.getCurrWeek().updWorkTime(1, 4);
		settings.updateDay();
		testWorker.getCurrWeek().updWorkTime(1, 9191);
		
		generateText(testWorker,0,0,settings);
	}
	
	
	/**
	 * Generates a text file consisting of what the worker has worked on from week x till y.
	 * @param worker
	 * @param from
	 * @param till
	 * @throws WorkerMissingTask
	 * @throws IOException
	 * @author s164166
	 * @throws InvalidDateRange 
	 */
	public static void generateText(Worker worker,int from, int till, Settings settings) throws WorkerMissingTask, IOException, InvalidDateRange
	{
		WorkWeek week;
		ArrayList<String> text = new ArrayList<String>();
		String fileName = worker.getName()+"#" + worker.getWorkID();
		text.add("Workname: " + fileName + " Full Name: " + worker.getName());
				
		Calendar date1 = (Calendar) settings.getStartupDate().clone();
		Calendar date2 = (Calendar) settings.getStartupDate().clone();
		date1.set(Calendar.DAY_OF_WEEK, 0);
		date2.set(Calendar.DAY_OF_WEEK, 6);
		date1.add(Calendar.DAY_OF_MONTH, 7*from);
		date2.add(Calendar.DAY_OF_MONTH, 6+7*till);
		text.add("From: " + date1.getTime() + " "  + " till: " + date2.getTime());
		if (from<0 || from>till)
			throw new InvalidDateRange("Invalid Date Range.");
		
		for (int weekCounter = from; weekCounter < till+1; weekCounter++)
		{
			text.add("Week: " + weekCounter);
			week = worker.getXweek(weekCounter);
			for (int day = 1; day<8;day++)
			{
				if (week.getCurrTaskAmm()	==	0)
				{
					text.add("\t--- No projects assigned this week ----");
					break;
				}	
				text.add(days[day-1] + "\t");
				for (int task = 0; task<week.getCurrTaskAmm();task++)
				{
						text.add("\t\tTask: " + week.getWorkTask(task).getName() + "#" + week.getWorkTask(task).getTaskID() + ". Hours worked: " + week.getTimeWorkedDay(day,task) +".");
				}
			}	
		}
		writeToFile(fileName ,text);
	}

	/**
	 * Writes a file with the file name and the following text.
	 * @param fileName
	 * @param text
	 * @throws IOException
	 */
	private static void writeToFile(String fileName, ArrayList<String> text) throws IOException {
		Path file = Paths.get(fileName + ".txt");
		Files.write(file, text, Charset.forName("UTF-8"));
	}
}
