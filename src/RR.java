
public class RR {
	public static void findWaitingTime(Process Proc[], int Wt[], int quantum) {
		int remaining_BT[] = new int[Proc.length];
		int copy_ID[]=new int[Proc.length];
		for (int i = 0; i < Proc.length; i++) {
			remaining_BT[i] = Proc[i].burstTime;
			copy_ID[i] = Proc[i].processID;
		}
		int time = 0;

		while (true) {
			boolean finished = true;
			for (int i = 0; i < Proc.length; i++) {
				if (remaining_BT[i] > 0) {
					finished = false;
					if (remaining_BT[i] > quantum) {
						System.out.print(time);
						time += quantum;
						remaining_BT[i] -= quantum;
					} 
					else {
						System.out.print(time);
						time += remaining_BT[i];
						Wt[i] = time - Proc[i].burstTime;

						remaining_BT[i] = 0;

					}
	
						  System.out.print("-" + "|P" + Proc[i].processID+"|" + "-");
							
			            
					
			
				}
                
			}
			if (finished) {
				break;
			}
			
		}
		System.out.println(time);

	}
	
	public static void find_complete_time(Process Proc[], int wt[], int ct[]) {
		for (int i = 0; i < Proc.length; i++) {
			ct[i] = Proc[i].burstTime + wt[i];
		}
	}
	
	public static void find_average_time(Process Proc[], int wt[], int ct[], int quantum, int total_wt, int total_ct) {
		
		findWaitingTime(Proc, wt, quantum);
		
		find_complete_time(Proc, wt, ct);
		
		for (int i = 0; i < Proc.length; i++) {
			total_wt += wt[i];
			total_ct += ct[i];
		}
		System.out.println("Average Waiting Time: "+(float)total_wt / (float)Proc.length);
		System.out.println("Average Completion Time: "+(float)total_ct / (float)Proc.length);

	}
}
