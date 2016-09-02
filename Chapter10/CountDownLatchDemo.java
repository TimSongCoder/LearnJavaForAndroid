import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo{
	final static int NTHREADS = 3;
	
	public static void main(String[] args){
		final CountDownLatch startSignal = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(NTHREADS);
		
		Runnable runnable = new Runnable(){
			@Override
			public void run(){
				try{
					report("entered run()");
					startSignal.await(); // wait until count down to zero.
					
					report("doing work");
					Thread.sleep((int)(Math.random()*10000));
					doneSignal.countDown(); // decrement the count if not zero
				}catch(InterruptedException ie){
					System.err.println(ie);
				}
			}
			
			void report(String s){
				System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread() + ": " + s);
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		for(int i=0; i<NTHREADS; i++){
			executor.execute(runnable);
		}
		try{
			System.out.println("main thread doing something");
			Thread.sleep(1000);
			
			startSignal.countDown();
			
			System.out.println("main thread doing something else");
			
			doneSignal.await();  // wait till count down to be zero. main thread is waiting for the end.
			
			executor.shutdownNow();
			System.out.println("main thread's wait finished. That's the end.");
		}catch(InterruptedException ie){
			System.err.println(ie);
		}
	}
}