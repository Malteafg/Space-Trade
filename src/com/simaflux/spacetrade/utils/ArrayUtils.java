package com.simaflux.spacetrade.utils;

import java.util.ArrayList;

public abstract class ArrayUtils {
	/*
	 * Splitting functions
	 */
	public static String[][] splitArray(String[] input) {
		String[][] result = new String[2][input.length / 2];
		
		for(int i = 0; i < input.length / 2; i++) {
			for(int j = 0; j < 2; j++) {
				result[j][i] = input[i * 2 + j];
			}
		}
		
		return result;
	}
	
	/*
	 * Handling functions
	 */
	public static String[][] removeString(String[] array1, String[] array2, String string) {
		String[][] result = new String[2][array1.length - 1];
		
		for(int i = 0; i < array1.length; i++) {
			if(string.equals(array1[i])) {
				for(int j = 0; j < result[1].length; j++) {
					if(i > j) {
						result[0][j] = array1[j];
						result[1][j] = array2[j];
					} else {
						result[0][j] = array1[j + 1];
						result[1][j] = array2[j + 1];
					}
				}
				
				return result;
			}
		}
		
		return result;
	}
	
	public static String[][] addString(String[] array1, String[] array2, String string1, String string2) {
		String[] tempNames = new String[array1.length + 1];
		String[] tempExpls = new String[array2.length + 1];
		
		for(int i = 0; i < array1.length; i++) {
			tempNames[i] = array1[i];
			tempExpls[i] = array2[i];
		}
		
		tempNames[array1.length] = string1;
		tempExpls[array2.length] = string2;
		
		return mergeArraysToTwo(tempNames, tempExpls);
	}
	
	public static String[] replaceString(String[] array1, String[] array2, String remove1, String replace1, String remove2, String replace2) {
		for(int i = 0; i < array1.length; i++) {
			if(array1[i].equals(remove1)) {
				array1[i] = replace1;
				array2[i] = replace2;
			}
		}

		return mergeArraysToOne(array1, array2);
	}
	
	/*
	 * Merging functions
	 */
	public static String[][] mergeArraysToTwo(String[] array1, String[] array2){
		String[][] result = new String[2][array1.length];
		
		for(int i = 0; i < result[0].length; i++) {
			result[0][i] = array1[i];
			result[1][i] = array2[i];
		}
		return result;
	}
	
	public static String[] mergeArraysToOne(String[] array1, String[] array2){
		String[] result = new String[array1.length + array2.length];
		
		for(int i = 0; i < result.length / 2; i++) {
			result[i * 2] = array1[i];
			result[i * 2 + 1] = array2[i];
		}
		
		return result;
	}
	
	/*
	 * Converting functions
	 */
	public static ArrayList<String> convertToArrayList(String[] a) {
		ArrayList<String> r = new ArrayList<>();
		
		for(String s : a) {
			r.add(s);
		}
		
		return r;
	}
	
}
