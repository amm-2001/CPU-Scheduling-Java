public class SRTF
{
    static void findWaitingTime(Process proc[], int wait_time[])
    {
        int copy_bt[] = new int[proc.length];
        for (int i = 0; i < proc.length; i++)
        	copy_bt[i] = proc[i].burstTime;
        
        int complete = 0, current_time = 0, minimum = Integer.MAX_VALUE , shortest = 0, finish_time ,pastID=0;
        boolean check = false;
        
        while (complete != proc.length) {
            for (int j =0; j < proc.length; j++)
            { 
                if ((proc[j].arrivalTime <= current_time)&& (copy_bt[j] < minimum) && (copy_bt[j] > 0)) {
                    minimum = copy_bt[j];
                    shortest = j;
                    check = true;
				}
            }
        
            
            if(!check){
                current_time++;
                continue;
                }
           
                copy_bt[shortest]--;
                minimum = copy_bt[shortest];
            if (minimum == 0) {
            	minimum = Integer.MAX_VALUE; // maximum possible number
                complete++;
                check = false;
                finish_time = current_time + 1;
                 wait_time[shortest] = finish_time - proc[shortest].burstTime - proc[shortest].arrivalTime;
                if (wait_time[shortest] < 0)
                    wait_time[shortest] = 0;
            }
			if (proc[shortest].processID != pastID) {
			  System.out.print(current_time + "-" + "|P" + proc[shortest].processID+"|" + "-");
				pastID = proc[shortest].processID;
			}
            current_time++;
            }
		System.out.println(current_time);
}
    
    public static void find_complete_time(Process Proc[], int wait_time[], int ct[]) {
		for (int i = 0; i < Proc.length; i++) {
			ct[i] = Proc[i].burstTime + wait_time[i];
		}

	}
	
	public static void find_average_time(Process Proc[], int wait_time[], int ct[], int total_wt, int total_ct) {
		findWaitingTime(Proc, wait_time);
		find_complete_time(Proc, wait_time, ct);
		
		for (int i = 0; i < wait_time.length; i++) {
			total_ct = total_ct + ct[i];
			total_wt+=wait_time[i];
		}
        System.out.println("Average Waiting Time: "+(float)total_wt / (float)Proc.length);
        System.out.println("Average Completion Time: "+(float)total_ct / (float)Proc.length);
	}
    
}