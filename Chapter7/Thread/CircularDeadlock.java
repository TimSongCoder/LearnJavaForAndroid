/* Thread A called Class A's synchronized method addOne(), Thread B called Class C's synchronized method addThree();
method addOne() called Class B's synchronized method addTwo(),
method addTwo() called Class C's synchronized method addThree(),
and method addThree() called Class A's method addOne().

When I run this application on my PC, the output is like this:
Class A add ONE to COUNT : 1
Class C add THREE to COUNT : 4
Class B add TWO to COUNT : 6
_
and then application is freezed up because of 'deadlock'.
Thread-B is waiting for Thread-A to release lock to execute the addOne() method call,
while Thread-A is waiting for Thread-B to release lock to execute the addThree() method call.
They both are holding the lock which the other needed.
*/
public class CircularDeadlock{
	public static int COUNT = 0;
	
	public static void main(String[] args){
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(COUNT < 10000){
					A.addOne();
				}
			}
		}, "Thread-A").start();
		
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(COUNT < 10000){
					C.addThree();
				}
			}
		}, "Thread-B").start();
	}
}

class A{
	static synchronized void addOne(){
		CircularDeadlock.COUNT ++;
		System.out.println("Class A add ONE to COUNT : " + CircularDeadlock.COUNT);
		try{
			Thread.sleep(50);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		B.addTwo();
	}
}

class B{
	static synchronized void addTwo(){
		CircularDeadlock.COUNT += 2;
		System.out.println("Class B add TWO to COUNT : " + CircularDeadlock.COUNT);
		try{
			Thread.sleep(50);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		C.addThree();
	}
}

class C{
	static synchronized void addThree(){
		CircularDeadlock.COUNT += 3;
		System.out.println("Class C add THREE to COUNT : " + CircularDeadlock.COUNT);
		try{
			Thread.sleep(50);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		A.addOne();
	}
}