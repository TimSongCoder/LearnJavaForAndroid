public class DigitsToWords{
	private static final String THOUSAND = "THOUSAND";
	private static final String HUNDRED = "HUNDRED";
	
	public static void main(String[] args){
		if(args.length!=1){
			int randomNumber = (int)(Math.random()*10000);
			System.out.println("Random Bonus ---> " + randomNumber + " is converted to : " + convertDigitsToWords(randomNumber));
			throw new IllegalArgumentException("Need one number argument.");
		}else{
			int number = Integer.parseInt(args[0]);
			if(number < 0 || number > 9999){
				throw new IllegalArgumentException("Need one number argument between 0 and 9999 inclusively.");
			}else{
				System.out.println(convertDigitsToWords(number));
			}
		}
	}
	
	static String convertDigitsToWords(int number){
		if(number==0){
			return "zero";
		}
		char separatorSpace = ' ';
		int thousandValue = number / 1000;
		int hundredValue = number%1000/100;
		int tenValue = number%100/10;
		int oneValue = number%10;
		int tenOneValue = number%100;
		StringBuffer stringBuffer = new StringBuffer();
		String thousandText = convertSingleDigitToWord(thousandValue);
		if(thousandText!=null){
			stringBuffer.append(thousandText).append(separatorSpace).append(THOUSAND);
		}
		String hundredText = convertSingleDigitToWord(hundredValue);
		if(hundredText!=null){
			if(stringBuffer.length()>0){
				stringBuffer.append(separatorSpace);
			}
			stringBuffer.append(hundredText).append(separatorSpace).append(HUNDRED);
		}
		if(stringBuffer.length()>0){
			stringBuffer.append(" and ");
		}
		String oneText = convertSingleDigitToWord(oneValue);
		if(tenValue>1){
			String tenText = converTenDigitToWord(tenValue);
			if(tenText!=null){
				stringBuffer.append(tenText);
				if(oneText!=null){
					stringBuffer.append('-').append(oneText);
				}
			}
		}else if(tenValue==0){
			if(oneText!=null){
				stringBuffer.append(oneText);
			}
		}else{
			String teenText = convertDigitsToTeenText(tenOneValue);
			if(teenText!=null){
				stringBuffer.append(teenText);
			}
		}
		return stringBuffer.toString();
	}
	
	static String convertDigitsToTeenText(int tenOneValue){
		switch(tenOneValue){
			case 10:
				return "ten";
			case 11:
				return "eleven";
			case 12:
				return "twelve";
			case 13:
				return "thirteen";
			case 14:
				return "fourteen";
			case 15:
				return "fifteen";
			case 16:
				return "sixteen";
			case 17:
				return "seventeen";
			case 18:
				return "eighteen";
			case 19:
				return "nineteen";
			default:
				return null;
		}
	}
	
	static String convertSingleDigitToWord(int digit){
		switch(digit){
			case 0:
				return null;
			case 1:
				return "one";
			case 2:
				return "two";
			case 3:
				return "three";
			case 4:
				return "four";
			case 5:
				return "five";
			case 6:
				return "six";
			case 7:
				return "seven";
			case 8:
				return "eight";
			case 9:
				return "nine";
			default:
				return null;
		}
	}
	
	static String converTenDigitToWord(int digit){
		switch(digit){
			case 2:
				return "twenty";
			case 3:
				return "thirty";
			case 4:
				return "forty";
			case 5:
				return "fifty";
			case 6:
				return "sixty";
			case 7:
				return "seventy";
			case 8:
				return "eighty";
			case 9:
				return "ninety";
			default:
				return null;
		}
	}
}