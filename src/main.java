import java.io.*;
import java.util.Scanner;

public class main {

	// Count how much lines in text file
	public static long countLineBufferedReader(File file1) {

		long lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
			while (reader.readLine() != null)
				lines++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;

	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter The Name of SRTF Text File");
		String S = scan.nextLine();
		File file1 = new File(S);
		Scanner sc = null;
		int NumberJobs_SRTF = (int) countLineBufferedReader(file1);
		try {
			sc = new Scanner(file1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int x = 0;
		// process (process ID , arrivalTime , Burst Time , priority)
		// input format (PID,AT,BT (AT: Arrival Time, BT: Burst Time))
		Process proc_SRTF[] = new Process[NumberJobs_SRTF];
		while (sc.hasNext()) {
			String scData = sc.next();
			String dataList[] = scData.split(",");
			// process (process ID , arrivalTime , Burst Time , priority)
			proc_SRTF[x++] = new Process(Integer.parseInt(dataList[0]), Integer.parseInt(dataList[1]),Integer.parseInt(dataList[2]), 1);
			
		}

		// process (process ID , arrivalTime , Burst Time , priority)
		// input format ( job(id)
		// burst Time ) Quantum Number
		System.out.println("Enter The Name of Round Robin Text File");
		String Ss = scan.nextLine();
		File file2 = new File(Ss);
		Scanner sc_RR = null;
		int NumberJobs_RR = (int) (countLineBufferedReader(file2) / 2);
		try {
			sc_RR = new Scanner(file2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Enter The Quantum Number");
		int Quantum = scan.nextInt();
		int i = 0;
		int ID_RR = 1;
		Process proc_RR[] = new Process[NumberJobs_RR];
		while (sc_RR.hasNext()) {
			// skip one line
			String data = sc_RR.next();
			String scData = sc_RR.next();
			int BT_RR = Integer.parseInt(scData);
			proc_RR[i++] = new Process(ID_RR++, 0, BT_RR, 1);
			
		}
		// process (process ID , arrivalTime , Burst Time , priority)
		Scanner scanPR = new Scanner(System.in);
		System.out.println("Enter The Name of Priority Text File");
		String ScanerP = scanPR.nextLine();
		File file3 = new File(ScanerP);
		int NumberJobs_PR = (int) (countLineBufferedReader(file3) / 2);
		Scanner sc_PR = null;
		try {
			sc_PR = new Scanner(file3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int ID_PR = 1;
		int index = 0;
		Process proc_PR[] = new Process[NumberJobs_PR];
		while (sc_PR.hasNext()) {
			// skip one line
			String data = sc_PR.next();
			String scData = sc_PR.next();
			String DataList[] = scData.split(",");
			// process (process ID , arrivalTime , Burst Time , priority)
			proc_PR[index++] = new Process(ID_PR++,0, Integer.parseInt(DataList[0]), Integer.parseInt(DataList[1]));
			
		}

		int wt_SRTF[] = new int[NumberJobs_SRTF];
		int wt_Priority[] = new int[NumberJobs_PR];
		int wt_RR[] = new int[NumberJobs_RR];

		int ct_SRTF[] = new int[NumberJobs_SRTF];
		int ct_Priority[] = new int[NumberJobs_PR];
		int ct_RR[] = new int[NumberJobs_RR];

		int total_wt_SRTF = 0, total_ct_SRTF = 0;
		int total_wt_Priority = 0, total_ct_Priority = 0;
		int total_wt_RR = 0, total_ct_RR = 0;
		scanPR.close();
        System.out.println();
		System.out.println("SRTF:");
		System.out.println("SRTF Gantt Chart:");
		SRTF.find_average_time(proc_SRTF, wt_SRTF, ct_SRTF, total_wt_SRTF, total_ct_SRTF);
		System.out.println();
		System.out.println();
		System.out.println("Round Robin:");
		System.out.println("RR Gantt Chart:");
		RR.find_average_time(proc_RR, wt_RR, ct_RR, Quantum, total_wt_RR, total_ct_RR);
		System.out.println();
		System.out.println();
        System.out.println("Priority:");
 		System.out.println("Priority Gantt Chart:");
		prioraty.find_average_time(proc_PR, wt_Priority, ct_Priority, total_wt_Priority, total_ct_Priority);

		

	}
}
