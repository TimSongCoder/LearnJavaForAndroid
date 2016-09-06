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
					
				}finally{
					rwl.writeLock().unlock();
					rwl.readLock().unlock();
				}
			}
		});
	}
}