import java.lang.reflect.Array;


public class SJF {
	static int time = 0;
	int[][] process;
	int amt = 0;
	int turnaround = 0;
	int response = 0;
	
	public SJF(int[][] process){
		this.process = process;
	}
	public void CalculateTime(){
		if(process[0][0] != 0){   //check if first job arrival time is at 0
			time = time + process[0][0];
		}
		for(int i=0;i<process.length;i++){ 					//still need to check if time less than arrival time.
			int count = 0;
			if(i == process.length-1){
				response = response + (time-process[i][0]);
				run(process[i][1]);
				turnaround = turnaround + (time-process[i][0]);
			}
			else{
				for(int j=0+i;j<process.length-1;j++){ 
					if(process[j][0] == process[j+1][0]){ //check if arrival time is the same
						count++; 						  // increment count if it is
					}			
				}
				int currentIndex = 0+i;
				for(int j=1;j<=count;j++){ //for loop to compare the runtime to find shortest runtime
					if(process[j][1] < process[currentIndex][1]){
						currentIndex = j;
					}
				}
				response = response + (time-process[currentIndex][0]);
				run(process[currentIndex][1]);			//run the job.
				turnaround = turnaround + (time-process[currentIndex][0]);
				process = switchJob(currentIndex,process); 	//switch job completed with first thing.
			}
		}
		System.out.println("The turnaround time for SJF is " + turnaround/process.length);
		System.out.println("The response time for SJF is " + response/process.length);
		
	}
	public void run(int job){
		time = time + job;
		
	}
	public int[][] switchJob(int index, int[][] array){
		int tempArrival = array[amt][0];
		int tempRunTime = array[amt][1];
		array[amt][0] = array[index][0];
		array[amt][1] = array[index][1];
		array[index][0] = tempArrival;
		array[index][1] = tempRunTime;
		amt++;
		return array;
	}
}
