import java.util.StringTokenizer;
import java.util.TimerTask;
import java.util.Timer;

public class TimerDemo{
	public static void main(String[] args){
		// StringTokenizer demo.
		System.out.println("StringTokenizer Demo:");
		StringTokenizer st = new StringTokenizer("this is a test"); // default delimiters is \t \r \n \f.
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
		
		// Timer demo.
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				System.out.println(System.currentTimeMillis());
			}
		};
		Timer timer = new Timer(); // Its related Thread is not daemon by default.
		timer.schedule(task, 0, 1000);
	}
}