package com.cyh.__java.text;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormatDemo {

	
	public static void main(String[] args) {
		Locale locale = Locale.getDefault();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
		String str = resourceBundle.getString("cyh");
		String str2 = MessageFormat.format(str, new Object[]{"dddd", 333});		
		System.out.println(str2);
		
		str = resourceBundle.getString("byw");
		str2 = MessageFormat.format(str, new Object[]{"efefef", 333});
		System.out.println(str2);
	}
	
}
