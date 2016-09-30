import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

public class CircleInfo{
	public static void main(String[] args){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			do{
				System.out.println("Enter the radius of the circle:");
				String text = br.readLine();
				double radius = Double.parseDouble(text);
				System.out.println("Circumference: " + getRoundedResult(2*Math.PI*radius));
				System.out.println("Area: " + getRoundedResult(Math.PI*radius*radius));
				System.out.println();
			}while(true);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	static double getRoundedResult(double original){
		BigDecimal bd = new BigDecimal(original);
		bd = bd.round(new MathContext(3));
		return bd.doubleValue();
	}
}