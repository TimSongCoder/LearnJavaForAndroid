/* Run this application once on my pc, deadlock occured when count == 80. 
Then the inner critical section can not be accessed by both threads while the app still on.
You can press control and 'c' key simultaneously to force terminating the app. */
public class DeadlockDemo{
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	public static volatile int count = 0;
	public static final int MAX_COUNT = 10000;
	
	public void instanceMethod1(){
		synchronized(lock1){
			synchronized(lock2){
				System.out.println(Thread.currentThread().getName() + " in instanceMethod1." + count);
				// Critical section guarded first by lock1 and then by lock2.
			}
		}
	}
	
	public void instanceMethod2(){
		synchronized(lock2){
			synchronized(lock1){
				System.out.println(Thread.currentThread().getName() + " in instanceMethod2." + count);
				// Critical section guarded first by lock2 and then by lock1.
			}
		}
	}
	
	public static void main(String[] args){
		final DeadlockDemo dld = new DeadlockDemo();
		
		Thread threadA = new Thread(new Runnable(){
			@Override
			public void run(){
				while(count < MAX_COUNT){
					dld.instanceMethod1();
					try{
						Thread.sleep(50);
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
					count++;
				}
			}
		}, "Thread-A");
		
		Thread threadB = new Thread(new Runnable(){
			@Override
			public void run(){
				while(count < MAX_COUNT){
					dld.instanceMethod2();
					try{
						Thread.sleep(50);
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
					count++;
				}
			}
		}, "Thread-B");
		
		threadA.start();
		threadB.start();
	}
}