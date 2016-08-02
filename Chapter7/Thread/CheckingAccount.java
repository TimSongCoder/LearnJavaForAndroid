public class CheckingAccount{
	private int balance;
	
	public CheckingAccount(int initialBalance){
		balance = initialBalance;
	}
	
	public synchronized boolean withdraw(int amount){
		if(amount<=balance){
			/*这里认为将操作时间放大，判断完之后，还没有做 减量操作，结果另一个线程对balance资源进行了操作。（对于同一个初始值，操作了2次），比如说还剩10的时候，会出现balance=-10的结果。*/
			try{
				Thread.sleep((long)(Math.random()*200));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			balance-=amount;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		final CheckingAccount ca = new CheckingAccount(100);
		Runnable r = new Runnable(){
			@Override
			public void run(){
				String threadName = Thread.currentThread().getName();
				for(int i=0;i<10;i++){
					System.out.println(threadName + " withdraw $10 : " + ca.withdraw(10));
				}
			}
		};
		
		Thread threadHusband = new Thread(r, "Husband");
		Thread threadWife = new Thread(r, "Wife");
		
		threadHusband.start();
		threadWife.start();
	}
}