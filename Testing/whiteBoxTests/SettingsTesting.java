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
	
	//Test whether days and weeks can be advanced using the advanceDays method
	// Author s164166
	@Test
	public void advanceDays()
	{
		int startDay = settings.getDay();
		settings.updateDay();
		assertTrue("Check that 1 dy has been advanced have been advanced",startDay<settings.getDay());
		int startWeek = settings.getWeekNumber();
		for (int i = 0; i<6; i++)
		{
			settings.updateDay();
		}
		assertEquals("Check That the day index is the same as the start",startDay,settings.getDay());
		assertTrue("Check that weeks have been advanced",startWeek < settings.getWeekNumber());
	}
}
