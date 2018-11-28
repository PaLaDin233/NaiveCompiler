package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;
/**
 * MIPS的全局变量区
 * @author minecraft
 * 在asm文件中的表示方式
 * 
 * .data
 * 		var1:	.word	3	# 声明一个 word 类型的变量 var1, 同时给其赋值为 3
 * 		array1:	.byte	'a','b'	# 声明一个存储2个字符的数组 array1，并赋值 'a', 'b'
 * 		array2:	.space	40	# 为变量 array2 分配 40字节（bytes)未使用的连续空间
 */

public class MIPSData {
	private String name;//数据名
	private String type;//数据类型
	private String value;//数据值
	
	private static final String BYTE=".byte";
	private static final String SPACE=".space";
	private static final String WORD=".word";
	private static final String ASCII=".asciiz";
	private static final String[] LEGAL_DATA_TYPE={BYTE,SPACE,WORD,ASCII};
	public MIPSData() {
		
	}
	/**
	 * 
	 * @param name	数据名
	 * @param type	数据类型
	 * @param value	数据值
	 * @example
	 * new MIPSData("var1",".word","3");	#声明一个 word 类型的变量 var1, 同时给其赋值为 3<br>
	 * new MIPSData("var2",".word","4,5");<br>
	 * new MIPSData("array1",".byte","\'a\',\'b\'");	# 声明一个存储2个字符的数组 array1，并赋值 'a', 'b'<br>
	 * new MIPSData("array2",".space","40");	#为变量 array2 分配 40字节（bytes)未使用的连续空间<br>
	 */
	public MIPSData(String name,String type,String value){
		setName(name);
		setType(type);
		setValue(value);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if(islegalArgument(type))
			this.type = type;
		else{
			throw new IllegalArgumentException();
		}
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 判断参数是否是一种数据类型
	 * @param arg 要判断的字符串
	 * @return	是已经定义的数据类型返回真，否则返回假
	 */
	private boolean islegalArgument(String arg){
		arg=arg.trim();
		for (String string : LEGAL_DATA_TYPE) {
			if(arg.equals(string))return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			new MIPSData().setType(".ascii");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
