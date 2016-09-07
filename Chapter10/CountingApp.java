import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountingApp{
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(2);
		Runnable countRunnable = new Runnable(){
			public void run(){
				int count = 0;
				String threadName = Thread.currentThread().getName();
				while(count<100){
					System.out.println(threadName + ": " + count++);
				}
			}
		};
		
		es.submit(countRunnable);
		
		es.submit(countRunnable);
	}
}