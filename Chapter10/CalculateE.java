import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculateE{
	final static int LASTITER = 17;
	
	public static void main(String[] args){
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Callable<BigDecimal> callable = new Callable<BigDecimal>(){
			@Override
			public BigDecimal call(){
				MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
				BigDecimal result = BigDecimal.ZERO;
				for(int i=0; i<= LASTITER; i++){
					BigDecimal factorial = factorial(new BigDecimal(i));
					BigDecimal res = BigDecimal.ONE.divide(factorial, mc);
					result = result.add(res);
				}
				return result;
			}
			
			public BigDecimal factorial(BigDecimal n){
				if(n.equals(BigDecimal.ZERO)){
					return BigDecimal.ONE;
				}else{
					return n.multiply(factorial(n.subtract(BigDecimal.ONE)));
				}
			}
		};
		Future<BigDecimal> taskFuture = executor.submit(callable);
		
		try{
			while(!taskFuture.isDone()){
				System.out.println("waiting");  // simulate other operations executing in current thread.
			}
			System.out.println(taskFuture.get());
			BigDecimal result = taskFuture.get();
			System.out.println("precision: " + result.precision());
			System.out.println("number length: " + result.toString().length() + "\n");
			// To explore the precision and unscaled value
			BigDecimal quotient = BigDecimal.ONE.divide(new BigDecimal(17), 6, RoundingMode.HALF_UP);
			System.out.println("quotient: " + quotient);
			System.out.println("precision: " + quotient.precision());
			System.out.println("scale: " + quotient.scale());
			System.out.println("number length: " + quotient.toString().length());
		}catch(ExecutionException ee){
			System.err.println("task threw an exception:");
			System.err.println(ee);
		}catch(InterruptedException ie){
			System.err.println("interrupted while waiting.");
		}
		executor.shutdownNow();
	}
}