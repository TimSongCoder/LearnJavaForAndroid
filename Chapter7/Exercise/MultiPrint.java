public class MultiPrint{
	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("Need one text argument to be printed and one number argument as copy count.");
			return;
		}else{
			String printText = args[0];
			try{
				int copyCount = Integer.parseInt(args[1]);
				if(copyCount < 1){
					System.out.println(copyCount + "is a invalid copy count number.");
				}else{
					for(int count = 0; count < copyCount; count++){
						System.out.println(printText);
					}
				}
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
	}
}