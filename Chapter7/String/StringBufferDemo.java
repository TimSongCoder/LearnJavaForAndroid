public class StringBufferDemo{
	public static void main(String[] args){
		StringBuffer buffer = new StringBuffer();
		buffer.append('B');
		buffer.append('u');
		buffer.append('z');
		buffer.append('z');
		System.out.println(buffer.toString());
		System.out.println(buffer.reverse().toString()); // buffer.reverse() return this StringBuffer object.
		
		buffer.append(" City");
		System.out.println("Capacity : "+ buffer.capacity() + ", Length : " + buffer.length() + ", Content : " + buffer.toString());
		
		buffer.setLength(6);
		System.out.println("Capacity : "+ buffer.capacity() + ", Length : " + buffer.length() + ", Content : " + buffer.toString());
		
		buffer.ensureCapacity(20);
        System.out.println("Capacity : "+ buffer.capacity() + ", Length : " + buffer.length() + ", Content : " + buffer.toString()); // 16<20 --> 16*2>20  --> 16*2 + 2 = 34. new Capacity.
	}
}