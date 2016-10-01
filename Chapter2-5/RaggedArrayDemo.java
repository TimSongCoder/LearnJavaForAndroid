import java.util.Arrays;

public class RaggedArrayDemo{
	public static void main(String[] args){
		// specify the row number, not the column number
		int[][] raggedArr = new int[5][];
		// specify diffent column numbers
		for (int i = 0; i < raggedArr.length; i++){
			raggedArr[i] = new int[i+1];
		}
		// output the raggedArr's contents
		for (int i = 0; i < raggedArr.length; i++){
				System.out.println(Arrays.toString(raggedArr[i]));
		}
		
	}
}