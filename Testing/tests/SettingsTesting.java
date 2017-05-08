package tests;
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
		assertTrue("Check that 1 day has been advanced have been advanced", startDay < settings.getDay());
		for (int i = 0; i<6; i++)
		{
			settings.updateDay();
		}
		assertEquals("Check That the day index is the same as the start",startDay,settings.getDay());
		assertEquals("Check that the week number is no1 t",1,settings.getWeekNumber());
	}
}
