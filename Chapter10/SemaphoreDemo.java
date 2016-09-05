import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo{
	public static void main(String[] args){
		final Pool pool = new Pool();
		Runnable runnable = new Runnable(){
			@Override
			public void run(){
				String threadName = Thread.currentThread().getName();
				try{
					while(true){
						String item;
						System.out.println(threadName + " acquiring " + (item = pool.getItem()));
						Thread.sleep(200 + (int)(Math.random()*1000));
						System.out.println(threadName + " putting back " + item);
						pool.putItem(item);
					}
				}catch(InterruptedException ie){
					System.out.println(threadName + " has been interrupted.");
				}
			}
		};
		
		ExecutorService[] executors = new ExecutorService[Pool.MAX_AVAILABLE + 5];
		for(int i=0;i<executors.length; i++){
			executors[i] = Executors.newSingleThreadExecutor();
			executors[i].execute(runnable);
		}
	}
}

final class Pool{
	public static final int MAX_AVAILABLE = 5;
	
	private Semaphore availabeSemaphore = new Semaphore(MAX_AVAILABLE, true);
	
	private String[] items;
	
	private boolean[] used = new boolean[MAX_AVAILABLE];
	
	Pool(){
		items = new String[MAX_AVAILABLE];
		for(int i=0; i<items.length;i++){
			items[i] = "Item_" + i;
		}
	}
	
	String getItem() throws InterruptedException{
		availabeSemaphore.acquire();
		return getNextAvailabeItem();
	}
	
	void putItem(String item){
		if(markAsUsed(item)){
			availabeSemaphore.release();
		}
	}
	
	private synchronized String getNextAvailabeItem(){
		for(int i=0; i<MAX_AVAILABLE; i++){
			if(!used[i]){
				used[i] = true;
				return items[i];
			}
		}
		return null;  // Not reached in this logic implementation.
	}
	
	private synchronized boolean markAsUsed(String item){
		for(int i=0; i<MAX_AVAILABLE; i++){
			if( item == items[i]){
				if(used[i]){
					used[i] = false;
					return true; // used before
				}else{
					return false;
				}
			}
		}
		return false;
	}
}