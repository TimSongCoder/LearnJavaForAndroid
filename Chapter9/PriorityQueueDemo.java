import java.util.Queue;
import java.util.PriorityQueue;

public class PriorityQueueDemo{
	public static void main(String[] args){
		Queue<Integer> qi = new PriorityQueue<Integer>();
		for(int i=0; i<15; i++){
			qi.add((int)(Math.random()*100));  // Randomly generated a integer range from 0 to 99 inclusively.
		}
		while(!qi.isEmpty()){
			System.out.print(qi.poll() + " ");
		}
		System.out.println();
	}
}