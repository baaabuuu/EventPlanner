package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author s164166, s164147, s160902
 * This class is used for constants, such as which week since launch it is, which day of the week it is and so on.
 */
public class Settings 
{
	private	int					maxAssignments			=	20;
	private	String				applicationName			=	"Work Planner";
	private	GregorianCalendar	startupDate 			=	new GregorianCalendar(2004, Calendar.APRIL, 10);
	private	int					weekNumber				=	0;
	private	int					day 					=	1;
	private	GregorianCalendar	currDate				=	new GregorianCalendar(2004, Calendar.APRIL, 10);
		
	public GregorianCalendar getCurrDate()
	{
		return currDate;
	}
	
	
	/**
	 * Returns the startup date of the application
	 * @author s160902
	 * @return date
	 */
	public GregorianCalendar getStartupDate()
	{
		return startupDate;
	}
	
	
	/**
	 * Returns the name of the application
	 * @author s160902
	 * @return string
	 */
	public String getAppName()
	{
		return applicationName;
	}
	
	/**
	 * Added so we can advance weeks, 
	 * @author s164166
	 */
	private void updateWeek()
	{
		weekNumber++;
	}
	
	/**
	 * Added so we can advance days, 
	 * @author s164166
	 */
	public boolean updateDay()
	{
		if (day == 6)
			updateWeek();
		day = (day  % 7) + 1;
		currDate.add(Calendar.DAY_OF_MONTH, 1);
		return day == 1 ? true : false;
	}
	
	/**
	 * returns the current day of the week, 1 = monday etc.
	 * @author s164147
	 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 * Returns the current day.
	 * @author s164147
	 */
	public int getWeekNumber()
	{
		return weekNumber;
	}

	/**
	 * Returns the maximum amount of assignments a worker can have.
	 * @author s164166
	 * @return
	 */
	public int getMaxAssignments() {
		return maxAssignments;
	}
	
}

