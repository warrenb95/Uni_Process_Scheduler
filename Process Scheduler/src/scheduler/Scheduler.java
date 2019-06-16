package scheduler;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Scheduler {
	
	private List<Process> fcfsList = new ArrayList<>();
	private List<Process> priorityOneList =  new ArrayList<>();
	private List<Process> priorityTwoList =  new ArrayList<>();
	private List<Process> priorityThreeList =  new ArrayList<>();
	
	private int Q1 = 5;
	private int Q2 = 4;
	private int Q3 = 3;
	
	private Round_Robin p1rr;
	private Round_Robin p2rr;
	private Round_Robin p3rr;
	private First_Come_First_Serve fcfs;
	
	/**
	 * Loads the scheduler with all of the processes
	 * @param List<Process> allProcesses
	 */
	public void loadScheduler(List<Process> allProcesses) {
		
		for (Process process : allProcesses) {
			
			switch (process.priority) {
			
			case 1:
				priorityOneList.add(process);
				break;
				
			case 2:
				priorityTwoList.add(process);
				break;
				
			case 3:
				priorityThreeList.add(process);
				break;
			
			}
			
		}
		
		// Print processes for each priority for DEBUG
		if (ScheduleController.DEBUG) {
			System.out.print("\nPriority 1:\n");
			for (Process process : priorityOneList) {
				System.out.printf("ID: %d\t Arrival: %d\t Burst: %d\n",
						process.id, process.arrival, process.burst);
			}
			System.out.print("\nPriority 2:\n");
			for (Process process : priorityTwoList) {
				System.out.printf("ID: %d\t Arrival: %d\t Burst: %d\n",
						process.id, process.arrival, process.burst);
			}
			System.out.print("\nPriority 3:\n");
			for (Process process : priorityThreeList) {
				System.out.printf("ID: %d\t Arrival: %d\t Burst: %d\n",
						process.id, process.arrival, process.burst);
			}
			System.out.println();
		}
		
		
	}
	
	/**
	 * Load all of the queues from all the processes
	 */
	public void LoadQueues() {
		
		p1rr = new Round_Robin(Q1, priorityOneList);
		p2rr = new Round_Robin(Q2, priorityTwoList);
		p3rr = new Round_Robin(Q3, priorityThreeList);
		fcfs = new First_Come_First_Serve(fcfsList);
		
	}
	
	/**
	 * Run all of the queues for Q time 1
	 */
	public void run() {
		
		while (true) {
			
			System.out.println("First Come First Serve START");
			fcfs.run();
			System.out.println("First Come First Serve END\n\n");
			
			System.out.println("Priority 1 Round Robin START");
			p1rr.runForQ();
			System.out.println("Priority 1 Round Robin END\n\n");
			
			System.out.println("Priority 2 Round Robin START");
			p2rr.runForQ();
			System.out.println("Priority 2 Round Robin END\n\n");
			
			System.out.println("Priority 3 Round Robin START");
			p3rr.runForQ();
			System.out.println("Priority 3 Round Robin END\n\n");
			
			fcfs.addProcesses(p1rr.promotionList);
			p1rr.promotionList.clear();
			p1rr.addProcesses(p2rr.promotionList);
			p2rr.promotionList.clear();
			p2rr.addProcesses(p3rr.promotionList);
			p3rr.promotionList.clear();
			
			if (p1rr.finished() && p2rr.finished() && p3rr.finished() && fcfs.finished()) break;
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n\nPress enter to continue...");
			scanner.nextLine();
			
		}
		
	}
	
}
