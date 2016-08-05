import java.util.Random;

public class ArrayShuffler{
	public static void main(String[] args){
		Integer[] array = generateFixedRandomArray(30);
		shuffleArray(array);
		for(int i =0 ; i<array.length;i++){
			System.out.print(array[i] + " ");
		}
	}
	
	static Integer[] generateFixedRandomArray(int length){
		long mySeed = 56L;
		Random random = new Random(mySeed);  // Same seed get same sequence.
		Integer[] array = new Integer[length];
		for(int i = 0; i< length ; i++){
			array[i] = random.nextInt(10);  // 0-9
			System.out.print(array[i] + " ");
		}
		System.out.println();
		return array;
	}
	
	// "Cannot Instantiate Generic Types with Primitive Types" from Java Documentation. T can not represent int .etc
	static <T> void shuffleArray(T[] array){
		Random random = new Random();
		for(int i =0;i<array.length;i++){  // Random time can not be exactly array.length.
			int n = random.nextInt(array.length);
			// swap element.
			T temp = array[i];
			array[i] = array[n];
			array[n] = temp;
		}
	}
}