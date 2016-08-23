import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueDemo{
	public static void main(String[] args){
		Queue<Integer> qi = new PriorityQueue<Integer>(new Comparator<Integer>(){
			public int compare(Integer e1, Integer e2){
				return e2 - e1; //  for descending order.
			}
		});
		for(int i=0; i<15; i++){
			qi.add((int)(Math.random()*100));  // Randomly generated a integer range from 0 to 99 inclusively.
		}
		while(!qi.isEmpty()){
			System.out.print(qi.poll() + " ");
		}
		System.out.println();
	}
}