import java.util.Arrays;   
public class prioraty {
	static void findWaitingTime(Process proc[], int wait_time[]) {
		int copy_ID[] = new int[proc.length];
		int copy_pri[] = new int[proc.length];
		int copy_bt[] = new int[proc.length];
		for (int i = 0; i < proc.length; i++) {
			copy_ID[i] = proc[i].processID;
			copy_bt[i] = proc[i].burstTime;
			copy_pri[i]= proc[i].priority;
		}
		int complete = 0, time = 0, finish_time, max_pri =5, index = 0;
		 while (complete < proc.length) {
			 for (int j = 0; j < proc.length; j++) { 
				 if (max_pri > copy_pri[j]) { 
					 max_pri = copy_pri[j];
					 index = j;  }
			 }
			 
			 System.out.print(time);
			 while (copy_bt[index] != 0) {
	                copy_bt[index]--;
	                time++;
	            }
            if (copy_bt[index] == 0) {
                complete++;
                finish_time = time ;
                copy_pri[index] = 6;
                max_pri =5;
                
                System.out.print("-" + "|P" +  proc[index].processID+"|" + "-");
                
                
                wait_time[index] = finish_time - proc[index].burstTime;
                if (wait_time[index] < 0)
                    wait_time[index] = 0;
            }            
		 }
         System.out.println(time);

}
	
	public static void find_complete_time(Process Proc[], int wait_time[], int ct[]) {
		for (int i = 0; i < Proc.length; i++) {
			ct[i] = Proc[i].burstTime + wait_time[i];
		}
	}
	
	public static void find_average_time(Process Proc[], int wait_time[], int ct[], int total_wt, int total_ct) {
		
		findWaitingTime(Proc, wait_time);
		
		find_complete_time(Proc, wait_time, ct);
		
		for (int i = 0; i < Proc.length; i++) {
			total_wt += wait_time[i];
			total_ct += ct[i];
		}
		System.out.println("Average Waiting Time: "+(float)total_wt / (float)Proc.length);
		System.out.println("Average Completion Time: "+(float)total_ct / (float)Proc.length );
	}
}