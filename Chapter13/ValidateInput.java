import java.util.Scanner;

public class ValidateInput{
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int lineNum = 0;
		while(true){
			System.out.printf("%nEnter the employee info: ");
			String entry = scanner.nextLine();
			System.out.println("nextline() value: " + entry); // for debug puporse
			entry = entry.trim();  // remove the possible leading and trailing spaces.
			if(entry.matches("(?i)quit")){  // Use embedded flag expression to ignore the case
				break;
			}
			if(entry.matches("\\w+\\s+\\d+")){
				// legal entry
				String[] items = entry.split("\\s+");
				System.out.printf("%1$d. Name: %2$s, Age: %3$s%n", ++lineNum, items[0], items[1]);
			}else if(entry.matches("\\D+")){
				System.out.println("age field missing.");
			}else if(entry.matches("\\d+")){
				System.out.println("name field missing.");
			}else{
				System.out.println("Violate format rule: name age");
			}
		}
		scanner.close();
	}
}