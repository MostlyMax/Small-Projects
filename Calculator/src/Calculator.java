import java.util.Scanner;

public class Calculator {
	
	static int rStart, rEnd;
	
	public static void main(String args[]) {
		
		String input;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);  
		
		input = scan.nextLine();
		System.out.println("Done:"+calc(input));
	}
	
	/**
	 * 
	 * @param input
	 * @return Calculates input string
	 */
	public static double calc(String input) {
		int openP, closeP = 0, ignore = 0;
		double ans = 0;
		String tempString;
		System.out.println(input);
		input = fixMinus(input);
		
		/**
		 * checks for brackets and recursively solves the inside
		 */
		if (input.contains("(")) {
			openP = input.indexOf("(");
			for (int i=openP+1; i<input.length(); i++) {
				if (input.charAt(i)=='(') ignore++;
				if ((input.charAt(i)==')')&&(ignore==0)) {
					closeP=i;
					break;
				}
				else if (input.charAt(i)==')') ignore--;
			}
			ans = calc(input.substring(openP+1, closeP));
			
			tempString = input.substring(openP, closeP+1);
			input = input.replace(tempString, String.valueOf(ans));
			System.out.println(input);
			input = fixMinus(input);
			
		}
		
		/**
		 * If the input is just a number, returns that number
		 */
		if (!(simplifyM(input))&&!(simplifyA(input))) {
			input = input.replace("~", "-");
			ans = Double.valueOf(input);
		}
		
		/**
		 * Simplifies all multiplication/division
		 */
		while (simplifyM(input)) {
				
				if (!(input.contains("/"))) ans=multiply(input);
				else if (!(input.contains("*"))) ans=divide(input);
				else if ((input.contains("*"))&&(input.contains("/"))) {
					if ((input.indexOf("*"))<(input.indexOf("/"))) ans=multiply(input);
					else ans=divide(input);
				}
			
				//System.out.println(ans);
				tempString = input.substring(rStart, rEnd);
				//System.out.println("Substring:"+tempString);
				input = input.replace(tempString, String.valueOf(ans));
				System.out.println("String:"+input);
				System.out.println("");
				input = fixMinus(input);
				
		}
		
		/**
		 * Simplifies all addition/subtraction
		 */
		while (simplifyA(input)) {
			
			if (!(input.contains("-"))) ans=add(input);
			else if (!(input.contains("+"))) ans=subtract(input);
			else if ((input.contains("+"))&&(input.contains("-"))) {
				if ((input.indexOf("+"))<(input.indexOf("-"))) ans=add(input);
				else ans=subtract(input);
			}
			
			//System.out.println(ans);
			tempString = input.substring(rStart, rEnd);
			//System.out.println("Substring:"+tempString);
			input = input.replace(tempString, String.valueOf(ans));
			System.out.println("String:"+input);
			System.out.println("");
			input = fixMinus(input);
		}
		
		
		return ans;
	
	}
	
	/**
	 * 
	 * @param input
	 * @return True if equation can be simplified to addition
	 */
	public static boolean simplifyM(String input) {
		if ((input.contains("*"))||(input.contains("/"))) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param input
	 * @return True if equation can be solved
	 */
	public static boolean simplifyA(String input) {
		if ((input.contains("+"))||(input.contains("-"))) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param input
	 * @return Adds first possible pair
	 */
	public static double add(String input) {
		double numLeft, numRight;
		
		numLeft = getNumLeft(input, input.indexOf("+"));
		System.out.println("numLeft:"+numLeft);
		//System.out.println("rStart:"+rStart);
		numRight = getNumRight(input,input.indexOf("+"));
		System.out.println("numRight:"+numRight);
		//System.out.println("rEnd:"+rEnd);
		return numLeft+numRight;
	}
	
	/**
	 * 
	 * @param input
	 * @return Subtracts first possible pair
	 */
	public static double subtract(String input) {
		double numLeft, numRight;
		
		numLeft = getNumLeft(input, input.indexOf("-"));
		System.out.println("numLeft:"+numLeft);
		//System.out.println("rStart:"+rStart);
		numRight = getNumRight(input,input.indexOf("-"));
		System.out.println("numRight:"+numRight);
		//System.out.println("rEnd:"+rEnd);
		return numLeft-numRight;
	}
	
	/**
	 * 
	 * @param input
	 * @return Product of multiplying first possible number
	 */
	public static double multiply(String input) {
		double numLeft, numRight;
		
		numLeft = getNumLeft(input, input.indexOf("*"));
		System.out.println("numLeft:"+numLeft);
		//System.out.println("rStart:"+rStart);
		numRight = getNumRight(input,input.indexOf("*"));
		System.out.println("numRight:"+numRight);
		//System.out.println("rEnd:"+rEnd);
		return numLeft*numRight;
	}
	
	/**
	 * 
	 * @param input
	 * @return Product of dividing first possible number
	 */
	public static double divide(String input) {
		double numLeft, numRight;
		numLeft = getNumLeft(input, input.indexOf("/"));
		System.out.println("numLeft:"+numLeft);
		//System.out.println("rStart:"+rStart);
		numRight = getNumRight(input,input.indexOf("/"));
		System.out.println("numRight:"+numRight);
		//System.out.println("rEnd:"+rEnd);
		
		return numLeft/numRight;
	}
	
	/**
	 * 
	 * @param input
	 * @param symbol
	 * @return The number to the left of the symbol
	 */
	public static double getNumLeft(String input, int symbol) {
		char test = 0;
		int start = symbol-1;
		int i = 1;
		double num = 0;
		
		while (start>=0) {
			test = input.charAt(start);
			rStart = start;
			
			if (test == '.') {
				num/=i;
				i=1;
				start--;
				rStart = start;
				continue;
			}	
			if (isNum(test)){
				double tempNum;
				tempNum = i*Character.getNumericValue(test);
				//System.out.println("tempNum = "+tempNum);
				num+=tempNum;
				//System.out.println("num = "+num);
			}
			else if(test == '~') return num*-1;
			else {
				rStart = start+1;
				break;
			}
			i*=10;
			start--;
		}
		return num;
	}
	
	/**
	 * 
	 * @param input
	 * @param symbol
	 * @return The number to the right of the symbol
	 */
	public static double getNumRight(String input, int symbol) {
		char test = 0;
		int end = symbol+1;
		int i = 1;
		double num = 0;
		boolean decimal = false;
		boolean negative = false;
		
		while (end<input.length()) {
			test = input.charAt(end);
			
			if (test == '.') {
				decimal = true;
				i=10;
				end++;
				rEnd = end;
				continue;
			}
			if (test == '~') {
				negative = true;
				end++;
				rEnd = end;
				continue;
			}
			
			if (isNum(test)){
				if (!decimal) {
					num*=10;
					num+=Character.getNumericValue(test);
					//System.out.println("num = "+num);
				}
				else {
					double tempNum = Character.getNumericValue(test);
					tempNum/=i;
					num+=tempNum;
				}
			}
			else {
				rEnd = end;
				break;
			}
			i*=10;
			end++;
			rEnd = end;
		}
		if (negative) return num*-1;
		else return num;
	}
	
	/**
	 * 
	 * @param test
	 * @return Returns true if the character is a number; false if not
	 */
	public static boolean isNum(char test) {
		return ((test=='0')||(test=='1')||(test=='2')||(test=='3')||(test=='4')||(test=='5')||(test=='6')||(test=='7')||(test=='8')||(test=='9'));
	}
	
	/**
	 * 
	 * @param input
	 * @return String with negative numbers replaced with '~'
	 */
	public static String fixMinus(String input) {
		if (input.charAt(0)=='-') input = input.replaceFirst("-", "~");
		if (input.contains("+-")) input = input.replace("+-", "-");
		if (input.contains("*-")) input = input.replace("*-", "*~");
		if (input.contains("/-")) input = input.replaceAll("/-", "/~");
		return input;
	}
	
}
