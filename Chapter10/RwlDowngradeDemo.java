import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RwlDowngradeDemo{
	public static volatile boolean writable = false;
	
	public static void main(String[] args){
		ReadWriteLock rwl = new ReentrantReadWriteLock();
		ExecutorService es = Executors.newFixedThreadPool(5);
	
		
		es.submit(new Runnable(){
			public void run(){
				try{
					rwl.writeLock().lock();
					if(writable){
							System.out.println(Thread.currentThread() + " Writing something, " + "with " + rwl.writeLock());
							writable = true;
					}else{
						// read
						rwl.readLock().lock();
						System.out.println(Thread.currentThread() + " Reading something, " + "with " + rwl.readLock());
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					System.out.println(rwl.writeLock() + ", " + rwl.readLock());
					rwl.writeLock().unlock();
					System.out.println("WriteLock released: \n" + rwl.writeLock() + ", " + rwl.readLock());
					rwl.readLock().unlock();
					System.out.println("ReadLock released: \n" + rwl.writeLock() + ", " + rwl.readLock());
				}
				es.shutdown();  // If you want the application end, you need to call the shutdown method otherwise it will be waiting for new runnables.
			}
		});
	}
}