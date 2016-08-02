public class InheritableThreadLocalDemo{
	private static volatile InheritableThreadLocal<Integer> workerIndex = new InheritableThreadLocal<Integer>(){
		@Override
		protected Integer childValue(Integer parentValue){
			return parentValue + 5;  // Default implementation return the parentValue.
		}
	};
	
	public static void main(String[] args){
		Runnable parentRun = new Runnable(){
			@Override
			public void run(){
				workerIndex.set(new Integer(10));
				
				Runnable childRun = new Runnable(){
					@Override
					public void run(){
						System.out.println(Thread.currentThread().getName() + "'s index is " + workerIndex.get());  
						// This demonstrates the 'inheritable' property. Output 15.
					}
				};
				
				Thread childThread = new Thread(childRun, "Child");
				childThread.start();
				
				System.out.println(Thread.currentThread().getName() + "'s index is " + workerIndex.get()); // Output 10.
			}
		};
		
		new Thread(parentRun, "Parent").start();
	}
}