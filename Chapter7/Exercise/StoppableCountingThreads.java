import java.io.IOException;

public class StoppableCountingThreads{
	private static volatile boolean stopped = false;
	
	public static void main(String[] args){
		Runnable r = new Runnable(){
			@Override
			public void run(){
				String name = Thread.currentThread().getName();
				int count = 0;
				while(!stopped){
					System.out.println(name + ": " + count++);
					try{
						Thread.sleep(100);
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
				}
			}
		};
		
		Thread threadA = new Thread(r);
		Thread threadB = new Thread(r);
		
		threadA.start();
		threadB.start();
		
		System.out.println(Thread.currentThread().getName() + " is waiting for your command to stop.");
		
		byte command = -1;
		try{
			command = (byte)(System.in.read()); // main thread is blocked by read method.
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			stopped = true;
			System.out.println("The command is : " + new String(new byte[]{command}) + " (with byte value " + command + " )");
		}
	}
}