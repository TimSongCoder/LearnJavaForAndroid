public class InheritableThreadLocalDemo{
	private static volatile InheritableThreadLocal<Integer> workerIndex = new InheritableThreadLocal<Integer>(){
		@Override
		protected Integer childValue(Integer parentValue){
			return parentValue + 5;  // Default implementation return the parentValue.
		}
	};
	
	// For comparision purpose. Can parent thread's thread-local variable's value pass to child thread? No.
	private static volatile ThreadLocal<String> workerName = new ThreadLocal<String>();
	
	public static void main(String[] args){
		Runnable parentRun = new Runnable(){
			@Override
			public void run(){
				workerIndex.set(new Integer(10));
				workerName.set("BigHead");
				
				Runnable childRun = new Runnable(){
					@Override
					public void run(){
						System.out.println(Thread.currentThread().getName() + "'s index is " + workerIndex.get() + "; with workerName : " + workerName.get());  
						// This demonstrates the 'inheritable' property. Output: Child, workerIndex: 15;  workerName: null
						Thread grandsonThread = new Thread(new Runnable(){
							@Override
							public void run(){
								System.out.println(Thread.currentThread().getName() + "'s index is " + workerIndex.get() + "; with workerName : " + workerName.get());
								// Parent --> Child --> Child...   Output: Grandson, workerIndex: 20; workerName: null
							}
						}, "Grandson");
						grandsonThread.start();
					}
				};
				
				Thread childThread = new Thread(childRun, "Child");
				childThread.start();
				
				System.out.println(Thread.currentThread().getName() + "'s index is " + workerIndex.get() + "; with workerName : " + workerName.get()); 
				// Output: Parent, workerIndex: 10;  workerName: BigHead
			}
		};
		
		new Thread(parentRun, "Parent").start();
	}
}