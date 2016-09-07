import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicVariableDemo{
	public static void main(String[] args){
		int threadAcount = 20;
		ExecutorService es = Executors.newFixedThreadPool(threadAcount);
		Runnable runnable = new Runnable(){
			public void run(){
				System.out.println(Thread.currentThread() + " get id: " + ID.getNextID());
				if(ID.getNextID() == threadAcount){
					es.shutdown();
				}
			}
		};
		for(int i=0; i<threadAcount; i++){
			es.submit(runnable);
		}
	}
}

class ID{
	private static AtomicLong nextID = new AtomicLong(0);
	
	static long getNextID(){
		return nextID.getAndIncrement();
	}
}