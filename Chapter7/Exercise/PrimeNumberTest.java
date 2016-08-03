public class PrimeNumberTest{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("I only handle a single number argument for once.");
			return;
		}else{
			String arg = args[0];
			try{
				int number = Integer.parseInt(arg);
				if(number<2){
					System.out.println("I only handle number argument greater than 1.");
					return;
				}else{
					boolean isPrime = true;
					for(int divisor=2; divisor <= Math.sqrt(number);divisor++){
						if(number % divisor == 0){
							isPrime = false;
							System.out.println(number + " can be evenly divided by " + divisor + ", so ");
							break;
						}
					}
					if(isPrime){
						System.out.println(number + " is a prime number.");
					}else{
						System.out.println(number + " is not a prime number.");
					}
				}
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
		System.out.println(Math.sqrt(121)); // output: 11.0
		System.out.println(11 == Math.sqrt(121));  // output: true;
		System.out.println(11 == 11.0);  //output: ture;
	}
}