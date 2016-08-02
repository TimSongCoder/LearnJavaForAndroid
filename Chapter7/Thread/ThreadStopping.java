public class ThreadStopping{
	public static void main(String[] args){
		class StoppableThread extends Thread{
			private volatile boolean stopped = false;
			
			@Override
			public void run(){
				while(!stopped){
					System.out.println(Thread.currentThread().getName() + " is running.");
				}
			}
			
			void stopThread(){
				stopped = true;
			}
		}
		
		StoppableThread myThread = new StoppableThread();
		myThread.setName("MyThread");
		
		myThread.start();
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		
		myThread.stopThread();
	}
}