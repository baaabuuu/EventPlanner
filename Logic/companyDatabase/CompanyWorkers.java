package companyDatabase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import workerLogic.Worker;
import workerLogic.WorkerNameError;
/**

 * This class contains info regarding who is working for the company.
 * @author s164166
 */
public class CompanyWorkers {
	private static ArrayList<Worker> companyWorkers = new ArrayList<Worker>();
	
	/**
	 * Adds a new worker
	 * @author s160902
	 * @throws WorkerNameError 
	 */
	public static void addNewWorker(String name) throws WorkerNameError
	{
		Worker newWorker = new Worker(name);
		addWorker(newWorker);
	}
	/**
	 * Adds a worker to the company database.
	 * @author s164166
	 * @param worker
	 */
	public static void addWorker(Worker worker)
	{
		worker.setWorkID(companyWorkers.size());
		companyWorkers.add(worker);
	}
	/**
	 * removes a worker from the company database using a specific object.
	 * @author s164166
	 * @param worker
	 */
	public static void removeWorker(Worker worker)
	{
		companyWorkers.remove(worker);
	}
	/**
	 * removes a worker from the company database using the ID.
	 * @author s164166
	 * @param index, the index.
	 */
	public static void removeWorker(int index)
	{
		companyWorkers.remove(index);
	}
	/**
	 * gets a specific worker by their index number.
	 * @author s164166
	 * @param index, the index.
	 */
	public static Worker getWorker(int index)
	{
		return companyWorkers.get(index);
	}
	/**
	 * Returns a list of workers that can work.
	 * @author s164166
	 * @return the workers than can work on a task at the moment
	 */
	public static List<Worker> getElligbleWorkers()
	{
		return companyWorkers.stream().filter(Worker -> Worker.isAvailableCurrWeek()).collect(Collectors.toList());		
	}
	/**
	 * Returns a list of workers.
	 * @author s164166
	 * @return the workers than can work on a task at the moment
	 */
	public static List<Worker> getAllWorkers()
	{
		return companyWorkers;		
	}

}
