import java.lang.ref.ReferenceQueue;
import java.lang.ref.PhantomReference;

public class PhantomReferenceDemo{
	public static void main(String[] args){
		ReferenceQueue<LargeObject> refQueue = new ReferenceQueue<LargeObject>();
		PhantomReference<LargeObject> phantomRef = new PhantomReference<LargeObject>(new LargeObject(), refQueue);
		
		while(refQueue.poll() == null){ // GC did not detect phantom reference type yet.
			System.out.println("Waiting for first large object to be finalized...");
			new LargeObject(); // Create another unreferenced object.
		}
		
		System.out.println("First LargeObject is finalized. Created LargeObject " + LargeObject.objectCounter + " totally.");
		System.out.println("phantomRef.get() returns : " + phantomRef.get());
	}
}

class LargeObject{
	volatile static  int objectCounter = 0;
	private byte[] largeData = new byte[1024*1024*250]; // 250 megabytes
	
	LargeObject(){
		System.out.println("A new LargeObject is being constrcted.");
		objectCounter++;
	}
}