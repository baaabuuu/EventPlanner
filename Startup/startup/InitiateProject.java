package startup;

import companyDatabase.CompanyProjects;
import companyDatabase.CompanyWorkers;

public class InitiateProject{
	private CompanyProjects projDatabase	= new CompanyProjects();
	private CompanyWorkers	workDatabase	= new CompanyWorkers();
	
	
	public CompanyProjects getProjects(){
		return projDatabase;
	}
	
	public CompanyWorkers getWorkers(){
		return workDatabase;
	}
	//test
}
