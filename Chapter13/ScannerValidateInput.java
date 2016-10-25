import java.util.Scanner;

public class ScannerValidateInput{
	
	public static void main(String[] args){
		Scanner inputScanner = new Scanner(System.in);
		int lineNum = 0;
		// scan this input on a line-by-line basis.
		while(inputScanner.hasNextLine()){
			// obtain next line
			String lineStr = inputScanner.nextLine();
			System.out.printf("%d: %s%n", ++lineNum, lineStr);
			
			// parse this line's content in detail with associated Scanner instance.
			Scanner scanner = new Scanner(lineStr);
			try{
				if(scanner.hasNext()){
					System.out.printf("Name: %s%n", scanner.next());
				}else{
					System.out.printf("%d: name field missing%n", lineNum);
					continue;
				}
				if(scanner.hasNextInt()){
					System.out.printf("Age: %d%n", scanner.nextInt());
				}else{
					System.out.printf("%d: age field missing%n", lineNum);
					continue;
				}
			}finally{
				System.out.println();
				scanner.close();
			}
		}
		inputScanner.close();
	}
}