package com.cyh.test;




public class Test3 {

	public static final String IDANDOMESNAME = "nbipm_IDANDOMES";
	public static final String stra = "aa";
	public static final String strb = "bb";
	
	public static void main(String[] args) {
		funA();
		funB();
		funC();
		funD();
	}

	private static void funA() {
		String str = stra + IDANDOMESNAME + strb;
		for(String s : str.split(IDANDOMESNAME)) {
			System.out.println("a[" + s + "]");
		}
		System.out.println(str.startsWith(IDANDOMESNAME) || str.endsWith(IDANDOMESNAME));
		System.out.println("----");
	}

	private static void funB() {
		String str = stra + IDANDOMESNAME;
		for(String s : str.split(IDANDOMESNAME)) {
			System.out.println("b[" + s + "]");
		}
		System.out.println(str.startsWith(IDANDOMESNAME) || str.endsWith(IDANDOMESNAME));
		System.out.println("----");
	}
	
	private static void funC() {
		String str = IDANDOMESNAME + strb;
		for(String s : str.split(IDANDOMESNAME)) {
			System.out.println("c[" + s + "]");
		}
		System.out.println(str.startsWith(IDANDOMESNAME) || str.endsWith(IDANDOMESNAME));
		System.out.println("----");
	}
	
	private static void funD() {
		String str = IDANDOMESNAME;
		for(String s : str.split(IDANDOMESNAME)) {
			System.out.println("d[" + s + "]");
		}
		System.out.println(str.startsWith(IDANDOMESNAME) || str.endsWith(IDANDOMESNAME));
		System.out.println("----");
	}
}
