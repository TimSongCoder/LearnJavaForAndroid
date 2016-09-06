import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PC{
	public static void main(String[] args){
		final Lock lock = new ReentrantLock();
		final BlockingQueue<Character> bq = new ArrayBlockingQueue<Character>(26);
		final ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable producer = new Runnable(){
			public void run(){
				for(char ch='A';ch <= 'Z';ch++){
					lock.lock();
					try{
						while(!bq.offer(ch)){ // full and not insert
							lock.unlock();
							Thread.sleep(50);
							lock.lock();  // reacquire the lock, or reentrant
						}
						System.out.println(ch + " produced by " + Thread.currentThread());
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}finally{
						lock.unlock();
					}
				}
			}
		};
		executor.execute(producer);
		
		Runnable consumeRunnable = new Runnable(){
			public void run(){
				char ch = '\0';
				do{
					try{
						lock.lock();
						Character c;
						while((c = bq.poll()) == null){ // empty, no elements can be polled.
							lock.unlock();
							Thread.sleep(50);
							lock.lock();  // reentrant
						}
						ch = c.charValue();
						System.out.println(ch + " consumed by " + Thread.currentThread());
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}finally{
						lock.unlock();
					}
				}while(ch!='Z');
				executor.shutdownNow();
			}
		};
		executor.execute(consumeRunnable);
	}
}