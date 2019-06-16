package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class First_Come_First_Serve {
	
	private List<Process> processList = new ArrayList<>();
	private int time = 0;
	
	/**
	 * Load the FCFS with the process list
	 * @param List<Process> fcfsList
	 */
	public First_Come_First_Serve(List<Process> fcfsList) {
		this.processList = fcfsList;
	}
	
	/**
	 * Add a List of processes to the queue
	 * @param process
	 */
	public void addProcesses(List<Process> promList) {
		for (Process prom : promList) {
			processList.add(prom);
		}
		
	}

	
	public void run() {
		
		List<Process> activeProcesses = new ArrayList<>();
		
		for (Process process : processList) {
			activeProcesses.add(process);
		}
		
		for (Process process : activeProcesses) {
			int runTime = 0;
			
			runTime = process.burst;
			process.ranFor(runTime);
			
			process.lastRun = runTime;
			
			print(process);
			
			processList.remove(process);
			
			}
		
	}

	public boolean finished() {
		if (processList.isEmpty()) return true;
		return false;
	}

	public void print(Process process) {
		System.out.printf("ID: %d\t Ran for: %d\t Priority: %d\t Ran for: %d\t Remaining burst: %d\n",
				process.id, process.burst, process.priority, process.lastRun, process.burst);
	}
}
