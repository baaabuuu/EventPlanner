package Settings;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author s164166, s164147, s160902
 * This class is used for constants, such as which week since launch it is, which day of the week it is and so on.
 */
public class Settings 
{
	public final static int 	maxAssignments		=	20;
	public final static String 	applicationName		=	"Work Planner";
	public final static	Date 	startupDate 		=	new GregorianCalendar(2017, Calendar.APRIL, 10).getTime();
	private 	 static	int		weekNumber			=	0;
	private		 static int		day 				=	1;
	
	/**
	 * Added so we can advance weeks, 
	 * @author s164166
	 */
	public static void updateWeek()
	{
		weekNumber++;
	}
	
	/**
	 * Added so we can advance days, 
	 * @author s164166
	 */
	public static void updateDay()
	{
		if (day == 6)
			updateWeek();
		day = (day  % 7) + 1;
	}
	
	/**
	 * returns the current day of the week, 1 = monday etc.
	 * @author s164166
	 */
	public static int getDay()
	{
		return day;
	}
	
	/**
	 * Returns the current day.
	 * @author s164166
	 */
	public static int getWeekNumber()
	{
		return weekNumber;
	}
}

