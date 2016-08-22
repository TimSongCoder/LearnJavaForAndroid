import java.util.Iterator;

interface MyIterableImp<E> extends Iterable{
	void myMethod();
	
	Iterator<E> iterator();
}

public class ExtendInterfaceDemo<E> implements MyIterableImp{
	public static void main(String[] args){
		System.out.println("Hello World.");
	}
	
	public void myMethod(){
		// Do nothing.
	}
	
	// Now I do not know this method implements which declarations, the one in Iterable or MyIterableImp. Or they have overriding relation?
	public Iterator<E> iterator(){
		return new Iterator<E>(){
			public boolean hasNext(){
				return false;  // pseudo code
			}
			
			public E next(){
				return null;
			}
			
			public void remove(){
				// Do nothing.
			}
		};
	}
}