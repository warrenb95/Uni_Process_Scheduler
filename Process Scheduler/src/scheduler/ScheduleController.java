package scheduler;

import java.util.List;

public class ScheduleController {
	
	final static boolean DEBUG = true;
	
	
	public static void main(String args[]) {
		
//		if (DEBUG) System.out.println("IN MAIN \n");
		
		CSVHandler csvHandler = new CSVHandler();
		
		List<Process> allProcesses = csvHandler.loadCSV("process.csv");
		
//		if (DEBUG) System.out.println("DATA LOADED IN");
//		
//		if (DEBUG) {
//			for(Process p : allProcesses) {
//				System.out.printf("ID: %d ", p.id);
//				System.out.printf("Arrival: %d ", p.arrival);
//				System.out.printf("Burst: %d ", p.burst);
//				System.out.printf("Priority: %d%n", p.priority);
//			}
//		}
		
		Scheduler scheduler = new Scheduler();
		scheduler.loadScheduler(allProcesses);
		scheduler.LoadQueues();
		scheduler.run();
	}
	
	
	
}
