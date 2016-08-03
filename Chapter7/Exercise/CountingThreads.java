public class CountingThreads{
	public static void main(String[] args){
		Runnable r = new Runnable(){
			@Override
			public void run(){
				String name = Thread.currentThread().getName();
				int count = 0;
				while(true){
					System.out.println(name + ": " + count++);
				}
			}
		};
		
		Thread threadA = new Thread(r);
		Thread threadB = new Thread(r);
		
		// daemon thread property : The Java Virtual Machine exits when the only threads running are all daemon threads.
		threadA.setDaemon(true);
		threadB.setDaemon(true);
		
		threadA.start();
		threadB.start();
		
		System.out.println(Thread.currentThread().getName() + " is dying left with 2 daemon threads.");
		// virtual machine exits nearly immidiately.
	}
}