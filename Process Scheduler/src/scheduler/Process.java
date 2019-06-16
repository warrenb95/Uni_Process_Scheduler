package scheduler;

public class Process {

	public int id;
	public int arrival;
	public int burst;
	public int priority; // 1 being highest
	public boolean isComplete = false;
	public int runCount = 0;
	public int lastRun = 0;
	
	/**
	 * Process Constructor
	 * @param String[] params
	 */
	Process(String[] params){
		this.id = Integer.parseInt(params[0]);
		this.arrival = Integer.parseInt(params[1]);
		this.burst = Integer.parseInt(params[2]);
		this.priority = Integer.parseInt(params[3]);
	}
	
	/**
	 * Decrement the burst by time
	 * @param int time
	 */
	public void ranFor(int time ) {
		this.burst -= time;
	}
	
	/**
	 * Increase the priority by +1
	 */
	public void inceasePriority() {
		if (this.priority > 1) {
			this.priority--;
		}
	}
	
	/**
	 * Has the process completed execution?
	 * @return
	 */
	public boolean processFinished(){
		return this.burst <= 0 ? true : false;
	}
	
}
