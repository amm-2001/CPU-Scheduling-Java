
public class Process {
	int processID; 
    int burstTime; 
    int arrivalTime;
    int priority;
    public Process(int pid, int art,int bt , int pri)
    {
        this.processID = pid;
        this.burstTime = bt;
        this.arrivalTime = art;
        this.priority = pri;
    }
}
