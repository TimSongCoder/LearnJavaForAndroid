import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dictionary{
	public static void main(String[] args){
		final String[] words = {"hypocalcemia", "prolixity", "assiduous", "indefatigable", "castellan"};
		final String[] definitions = {"a deficiency of calcium in the blood",
		"unduly prolonged or drawn out", 
		"showing great care, attention, and effort",
		"able to work or continue for a lengthy time without tiring",
		"the govenor or warden of a castle or fort"};
		final Map<String, String> dictionary = new HashMap<String, String>();
		
		ReadWriteLock rwl = new ReentrantReadWriteLock(true);
		final Lock writeLock = rwl.writeLock();
		final Lock readLock = rwl.readLock();
		System.out.println("WriteLock: " + writeLock + "; ReadLock: " + readLock);
		
		Runnable writer = new Runnable(){
			public void run(){
				for(int i=0;i<words.length;i++){
					writeLock.lock();
					try{
						dictionary.put(words[i], definitions[i]);
						System.out.println("writer storing " + words[i] +  " entry");
					}finally{
						writeLock.unlock();
					}
					try{
						Thread.sleep((int)(Math.random()*2000));
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
				}
			}
		};
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.submit(writer);
		
		Runnable reader = new Runnable(){
			public void run(){
				while(true){
					readLock.lock();
					try{
						int index = (int)(Math.random()*words.length);
						System.out.println("reader accessing " + words[index] + ": " + dictionary.get(words[index]) + " entry");
					}finally{
						readLock.unlock();
					}
					try{
						Thread.sleep((int)(Math.random()*100));
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
				}
			}
		};
		es = Executors.newFixedThreadPool(1);
		es.submit(reader);
	}
}