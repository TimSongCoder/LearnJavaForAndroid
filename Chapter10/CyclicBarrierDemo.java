import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo{
	public static void main(String[] args){
		float[][] matrix = new float[3][3];
		int counter = 0;
		for(int row=0;row<matrix.length;row++){
			for(int col =0 ; col<matrix[0].length; col++){
				matrix[row][col] = counter++;
			}
		}
		
		dump(matrix);
		System.out.println();
		
		Solver solver = new Solver(matrix);
		System.out.println();
		dump(matrix);
	}
	
	static void dump(float[][] matrix){
		for(int row =0;row<matrix.length;row++){
			for(int col=0;col<matrix[0].length;col++){
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}
	}
}

class Solver{
	final int N;
	final float[][] data;
	final CyclicBarrier barrier;
	
	class WorkerTask implements Runnable{
		int myRow;
		boolean done = false;
		
		WorkerTask(int row){
			myRow = row;
		}
		
		boolean done(){
			return done;
		}
		
		void processRow(int myRow){
			System.out.println(Thread.currentThread() + " processing row: " + myRow);
			for(int i=0;i<data[myRow].length;i++){
				data[myRow][i]*=10;
			}
			done = true;
		}
		
		@Override
		public void run(){
			while(!done()){
				processRow(myRow);
				
				try{
					barrier.await();  // Not tripped till all parties wait at the barrier :)
				}catch(InterruptedException ie){
					ie.printStackTrace();
					return;
				}catch(BrokenBarrierException bbe){
					bbe.printStackTrace();
					return;
				}
			}
		}
	}
	
	public Solver(float[][] matrix){
		data = matrix;
		N = matrix.length;
		barrier = new CyclicBarrier(N, new Runnable(){
			@Override
			public void run(){
				mergeRows();  // execute when the barrier tripped.
			}
		});
		
		for(int i=0;i<N;i++){
			new Thread(new WorkerTask(i)).start();
		}
		
		waitUntilDone();
	}
	
	void mergeRows(){
		System.out.println(Thread.currentThread() + "merging simulating.");
		synchronized("abc"){
			"abc".notify();
		}
	}
	
	void waitUntilDone(){
		synchronized("abc"){
			try{
				System.out.println(Thread.currentThread() + " is waiting.");
				"abc".wait();
				System.out.println(Thread.currentThread() + " is notified.");
			}catch(InterruptedException ie){
				System.out.println(Thread.currentThread() + " is interrupted.");
			}
		}
	}
}

