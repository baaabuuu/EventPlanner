package application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkOutput {
	
	private	final 	String[] days = {"Monday\t","Tuesday\t","Wednessday","Thursday","Friday\t","Saturday","Sunday\t"};

	
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
	public  void generateText(Worker worker,int from, int till, Settings settings) throws WorkerMissingTask, IOException, InvalidDateRange
	{
		if (from<0 || from>till)
			throw new InvalidDateRange("Invalid Date Range.");
		WorkWeek week;
		ArrayList<String> text = new ArrayList<String>();
		String fileName = worker.getWorkName()+"#" + worker.getWorkID();
		text.add("Workname: " + fileName + " Full Name: " + worker.getName());
				
		Calendar date1 = (Calendar) settings.getStartupDate().clone();
		Calendar date2 = (Calendar) settings.getStartupDate().clone();
		date1.set(Calendar.DAY_OF_WEEK, 0);
		date2.set(Calendar.DAY_OF_WEEK, 6);
		date1.add(Calendar.DAY_OF_MONTH, 7*from);
		date2.add(Calendar.DAY_OF_MONTH, 6+7*till);
		date1.set(Calendar.HOUR_OF_DAY,0);
		date1.set(Calendar.HOUR_OF_DAY,23);
		
		text.add("From: " + date1.getTime() + " "  + " till: " + date2.getTime());

		for (int weekCounter = from; weekCounter < till+1; weekCounter++)
		{
			text.add("Week: " + weekCounter);
			week = worker.getWeekFromStart(weekCounter);
			for (int day = 1; day < 8;day++)
			{
				if (week.getCurrTaskAmm()	==	0)
				{
					text.add("\t--- No projects assigned this week ----");
					break;
				}
				text.add(days[day-1] + "\t");
				for (int task = 0; task < week.getCurrTaskAmm();task++)
				{
						text.add("\t\tProject: " + week.getWorkTask(task).getProject().getName() + "#" + week.getWorkTask(task).getProject().getID() + " Task: " + week.getWorkTask(task).getName() + "#" + week.getWorkTask(task).getTaskID() + ". Hours worked: " + week.getTimeWorkedDay(day,task) +".");
				}
			}
			if (week.getHelpedTaskCount() > 0)
			{
				
				text.add("\t\t----- HELPED TASKS -----");
				for (int i = 0; i < week.getHelpedTasks().size(); i++)
				{
					text.add("\t\tProject: " + week.getHelpedTasks().get(i).getProject().getName() + "#" + week.getHelpedTasks().get(i).getProject().getID() + " Task: " + week.getHelpedTasks().get(i).getName() + "#" + week.getHelpedTasks().get(i).getTaskID() + ". Hours worked: " + week.getHelpedTasksTime().get(i) +".");
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
	private  void writeToFile(String fileName, ArrayList<String> text) throws IOException {
		Path file = Paths.get("WorkerInfo\\" + fileName + ".txt");
		Files.write(file, text, Charset.forName("UTF-8"));
	}
}
