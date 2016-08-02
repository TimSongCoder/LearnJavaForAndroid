public class ThreadLocalDemo{
	private static volatile ThreadLocal<String> userID = new ThreadLocal<String>();
	
	public static void main(String[] args){
		Runnable r = new Runnable(){
			@Override
			public void run(){
				String name = Thread.currentThread().getName();
				String threadLocalInitialValue = userID.get(); // null as default value
				if(name.equals("A")){
					userID.set("foxtrot");
				}else{
					userID.set("charlie");
				}
				System.out.println(name + " " + userID.get() + " (With initial value : " + threadLocalInitialValue + " )");
			}
		};
		
		Thread threadA = new Thread(r, "A");
		Thread threadB = new Thread(r, "B");
		
		threadA.start();
		threadB.start();
	}
}