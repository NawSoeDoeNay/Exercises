package com.startup.algorithm.string;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {
	
	public static int isFirstNonRepeatingChar(String str) {
		Map<Character, Integer> characterFrequency = new HashMap<Character, Integer>();
		char[] chars = str.toCharArray();
		
		for(char c : chars) {
			characterFrequency.put(c, characterFrequency.getOrDefault(c, 0) + 1);
		}
		for(int i =0; i < chars.length; i++) {
			char ch = chars[i];
			if(characterFrequency.get(ch) == 1) {
				return i;
			}
			
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println(isFirstNonRepeatingChar("abcabcae"));
		
	}

}
