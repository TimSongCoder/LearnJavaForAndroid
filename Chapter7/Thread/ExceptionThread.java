// Control flow: Thread object specified Handler --> ThreadGroup handler --> Default handler.
public class ExceptionThread{
	public static void main(String[] args){
		Thread thd = new Thread(new Runnable(){
			@Override
			public void run(){
				int x = 1/0;
			}
		});
		thd.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
			@Override 
			public void uncaughtException(Thread t, Throwable e){
				System.out.println("Caught throwable " + e + " for thread " + t);
			}
		});
		// Method below is a static one. AKA, class method.
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
			@Override 
			public void uncaughtException(Thread t, Throwable e){
				System.out.println("Default uncaught exception handler caught throwable " + e + " for thread " + t);
			}
		});
		
		thd.start();
	}
}