package com.mobile.meishang.utils;
//package com.ivpoints.mobile.utils;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class StringUtil {
//
//	public static String replaceAllStr(String keyword) {
//		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//		Pattern p = Pattern.compile(regEx);
//		Matcher m = p.matcher(keyword);
//
//		return m.replaceAll("").trim();
//	}
//
//	public static String replaceBlank(String str) {
//		Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
//		Matcher matcher = pattern.matcher(str);
//		return matcher.replaceAll("");
//	}
//
//	public static String toDBC(String input) {
//		char[] c = input.toCharArray();
//		for (int i = 0; i < c.length; i++) {
//			if (c[i] == 12288) {
//				c[i] = (char) 32;
//				continue;
//			}
//			if (c[i] > 65280 && c[i] < 65375)
//				c[i] = (char) (c[i] - 65248);
//		}
//		return new String(c);
//	}
//
//	public static String stringFilter(String str) {
//		str = str.replaceAll("【", "[").replaceAll("】", "]")
//				.replaceAll("！", "!");// 替换中文标号
//		String regEx = "[『』]"; // 清除掉特殊字符
//		Pattern p = Pattern.compile(regEx);
//		Matcher m = p.matcher(str);
//		return m.replaceAll("").trim();
//	}
//
//	/**
//	 * 判断给定字符串是否空白串。<br>
//	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串<br>
//	 * 若输入字符串为null或空字符串，返回true
//	 * 
//	 * @param input
//	 * @return boolean
//	 */
//	public static boolean isBlank(String input) {
//		if (input == null || "".equals(input))
//
//			return true;
//
//		for (int i = 0; i < input.length(); i++) {
//			char c = input.charAt(i);
//			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * 将传入的null对象，或者空字符串转换成对应的int值
//	 * 
//	 * @param number
//	 * @return
//	 */
//	public static String replaceStr(String number) {
//		if ("".equals(number) || number == null) {
//			return "0";
//		} else {
//			return number;
//		}
//	}
//}