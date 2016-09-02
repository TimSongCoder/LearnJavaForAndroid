import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Exchanger;

public class ExchangerDemo{
	static Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	
	static DataBuffer initialEmptyBuffer = new DataBuffer();
	static DataBuffer initialFullBuffer = new DataBuffer("I");
	
	public static void main(String[] args){
		class FillingLoop implements Runnable{
			int count =0;
			
			@Override
			public void run(){
				DataBuffer currentBuffer = initialEmptyBuffer;
				try{
					while(true){
						addToBuffer(currentBuffer);
						if(currentBuffer.isFull()){
							System.out.println(Thread.currentThread() + " as filling thread wants to exchange");
							currentBuffer = exchanger.exchange(currentBuffer);
							System.out.println(Thread.currentThread() + " receives an exchange");
						}
					}
				}catch(InterruptedException ie){
					System.out.println(Thread.currentThread() + " has been interrupted.");
				}
			}
			
			void addToBuffer(DataBuffer buffer){
				String item = "NI_" + count++;
				System.out.println(Thread.currentThread() +  " adding: " + item);
				buffer.add(item);
				try{
					Thread.sleep((long)(Math.random()*2000));  // simulating lengthier operation
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
		}
		
		class EmptyingLoop implements Runnable{
			@Override
			public void run(){
				DataBuffer currentBuffer = initialFullBuffer;
				try{
					while(true){
						takeFromBuffer(currentBuffer);
						if(currentBuffer.isEmpty()){
							System.out.println(Thread.currentThread() + " as emptying thread, wants to exchange");
							currentBuffer = exchanger.exchange(currentBuffer);
							System.out.println(Thread.currentThread() + " receives an exchange");
						}
					}
				}catch(InterruptedException ie){
					System.out.println(Thread.currentThread() + " has been interrupted.");
				}
			}
			
			void takeFromBuffer(DataBuffer buffer){
				System.out.println(Thread.currentThread() + " removing: " + buffer.remove());
				try{
					Thread.sleep((long)(Math.random()*2000));  // simulating lengthier operation
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
		}
		
		new Thread(new EmptyingLoop()).start();
		new Thread(new FillingLoop()).start();
	}
}

class DataBuffer{
	private final static int MAX_ITEMS = 10;
	
	private List<String> items = new ArrayList<String>();
	
	DataBuffer(){
		
	}
	
	DataBuffer(String prefix){
		for(int i=0; i<MAX_ITEMS;i++){
			String item = prefix + i;
			System.out.printf("Adding %s%n", item);
			items.add(item);
		}
	}
	
	void add(String s){
		if(!isFull()){
			items.add(s);
		}
	}
	
	boolean isEmpty(){
		return items.isEmpty();
	}
	
	boolean isFull(){
		return items.size() == MAX_ITEMS;
	}
	
	String remove(){
		if(!isEmpty()){
			return items.remove(0);
		}else{
			return null;
		}
	}
}