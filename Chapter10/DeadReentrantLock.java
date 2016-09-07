import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadReentrantLock{
	private static volatile boolean isADone = false;
	private static volatile boolean isBDone = false;
	
	public static void main(String[] args){
		final Lock lock1 = new ReentrantLock();
		final Lock lock2 = new ReentrantLock();
		ExecutorService es = Executors.newFixedThreadPool(2);
		Runnable r1 = new Runnable(){
			public void run(){
				lock1.lock();
				System.out.println(Thread.currentThread() + " Do something A: " + lock1);
				lock2.lock();
				System.out.println(Thread.currentThread() + " Do something B: " + lock2);
				lock1.unlock(); // for A
				lock2.unlock(); // for B
				
				isADone = true;
				
				if(isADone&&isBDone){
					es.shutdown();
				}
			}
		};
		es.submit(r1);
		
		Runnable r2 = new Runnable(){
			public void run(){
				lock2.lock();
				System.out.println(Thread.currentThread() + " Do something ONE: " + lock2);
				lock1.lock();
				System.out.println(Thread.currentThread() + " Do something TWO: " + lock1);
				lock2.unlock(); // for ONE
				lock1.unlock(); // for TWO
				
				isBDone = true;
				
				if(isADone&&isBDone){
					es.shutdown();
				}
			}
		};
		es.submit(r2);
	}
}