package whiteBoxTests;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
		assertTrue("Check that 1 day has been advanced have been advanced", startDay < settings.getDay());
		for (int i = 0; i<4; i++)
		{
			settings.updateDay();
		}
		//advancing form saturday till sunday
		assertFalse("Check that 1 week has not been advanced",settings.updateDay());
		//day 7 of the week (sunday, so we advanced from sunday)
		assertTrue("Check that weeks have been advanced",settings.updateDay());
		assertEquals("Check That the day index is the same as the start",startDay,settings.getDay());
	}
}
