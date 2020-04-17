package main;

public class Alphabet {
public static boolean vk_0;
public static boolean vk_1;
public static boolean vk_2;
public static boolean vk_3;
public static boolean vk_4;
public static boolean vk_5;
public static boolean vk_6;
public static boolean vk_7;
public static boolean vk_8;
public static boolean vk_9;
public static boolean vk_backspace;

public static String type() {
	if (vk_0) {
		vk_0=false;
		return "0";
	}else if (vk_1) {
		vk_1=false;
		return "1";
	}else if (vk_2) {
		vk_2=false;
		return "2";
	}else if (vk_3) {
		vk_3=false;
		return "3";
	}else if (vk_4) {
		vk_4=false;
		return "4";
	}else if (vk_5) {
		vk_5=false;
		return "5";
	}else if (vk_6) {
		vk_6=false;
		return "6";
	}else if (vk_7) {
		vk_7=false;
		return "7";
	}else if (vk_8) {
		vk_8=false;
		return "8";
	}else if (vk_9) {
		vk_9=false;
		return "9";
	}else if (vk_backspace) {
		vk_backspace=false;
		return "backspace";
	} 
	
	return "";
}
}
