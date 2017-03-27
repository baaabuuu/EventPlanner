package Settings;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author s164166, s164147, s160902
 * This class is used for constants, we will be using it to store constants such as maximum projects a worker can be on.
 * This will make it easier for us to do tasks in the future. 
 */
public class settings 
{
	public final static int maxAssignments		 	=	20;
	public final static String applicationName		=	"Work Planner";
	public final static	Date startupDate 			=	new GregorianCalendar(2017, Calendar.APRIL, 10).getTime();
	private int weekNumber							=	0;
	
	
	
	/**
	 * Added so we can advance weeks, 
	 */
	public void updateWeek()
	{
		weekNumber++;
	}
	
	public int getWeekNumber()
	{
		return weekNumber;
	}
}

