package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Round_Robin {

	private List<Process> processList = new ArrayList<>();
	public List<Process> promotionList = new ArrayList<>();
	private int Q = 0;
	private int time = 1;
	
	/**
	 * Load the RR with a process list
	 * @param List<Process> processes
	 */
	public Round_Robin(int Q, List<Process> processes) {
		this.Q = Q;
		this.processList = processes;
		
		Collections.sort(processList, new SortByArrival());
	}
	
	/**
	 * Add a process to the queue
	 * @param process
	 */
	public void addProcesses(List<Process> promList) {
		for (Process prom : promList) {
			processList.add(prom);
		}
		
		Collections.sort(this.processList, new SortByArrival());
		
	}
	
	/**
	 * Promote the process and remove it from the list
	 * @param process
	 * @return
	 */
	public void promoteProcess(Process process) {
		process.inceasePriority();
		
		processList.remove(process);
		
		if(ScheduleController.DEBUG) System.out.printf("\nPromoting process ID: %d\n\n", process.id);
		
		this.promotionList.add(process);
	}
	
	
	public void runForQ() {
		
		List<Process> activeProcesses = new ArrayList<>();
		
		for (Process process : processList) {
			if (process.arrival <= time) {
				activeProcesses.add(process);
			}
		}
		
		for (Process process : activeProcesses) {
			int runTime = 0;
			
			if (process.burst - this.Q <= 0) {
				runTime = process.burst;
				
				process.ranFor(runTime);
				
				processList.remove(process);
			} else {
				runTime = this.Q;
				process.ranFor(runTime);
				
				process.runCount++;
				
				if (process.runCount % 2 == 0) promoteProcess(process);
			}
			
			process.lastRun = runTime;
			print(process);
			
		}
		
		this.time += this.Q;
		
	}

	public boolean finished() {
		if (processList.isEmpty()) return true;
		return false;
	}
	
	public void print(Process process) {
		System.out.printf("ID: %d\t Arrival:%d\t Priority: %d\t Ran for: %d\t Remaining burst: %d\n",
				process.id, process.arrival, process.priority, process.lastRun, process.burst);
	}

}


class SortByArrival implements Comparator<Process> 
{ 
    /**
     * Used to order the process list by arrival time
     */
    public int compare(Process a, Process b) 
    { 
        return a.arrival - b.arrival; 
    } 
} 
