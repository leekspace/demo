package com.leekli.javase;

import com.google.common.collect.ArrayListMultimap;

public class Demo1 {
	
	public static void main(String[] args) {
		ArrayListMultimap<Object, Object> a = ArrayListMultimap.create();
		a.put("aa", 1);
		System.out.println(a.keys());
		
		
		
	}

}
