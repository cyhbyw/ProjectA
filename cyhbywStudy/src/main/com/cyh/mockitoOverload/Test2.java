package com.cyh.mockitoOverload;

public class Test2 {

	
	public void fun() {
		System.out.println("Test2 fun begin...");
		
		System.out.println(LicenseUtil.getLicense(235200000L, "cyhbywsdag"));
		System.out.println(LicenseUtil.getLicense(254512L));
		
		System.out.println("Test2 fun end...");
	}
	
}
