package com.github.chenyoca.snippet.utils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @email  : chenyoca@gmail.com
 * @date   : 2012-10-23
 * @desc   : 字符工具类
 */
public class CharsetUtility {

	public static final char[] CHINESE_FIRST_LETTER_TABLE = { 'a', 'b', 'c',
			'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'w', 'x', 'y', 'z' };

	// 存放国标一级汉字不同读音的起始区位码
	private static final int[] GB_AREA_CODE = { 1601, 1637, 1833, 2078, 2274,
			2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
			4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

	/**
	 * </br><b>title : </b>		判断是否是中文字符
	 * </br><b>description :</b>判断是否是中文字符
	 * </br><b>time :</b>		2012-7-17 下午10:27:17
	 * @param ch
	 * @return
	 */
	public static boolean isChineseChar(char ch) {
		// 如果左移7不为0是中文
		return (ch >> 7) != 0;
	}

	/**
	 * @title: getFirstLetter
	 * @Description: 取得首字母
	 * @Param chinesChar 中文
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 *             不是GBK编码时抛出异常
	 */
	public static Character getFirstLetter(char chinesChar)
			throws UnsupportedEncodingException {
		byte[] uniCode = String.valueOf(chinesChar).getBytes("GBK");
		return (0 < uniCode[0] && uniCode[0] < 128) ? null : convert(uniCode);
	}

	/**
	 * </br><b>description :</b>转换成字符
	 * </br><b>time :</b>		2012-7-17 下午10:28:57
	 * @param bytes
	 * @return
	 */
	private static char convert(byte[] bytes) {
		char result = '-';
		int secPosValue = 0;
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] -= 160;
		}
		secPosValue = bytes[0] * 100 + bytes[1];
		for (int i = 0; i < CHINESE_FIRST_LETTER_TABLE.length; i++) {
			if (secPosValue >= GB_AREA_CODE[i]
					&& secPosValue < GB_AREA_CODE[i + 1]) {
				result = CHINESE_FIRST_LETTER_TABLE[i];
				break;
			}
		}
		return result;
	}
	
	/**
	 * <b>description :</b>		半角字符转全角字符
	 * </br><b>time :</b>		2012-9-14 上午10:36:15
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		if(input == null) return null;
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}
	
	/**
	 * <b>description :</b>		去除特殊字符或将所有中文标号替换为英文标号
	 * </br><b>time :</b>		2012-9-14 上午10:37:17
	 * @param str
	 * @return
	 */
	public static String stringFilter(String input) {
		if(input == null) return null;
		input = input.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(input);
		return m.replaceAll("").trim();
	}
	
	/**
	 * 首字母大字
	 * @param input
	 * @return
	 */
	public static String firstCharUpper(String input){
		if(input == null) return null;
		String firstChar = input.substring(0, 1).toUpperCase(Locale.getDefault());
		return firstChar + input.subSequence(1, input.length());
	}
}
