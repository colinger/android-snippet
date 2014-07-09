package com.github.chenyoca.snippet.utils;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com
 * date   : 2013-4-7
 * 计量单位工具类
 */
public class UnitUtility {

	private final int system;
	private String baseUnit;
	private String unit;
	private int accuracy = 2;
	
	private String link = " ";
	
	private String[] sections;
	private long[] sectionLevels;
	
	/**
	 * 默认计数系统值为1000
	 */
	public UnitUtility (){
		this(1000);
	}
	
	/**
	 * 指定计数系统值。例如字节单位的计数系统值为1024
	 * @param system 计数系统值
	 */
	public UnitUtility (int system){
		this.system = system;
	}
	
	/**
	 * 数据与单位之间的连接符，默认为一个空格。
	 * @param linkChar 连接符
	 * @return 计量单位工具类对象本身
	 */
	public UnitUtility link(String linkChar){
		this.link = linkChar;
		return this;
	}
	
	/**
	 * 结果的小数精度
	 * @param accuracy
	 * @return 计量单位工具类对象本身
	 */
	public UnitUtility accuracy(int accuracy){
		this.accuracy = accuracy;
		return this;
	}
	
	/**
	 *  
	 * @param baseUnit 基本单位。如果一个数没有达到最低计算数值，则用基本返回。例如，计算距离为千米（1000）,如果给定数据为123，则返回"123 米"。
	 * @param formatUnit 计量单位。只计算一个量级时，设置此单位。例如，计算距离为千米，如果给定数据为 123456789，则返回 123456.79千米。
	 * @return 计量单位工具类对象本身
	 */
	public UnitUtility unit(String baseUnit,String formatUnit){
		this.baseUnit = baseUnit;
		this.unit = formatUnit;
		return this;
	}
	
	/**
	 * 分段计算。分段计算几个量级时，设置此参数，最基本单位开始。
	 * e.g: unitUtility.sections("B","KB","MB","GB","TB")
	 * @param sections 分段单位数组
	 * @return 计量单位工具类对象本身
	 */
	public UnitUtility sections(String... sections){
		this.sections = sections;
		sectionLevels = new long[sections.length];
		sectionLevels[0] = system;
		for(int i=1;i<sectionLevels.length;i++){
			sectionLevels[i] = (long)Math.pow(system, i);
		}
		return this;
	}

	/**
	 * 将值数据转换成带单位的字符串
	 * @param value 值数据
	 * @return 带单位的字符串
	 */
	public String convert(double value){
		if(sections == null){
			if(baseUnit == null || unit == null){
				throw new IllegalArgumentException("Method 'base(String baseUnit)' and 'unit(String unit)' not makeCall!");
			}
			StringBuffer format = format(value,system);
			double result = value/system;
			if(result>=1) {
				format.append(unit);
			}else{
				result = value;
				format.append(baseUnit);
			}
			return String.format(format.toString(), result);
		}else{
			final int pow = logX(system,value);
			final int maxPow = sectionLevels.length - 1;
			final int index = (pow > maxPow ? maxPow : pow);
			double result = value/sectionLevels[index];
			StringBuffer format = format(value,sectionLevels[index]);
			if(result>=1) {
				format.append(sections[pow]);
			}else{
				result = value;
				format.append(sections[index]);
			}
			return String.format(format.toString(), result);
		}
	}

	static int logX(double base,double value){
		double result = value;
		int pow = 0;
		for(;;){
			result = result/base;
			if(result<1){
				break;
			}else{
				pow++;
			}
		}
		return pow;
	}
	
	StringBuffer format(double value,long base){
		StringBuffer format = new StringBuffer("%.");
		long valueL = (long)value;
		if (valueL == value){ // 没有有效小数部分
		    final long mod = valueL%base;
		    format.append( (mod == 0 || valueL == mod) ? 0 : accuracy);
		}else{
			format.append(accuracy);
		}
		format.append("f").append(link);
		return format;
	}
	
//	public static void main(String[] args){
		
//		UnitUtility basic = new UnitUtility().base("m").unit("km");
//		System.out.println(basic.convert(99.335));
//		System.out.println(basic.convert(1000));
//		System.out.println(basic.convert(9941.34));
//		System.out.println(basic.convert(10000));
//		System.out.println(basic.convert(99945));
		
//		final int loop = 1024;
//		
//		final int KB = 1024;
//		UnitUtility util = new UnitUtility(KB).sections("B","KB","MB","GB","TB");
//		System.out.println(util.convert(KB));
//		for(int i=1;i<loop;i++){
//			System.out.println(util.convert(KB+i*10));
//		}
//		
//		System.out.println(util.convert(KB*50));
//		final double MB = Math.pow(1024, 2);
//		System.out.println(util.convert(MB));
//		for(int i=1;i<loop;i++){
//			System.out.println(util.convert(MB+i*KB*10));
//		}
//		
//		final double GB = Math.pow(1024,3);
//		System.out.println(util.convert(GB));
//		for(int i=1;i<loop;i++){
//			System.out.println(util.convert(GB+i*MB*10));
//		}
//	}
	

}