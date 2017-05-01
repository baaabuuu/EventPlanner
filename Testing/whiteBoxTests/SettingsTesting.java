package whiteBoxTests;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import application.Settings;

public class SettingsTesting 
{
	
	Settings settings;
	
	@Before
	public void setup()
	{
		settings = new Settings();
	}
	
	//Test whether days can be advanced
	// Author s164166
	@Test
	public void advanceDays()
	{
		int startDay = settings.getDay();
		settings.updateDay();
		assertTrue("Check that days have been advanced",startDay<settings.getDay());
	}
	
	//Test whether weeks can be advanced, and if a week is advanced by 7 days, days should be equal to 1
	// Author s164166
	@Test
	public void advanceWeek()
	{
		int startDay = settings.getDay();
		int startWeek = settings.getWeekNumber();
		for (int i = 0; i<7; i++)
		{
			settings.updateDay();
		}
		assertEquals("Check That the day index is the same as the start",startDay,settings.getDay());
		assertTrue("Check that weeks have been advanced",startWeek < settings.getWeekNumber());
	}
	//Check that the hardcap of assignments is equal to 20.
	// Author s164166
	@Test
	public void maxAssignments()
	{
		assertEquals("Test that the max amount is 20",settings.getMaxAssignments(),20);
	}
}
