
public class STCF {
	static int time = 0;
	int[][] process;
	int turnaround = 0;
	int response = 0;
	int amt = 0;
	
	public STCF(int[][] process){
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
					if(time >= process[j][0]&& process[j][0] == process[j+1][0]){ //check if job has arrived
						count++; 						  // increment count if it has
					}
					else if(time > process[j][0]){
						count++;
					}
				}
				//System.out.println(count);
				int currentIndex = 0+i;
				for(int j=1;j<=count;j++){ //for loop to compare the runtime to find shortest runtime
					if(process[j][1] < process[currentIndex][1]){
						currentIndex = j;
					}
				}
				if(process.length == count+1){  //check if arrival time (after the smaller arrival time) is less then runtime
					run(process[currentIndex][1]);
					turnaround = turnaround + (time-process[currentIndex][0]);
					process = switchJob(currentIndex,process);
				}
				else if(process.length != count+1 && process[count+1][0] < process[currentIndex][1]){
					if(process.length > count+1){
						response = response + (time-process[currentIndex][0]);
						run(process[currentIndex][1]);			//run the job.
						turnaround = turnaround + (time-process[currentIndex][0]);
						process = switchJob(currentIndex,process); 	//switch job completed with first thing.
					}
					else{
						response = response + (time-process[currentIndex][0]);
						time = time + (process[currentIndex][1] - process[count+1][0]); //run till next arrival time to check for shortest.
						process[currentIndex][1] = process[currentIndex][1] - process[count+1][0]; //update runtime that is still required.
						i--; //in order to rerun loop it since job hasn't finished.
					}
				}
				
			}
		}
		System.out.println("The turnaround time for STCF is " + turnaround/process.length);
		System.out.println("The response time for STCF is " + response/process.length);
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
