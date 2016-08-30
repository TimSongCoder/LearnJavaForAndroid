import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

public class JavaQuiz{
	public static void main(String[] args){
		List<QuizEntry> quizList = new ArrayList<QuizEntry>();
		quizList.add(new QuizEntry("What's the latest version of JDK?", 
									new String[]{"1.8", "1.7", "1.6", "1.9"},
									'A'));
		quizList.add(new QuizEntry("How many primitive types does Java have?", 
									new String[]{"4", "8", "6", "9"},
									'B'));
		quizList.add(new QuizEntry("What are you learning Java for?", 
									new String[]{"Hobby", "Java Development", "Android Development", "Make a living"},
									'C'));
									
		Iterator<QuizEntry> iterator = quizList.iterator();
		while(iterator.hasNext()){
			QuizEntry quizEntry = iterator.next();
			System.out.println(quizEntry.question);
			for(int i=0;i<quizEntry.answers.length; i++){
				System.out.println(QuizEntry.answerIndicator[i] + ". " + quizEntry.answers[i]);
			}
			// prompt the user to answer
			System.out.print("Enter your answer: ");
			try{
				int userAnswer = -1;
				do{
					userAnswer = System.in.read();
					if(!isEscapeCharacter(userAnswer)){
						if(userAnswer == quizEntry.answer){
							System.out.println("Congratunations, you are right.");
						}else {
							System.out.println("You are wrong.");
						}
						System.out.println();
					}
				}while(isEscapeCharacter(userAnswer));  // Consume the system related carrige return character.
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
		}
	}
	
	static boolean isEscapeCharacter(int character){
		return character == '\r' || character=='\n';
	}
}

class QuizEntry{
	String question;
	String[] answers;
	char answer;
	static final char[] answerIndicator = {'A', 'B', 'C', 'D'};
	
	QuizEntry(String question, String[] answers, char answer){
		this.question = question;
		this.answers = answers;
		this.answer = answer;
	}
}