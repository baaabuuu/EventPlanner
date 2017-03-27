package workerLogic;

import java.util.ArrayList;

public class Worker {
	public Worker(String _name, int _workID)
	{
		workID = _workID;
		name = _name;
	}
	private String name, workName;
	private int workID;
	private ArrayList<WorkWeek> WorkWeek = new ArrayList<WorkWeek>();
	
	public int getWorkID()
	{
		return workID;

	}
	public String getName()
	{
		return name;

	}
	
}
