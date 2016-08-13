import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskDemo{
	public static void main(String[] args){
		Timer timer = new Timer(true); // daemon task-execution thread
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				System.out.println("one-shot timer task executing.");
			}
		}, 2000);
		
		System.out.println("main thread is sleeping for 4 seconds.");
		try{
			Thread.sleep(4000);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		System.out.println("main thread has woken up.");
		
		timer = new Timer(); // not daemon thread by default.
		timer.schedule(new TimerTask(){
			int i;
			@Override
			public void run(){
				System.out.println("repeated timer task is running.");
				if(++i == 6){
					System.out.println("canceling repeated timer task: " + cancel());
					System.out.println("canceled");
					
					System.exit(0); // exit the application with force because that Timer is not canceled yet.
				}
			}
		}, 2000, 500);
		
		System.out.println("main thread is terminating.");
	}
}