import java.util.Timer;
import java.util.TimerTask;

/**
* Repeatedly move an asterisk forward 20 steps and then backward 20 steps.
*/
public class BackAndForth{
	
	private static final long INTERVAL = 500;
	private static final String TOKEN = "*";
	private static int MAX_STEP = 20;
	
	private static volatile int stepCount = 0;
	private static volatile boolean isForward = true;
	
	public static void main(String[] args){
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				StringBuilder sb = new StringBuilder(TOKEN);				
				
				for(int i=0; i<stepCount; i++){
					sb.insert(0, " ");
				}
				System.out.println(sb.toString());
				
				if(isForward){
					stepCount++;
				}else{
					stepCount--;
				}
								
				if(stepCount == MAX_STEP){
					isForward = false;
				}else if(stepCount == 0){
					isForward = true;
				}
			}
		};
		
		timer.schedule(task, 1000, INTERVAL);
	}
}