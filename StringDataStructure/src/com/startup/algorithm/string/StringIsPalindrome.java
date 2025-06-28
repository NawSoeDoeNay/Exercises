package com.startup.algorithm.string;

public class StringIsPalindrome {
	
	public boolean isPalindrome(String word) {
		
		char[] charArray = word.toCharArray();
		int start = 0;
		int end = word.length() - 1;
		
//		while(start < end) {
//			if(charArray[start] != charArray[end]) {
//				return false;
//			}
//			start ++;
//			end --;
//		}
		
		for(int i = 0; i < end ; i++) {
			if(charArray[start] != charArray[end]) {
				return false;
			}
			start ++;
			end--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		StringIsPalindrome stringIsPalindrome = new StringIsPalindrome();
		if(stringIsPalindrome.isPalindrome("racecar")) {
			System.out.println("The string is palindrome!!!");
		} else {
			System.out.println("The string is not palindrome!!!");
		}
		
	}

}
