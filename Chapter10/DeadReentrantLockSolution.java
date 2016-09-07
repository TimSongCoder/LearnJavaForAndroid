import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Not deadlock, but with potential task-skip because of single acquiring attempt.
public class DeadReentrantLockSolution{
	private static volatile boolean isADone = false;
	private static volatile boolean isBDone = false;
	
	public static void main(String[] args){
		final Lock lock1 = new ReentrantLock();
		final Lock lock2 = new ReentrantLock();
		ExecutorService es = Executors.newFixedThreadPool(2);
		Runnable r1 = new Runnable(){
			public void run(){
				if(lock1.tryLock()){
					try{
						System.out.println(Thread.currentThread() + " Do something A: " + lock1);
						if(lock2.tryLock()){
							try{
								System.out.println(Thread.currentThread() + " Do something B: " + lock2);
							}finally{
								lock2.unlock();
							}
						}
					}finally{
						lock1.unlock();
					}
				}
				
				isADone = true;
				
				if(isADone&&isBDone){
					es.shutdown();
				}
			}
		};
		es.submit(r1);
		
		Runnable r2 = new Runnable(){
			public void run(){
				if(lock2.tryLock()){
					try{
						System.out.println(Thread.currentThread() + " Do something ONE: " + lock2);
						if(lock1.tryLock()){
							try{
								System.out.println(Thread.currentThread() + " Do something TWO: " + lock1);
							}finally{
								lock1.unlock();
							}
						}
					}finally{
						lock2.unlock();
					}
				}
				
				isBDone = true;
				
				if(isADone&&isBDone){
					es.shutdown();
				}
			}
		};
		es.submit(r2);
	}
}