package com.startup.algorithm.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringReverse {
	
	public static String isReverse(String word) {
		
//		Stack<Character> stack = new Stack<Character>();
//		char[] charArray = word.toCharArray();
//		
//		for(char c : charArray) {
//			stack.push(c);
//		}
//		for(int i = 0; i < word.length(); i++) {
//			charArray[i] = stack.pop();
//		}
		
		List<Character> list = new ArrayList<Character>();
		char[] charArray = word.toCharArray();
		
		for(char c : charArray) {
			list.add(c);
		}
		
		for(int i = 0; i < word.length(); i++) {
			charArray[i] = list.remove(list.size()- 1);
		}
		return new String(charArray);
	}
	
	public static void main(String[] args) {
		
		String str = "ABCD";
		System.out.println("Before reverse: " + str);
		System.out.println("After reverse: " + isReverse(str));
	}

}
