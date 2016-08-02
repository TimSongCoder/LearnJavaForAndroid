public class ID{
	private static long nextId = Integer.MAX_VALUE;
	
	static synchronized long getNextId(){
		return nextId++; 
		// nextId = nextId + 1; 1--> convert to long type, this is not a atomic operation. need read and wirte, 2 steps.
	}
	
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			System.out.println(ID.getNextId());
		}
	}
}