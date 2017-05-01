package application;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author s164166, s164147, s160902
 * This class is used for constants, such as which week since launch it is, which day of the week it is and so on.
 */
public class Settings 
{
	private final int 	maxAssignments		=	20;
	private final String applicationName	=	"Work Planner";
	private final Date 	startupDate 		=	new GregorianCalendar(2017, Calendar.APRIL, 10).getTime();
	private 	 int	weekNumber			=	0;
	private		 int	day 				=	1;
	
	
	
	/**
	 * Returns the startup date of the application
	 * @author s164166
	 * @return date
	 */
	public Date getStartupDate()
	{
		return startupDate;
	}
	
	
	/**
	 * Returns the name of the application
	 * @author s164166
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
	public void updateWeek()
	{
		weekNumber++;
	}
	
	/**
	 * Added so we can advance days, 
	 * @author s164166
	 */
	public void updateDay()
	{
		if (day == 6)
			updateWeek();
		day = (day  % 7) + 1;
	}
	
	/**
	 * returns the current day of the week, 1 = monday etc.
	 * @author s164166
	 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 * Returns the current day.
	 * @author s164166
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

