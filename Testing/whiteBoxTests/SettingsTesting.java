package whiteBoxTests;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import Settings.Settings;

public class SettingsTesting 
{
	
	//Test whether days can be advanced
	// Author s164166
	@Test
	public void advanceDays()
	{
		int startDay = Settings.getDay();
		Settings.updateDay();
		assertTrue(startDay<Settings.getDay());
	}
	//Test whether weeks can be advanced, and if a week is advanced by 7 days, days should be equal to 1
	// Author s164166
	@Test
	public void checkThisWeekAgain()
	{
		int startDay = Settings.getDay();
		int startWeek = Settings.getWeekNumber();
		for (int i = 0; i<7; i++)
		{
			Settings.updateDay();
		}
		assertEquals(startDay,Settings.getDay());
		assertTrue(startWeek < Settings.getWeekNumber());
	}
}
