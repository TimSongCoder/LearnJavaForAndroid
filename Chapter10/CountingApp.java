import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CountingApp{
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(2, new ThreadFactory(){
			char threadName = 'A';
			public Thread newThread(Runnable r){
				// with limitations: pool capacity is less than 26.
				return new Thread(r, String.valueOf(threadName++));
			}
		});
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