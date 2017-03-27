package companyDatabase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import workerLogic.Worker;
/**
 * 
 * @author s164166
 * This class is used to hold the company info regarding who is working for the company.
 * 
 */
public class CompanyWorkers {
	private static ArrayList<Worker> companyWorkers = new ArrayList<Worker>();
	/**
	 * Adds a worker to the company database.
	 * @author s164166
	 * @param worker
	 */
	public void addWorker(Worker worker)
	{
		worker.setWorkID(companyWorkers.size());
		companyWorkers.add(worker);
	}
	/**
	 * removes a worker from the company database using a specific object.
	 * @author s164166
	 * @param worker
	 */
	public void removeWorker(Worker worker)
	{
		companyWorkers.remove(worker);
	}
	/**
	 * removes a worker from the company database using the ID.
	 * @author s164166
	 * @param index, the index.
	 */
	public void removeWorker(int index)
	{
		companyWorkers.remove(index);
	}
	
	/**
	 * gets a specific worker by their index number.
	 * @author s164166
	 * @param index, the index.
	 */
	public Worker getWorker(int index)
	{
		return companyWorkers.get(index);
	}
	
	/**
	 * Returns a list of workers that can work.
	 * @author s164166
	 * @return the workers than can work on a task at the moment
	 */
	public List<Worker> getElligbleWorkers()
	{
		return companyWorkers.stream().filter(worker -> worker.isLegalWorker()).collect(Collectors.toList());		
	}
}
