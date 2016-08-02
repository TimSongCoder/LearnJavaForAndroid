public class ProducerConsumer{
	public static void main(String[] args){
		Shared sharedInstance = new Shared();
		new Producer(sharedInstance).start();
		new Consumer(sharedInstance).start();
	}
}
 class Shared{
	 private char c = '\u0000';
	 private boolean writeable = true;
	 
	 synchronized void setSharedChar(char c){
		 while(!writeable){
			try{
				wait();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		 }
		 
		 this.c = c;
		 writeable = false;
		 
		 notify(); // this is omitted. This Shared Object instance is the lock object.
	 }
	 
	 synchronized char getSharedChar(){
		 while(writeable){
			 try{
				 wait();
			 }catch(InterruptedException ie){
				 ie.printStackTrace();
			 }
		 }
		 
		 writeable = true;
		 notify();
		 return c;
	 }
 }
 
 class Producer extends Thread{
	 private Shared s;
	 
	 Producer(Shared s){
		 this.s = s;
	 }
	 
	 @Override
	 public void run(){
		 for(char ch='A'; ch <='Z'; ch++){
			 synchronized(s){  // Need to use the same lock object with the other code block.
				 s.setSharedChar(ch);
				 System.out.println(ch + " produced by producer.");
			 }
		 }
	 }
 }
 
 class Consumer extends Thread{
	 private Shared s;
	 
	 Consumer(Shared s){
		 this.s = s;
	 }
	 
	 @Override
	 public void run(){
		 char ch;
		 do{
			 synchronized(s){
				 ch = s.getSharedChar();
				 System.out.println(ch + " consumed by consumer.");
			 }
		 }while(ch != 'Z');
	 }
 }