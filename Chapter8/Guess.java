import java.util.Random;
import java.io.IOException;
/**
* 1. Use Random to generate a integer from 0 to 25, both inclusively.
* 2. Repeatedly ask the user to guess the integer by entering a letter from a to z.
* 3. Compare the user entered letter with the random integer, the offset by lowercase letter a(0-25),
*    output message if user guess right or wrong, low or high.
*/
public class Guess{
	public static void main(String[] args){
		Random random = new Random();
		int number = random.nextInt(26);
		number += 'a';
		
		System.out.println("Please Guess the hidden number in the black box, using lowercase letter a-z representing 0-25:");
		int userGuess = -1;
		do{
			try{
				userGuess = System.in.read();
			}catch(IOException ioe){
				ioe.printStackTrace();
				System.out.println("Error, try again.");
				continue;
			}
			if(userGuess == '\r' || userGuess == '\n'){
				continue;
			}
			if(userGuess > number){
				System.out.println("Too high, guess again:");
			}else if( userGuess < number){
				System.out.println("Too low, guess again:");
			}
		}while(userGuess != number );
		
		System.out.println("Congratulations, you are right :) The number in the box is: " + (userGuess - 'a'));
		
	}
}