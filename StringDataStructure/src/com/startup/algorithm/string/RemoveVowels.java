package com.startup.algorithm.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoveVowels {
	
	public static String removeVowels(String str) {
		Set<Character> vowels = Set.of('a','e','i','o','u');
		StringBuilder sb = new StringBuilder();
		
		char[] chars = str.toCharArray();
		
		for(char c : chars) {
			if(!vowels.contains(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
		
	}
	
	public static int[] twoSum() {
		int[] result = new int[2];
		int[] numbers = {1,11,5,10,7,8};
		Map<Integer, Integer> map = new HashMap< >();
		int target = 9;
		
		for(int i = 0; i < numbers.length; i++) {
			if(!map.containsKey(numbers[i] - target)) { //target - numbers[i]
				map.put(numbers[i], i);
			} else {
				result[1] = i;
				result[0] = map.get(numbers[i] - target);
				return result;
			}
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(removeVowels("ice cream"));
		System.out.println(Arrays.toString(twoSum()));
		int[] result = twoSum();
		int[] numbers = {1,11,5,10,7,8};
		System.out.println(numbers[result[1]] + "," + numbers[result[0]]);
		
	}
	
	

}
