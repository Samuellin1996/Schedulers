import java.util.ArrayList;
import java.util.Scanner;


public class Scheduler {
	static int Sjf_Time = 0;
	static int Stcf_Time = 0;
	static int[][] processTime;
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of job: ");
		int job = input.nextInt();
		processTime = new int[job][2];
		for(int i=0;i<job;i++){
			System.out.println("Enter arrival Time: ");
			processTime[i][0] = input.nextInt();
			System.out.println("Enter run Time: ");
			processTime[i][1] = input.nextInt();
		}
		SJF  test = new SJF(processTime);
		test.CalculateTime();
		STCF test1 = new STCF(processTime);
		test1.CalculateTime();
		input.close();
	}

}
