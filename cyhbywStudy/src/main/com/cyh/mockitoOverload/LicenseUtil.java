package com.cyh.mockitoOverload;

public class LicenseUtil {

	private LicenseUtil() {}
	
	public static int getLicense(long code) {
		System.out.println("isLicenseValid-a");
		return getLicense(code, "localhost");
	}
	
	public static int getLicense(long code, String str) {
		System.out.println("isLicenseValid-b");
		return 1020;
	}
	
	public static void print() {
		System.out.println("------------");
	}
	
}
