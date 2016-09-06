import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PC2{
	public static void main(String[] args){
		Shared s = new Shared();
		
		new Consumer(s).start();
		
		// Simulate the delay and verify the Lock, Condition mechanics.
		try{
			Thread.sleep(1000);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		
		new Producer(s).start();
		
		System.out.println(Thread.currentThread() + " is ending...");
	}
}

class Shared{
	/* Fields c and available are volatile so that writes to them are visible to the various threads.
	* Fields lock and condition are final so that there initial values are visible to the various threads.
	* The Java memory model promises that, after a final field has been initialized, any thread will see the same
	* correct value.
	*/
	private volatile char c;
	private volatile boolean available;
	private final ReentrantLock lock;
	private final Condition condition;
	
	Shared(){
		c = '\u0000';
		available = false;
		lock = new ReentrantLock();
		condition = lock.newCondition(); // bound
		System.out.println("Condition initialized: " + condition);
	}
	
	Lock getLock(){
		return lock;
	}
	
	char getSharedChar(){
		System.out.println("getSharedChar: " + lock);
		lock.lock();
		try{
			while(!available){
				try{
					condition.await();  // release the lock and wait.
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}	
			}
			available = false;
			condition.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
			return c;  // where volatile is neccesary, the field is modified and accessed from multiple threads. It needs visibility.
		}
	}
	
	void setSharedChar(char c){
		System.out.println("setSharedChar: " + lock + lock.getHoldCount());
		lock.lock();
		try{
			while(available){
				try{
					condition.await();  // field c is available for getting. wait and release the lock.
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
			this.c = c;
			available = true;
			condition.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("Before setSharedChar unlock: " + lock + lock.getHoldCount()); // count 2
			lock.unlock();
			System.out.println("After setSharedChar unlock: " + lock + lock.getHoldCount()); // count 1
			// The lock is still locked by ProducerThread because of holder count not be zero.
		}
	}
}

class Producer extends Thread{
	// field lock and shared are final because it's initialized on the main thread and accessed on the other thread.
	private final ReentrantLock lock;
	
	private final Shared shared;
	
	Producer(Shared s){
		shared = s;
		lock = (ReentrantLock)s.getLock();
		setName("ProducerThread");
	}
	
	@Override
	public void run(){
		System.out.println("Producer is starting...");
		System.out.println("Producer: " + lock + lock.getHoldCount());
		
		for(char ch = 'A'; ch<='Z';ch++){
			lock.lock(); // repeatedly called.
			shared.setSharedChar(ch);
			try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			System.out.println("Producer afterSetChar: " + lock + lock.getHoldCount());
			
			System.out.println(ch + " produced by producer.");
			lock.unlock();
		}
		
		System.out.println("Producer is ending...");
	}
}

class Consumer extends Thread{
	private final ReentrantLock lock;
	
	private final Shared shared;
	
	Consumer(Shared s){
		shared = s;
		lock = (ReentrantLock)s.getLock();
		setName("ConsumerThread");
	}
	
	@Override
	public void run(){
		System.out.println("Consumer is starting...");
		System.out.println("Consumer: " + lock + lock.getHoldCount());
		char ch;
		do{
			lock.lock();
			ch = shared.getSharedChar();
			try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			System.out.println("Consumer afterGetChar: " + lock + lock.getHoldCount()); // count 1
			
			System.out.println(ch + " consumed by consumer.");
			lock.unlock();
		}while(ch!='Z');	
		
		System.out.println("Consumer is ending...");
	}
}

/* About the ReentrantLock.unlock() method doc.
* Attempts to release this lock.
* If the current thread is the holder of this lock then the hold count is decremented.
* If the hold count is now zero then the lock is released.
* If the current thread is not the holder of this lock then IllegalMonitorStateException is thrown.
*/