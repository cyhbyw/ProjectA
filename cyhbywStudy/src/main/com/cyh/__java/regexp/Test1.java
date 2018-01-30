package com.cyh.__java.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

	public static void main(String[] args) {
		String regexp = "(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b";
		System.out.println(regexp);
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher("Arline ate eight apples and one orange while Anita hadn't any");
		System.out.println(m.matches());
	}

}
