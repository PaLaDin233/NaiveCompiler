package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;

public class SymbolTableItem {
	
	
	
	
	private int type;//类型
	private String name;//名称
	private int value;//常量的值,或者是参数的index
	private SymbolTable symbolTable;//如果是方法，方法对应的局部符号表
	
	//下面是符号表项类型 常量声明
	/**
	 * 主函数
	 */
	public static final int MAIN_PROC=0;
	
	/**
	 * 常量
	 */
	public static final int CONST=1;
	
	/**
	 * 整型变量
	 */
	public static final int INT=2;
	
	/**
	 * 字符串
	 */
	public static final int STRING=3;
	
	/**
	 * 参数
	 */
	public static final int PARA=4;	
	/**
	 * 无返回值的过程
	 */
	public static final int VOID_PROC=5;
	
	/**
	 *带返回值的过程 
	 */
	public static final int PROC=6;
	
	//扩展的，未实现
	public static final int FLOAT = 7;
	public static final int DOUBLE = 8;
	public static final int SHORT = 9;
	public static final int LONG = 10;
	public static final int BYTE = 11;
	public static final int STRUCT=12;//结构体
	private SymbolTableItem(){}
	
	//工厂模式（笑
	/**
	 * 获取一个无返回值的方法符号表项实例（对象）<br>
	 * @param name 方法名
	 * @return
	 */
	public static SymbolTableItem getReturnVoidFuncItem(String name){
		SymbolTableItem item=getFuncItemNoType(name);
		item.setType(VOID_PROC);
		return item;
	}
	/**
	 * 获取一个带返回值的方法符号表项实例（对象）<br>
	 * @param name 方法名
	 * @return
	 */
	public static SymbolTableItem getFuncItem(String name){
		SymbolTableItem item=getFuncItemNoType(name);
		item.setType(PROC);
		return item;
	}
	
	/**
	 * 返回一个不带类型的符号表项
	 * @param name 符号名称
	 * @return
	 */
	private static SymbolTableItem getItemNoType(String name){
		SymbolTableItem item=new SymbolTableItem();
		item.setName(name);
		return item;
	}
	
	/**
	 * 获取一个初始化的方法项，类型还未设置
	 * 为方法项设置局部符号表
	 * @param name 方法名
	 * @return
	 */
	private static SymbolTableItem getFuncItemNoType(String name){
		SymbolTableItem item=getItemNoType(name);
		//为方法设置局部符号表
		item.setTempSymbolTable(new TempSymbolTable() );
		return item;
	}
	
	/**
	 * 获取一个整型变量符号表项的实例化对象
	 * @param name 变量名
	 * @return
	 */
	public static SymbolTableItem getIntItem(String name){
		SymbolTableItem item=getItemNoType(name);
		item.setType(INT);		
		return item;
	}
	
	/**
	 * 获取一个常量符号表项的实例化对象
	 * @param name 常量名
	 * @param value 常量值
	 * @return
	 */
	public static SymbolTableItem getConstItem(String name,int value){
		SymbolTableItem item=getItemNoType(name);
		item.setType(CONST);		
		return item;
	}
	
	/**
	 * 获取一个主函数符号表项的实例化对象
	 * @return
	 */
	public static SymbolTableItem getMainProcItem(){
		SymbolTableItem item=getFuncItemNoType("main");
		item.setType(MAIN_PROC);		
		return item;
	}
	
	/**
	 * 根据参数名列表获得一个参数项列表
	 * @param names 参数名列表
	 * @return
	 */
	public static List<SymbolTableItem> getParaItem(List<String> names){
		
		List<SymbolTableItem> items=new ArrayList<>();
		
		int i=0;
		for (String name : names) {
			SymbolTableItem item=getFuncItemNoType(name);
			item.setType(PARA);
			item.setValue(i++);
			items.add(item);
		}
		
		return items;
	}
	
	/**
	 * 获取一个字符串变量符号表项的实例化对象
	 * @param name 字符串变量的变量名
	 * @return
	 */
	public static SymbolTableItem getStringItem(String name){
		SymbolTableItem item=getItemNoType(name);
		item.setType(STRING);		
		return item;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}
	public void setTempSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}
	
	
	
	
	
}
