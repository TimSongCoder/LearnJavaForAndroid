import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PC{
	public static void main(String[] args){
		final BlockingQueue<Character> bq = new ArrayBlockingQueue<Character>(26);
		final ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable producer = new Runnable(){
			public void run(){
				for(char ch='A';ch <= 'Z';ch++){
					try{
						bq.put(ch);
						System.out.println(ch + " produced by " + Thread.currentThread());
					}catch(InterruptedException ie){
						ie.printStackTrace();
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
						ch = bq.take();
						System.out.println(ch + " consumed by " + Thread.currentThread());
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
				}while(ch!='Z');
				executor.shutdownNow();
			}
		};
		executor.execute(consumeRunnable);
	}
}