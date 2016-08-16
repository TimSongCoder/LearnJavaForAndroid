import java.util.TimerTask;
import java.util.Timer;

public class BackAndForth2{
	static enum Direction{
		FORWARDS,BACKWORDS
	}
	
	public static void main(String[] args){
		TimerTask task = new TimerTask(){
			final static int MAXSTEPS = 20;
			
			Direction direction = Direction.FORWARDS;
			
			int steps = -1; // need print one more time for the first round.
			
			boolean isInitial = true;
			
			@Override
			public void run(){
				switch(direction){
					case FORWARDS:
						if(!isInitial)
						System.out.print("\b ");  // \b stands for backspace, just move the edit place back, no replacement
						System.out.print("*");
						break;
					case BACKWORDS:
						System.out.print("\b ");  // replace current asterisk by space: back the index, use new character
						System.out.print("\b\b*");
						break;
				}
				if(++steps == MAXSTEPS){
					direction = (direction == Direction.FORWARDS) ? Direction.BACKWORDS : Direction.FORWARDS;
					steps = 0;
				}
				
				isInitial = false;
			}
		};
		
		Timer timer = new Timer();
		int interval = 1000;
		if(args.length!=0){
			try{
				interval = Integer.parseInt(args[0]);
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
		timer.schedule(task, 1000, interval);
	}
}