package cn.ed.qut.compiler.base.dataStruct.symbolTable.module;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.ProcSymbolTable;

public class SymbolTableItem {
	
	
	
	
	private SymbolType type;//符号类型：有四种取值，常量，变量，过程,参数
	private String name;//名称
	private Object value;//常量的值,或者是参数的index
	private DataType dataType;//参变量参数的数据类型（函数返回值类型），int,char,string,short....
	private ProcSymbolTable symbolTable;//如果是方法，方法对应的局部符号表
	private Variate variate;//如果是变量，和变量相关的变量信息

	private SymbolTableItem(){}
	
	/**
	 * 不建议使用该构造方法
	 * @param type
	 * @param name
	 * @param value
	 * @param dataType
	 */
	public SymbolTableItem(SymbolType type, String name, int value, DataType dataType) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.dataType = dataType;
	}


	//工厂模式（笑
	
	
	
	/**
	 * 获取一个初始的符号表项，只为该项命名
	 * 返回一个规定name的不带类型的符号表项
	 * @param name 符号名称
	 * @return
	 */
	private static SymbolTableItem getItemNoType(String name){
		SymbolTableItem item=new SymbolTableItem();
		item.setName(name);
		return item;
	}
	
	
	
	/**
	 * 获取一个初始化的方法项，返回值类型还未设置
	 * 为方法项设置局部符号表
	 * @param name 方法名
	 * @return
	 */
	private static SymbolTableItem getProcItemNoReturnType(String name){
		SymbolTableItem item=getItemNoType(name);
		//这是项的类型是方法
		item.setSymbolType(SymbolType.PROC);
		//为方法设置局部符号表
		item.setTempSymbolTable(new ProcSymbolTable() );
		return item;
	}
	
	
	/**
	 * 获取一个规定返回值的方法符号表项实例（对象）<br>
	 * @param name 方法名
	 * @param dataType 返回的数据类型
	 * @return 生成的符号表项
	 */
	public static SymbolTableItem getProcItemWithDataType(String name,DataType dataType){
		SymbolTableItem item=getProcItemNoReturnType(name);
		item.setDataType(dataType);
		return item;
	}
	
	
	/**
	 * 获取一个无类型的变量
	 * @param name 变量名
	 * @return
	 */
	private static SymbolTableItem getVar(String name){
		SymbolTableItem item=getItemNoType(name);
		item.setSymbolType(SymbolType.VAR);
		item.setVariate(new Variate());
		return item;
	}
	
	/**
	 * 获取一个指定数据类型的变量
	 * @param name
	 * @return
	 */
	public static SymbolTableItem getVar(String name,DataType dataType){
		SymbolTableItem item=getVar(name);
		item.setDataType(dataType);
		return item;
	}
	
	/**
	 * 获取一个整型变量符号表项的实例化对象
	 * @param name 变量名
	 * @return
	 */
	public static SymbolTableItem getIntVar(String name){
		return getVar(name, DataType.INT);
	}
	
	/**
	 * 获取一个常量符号表项的实例化对象
	 * @param name 常量名
	 * @param value 常量值
	 * @return
	 */
	public static SymbolTableItem getConst(String name,int value){
		SymbolTableItem item=getItemNoType(name);
		item.setSymbolType(SymbolType.CONST);		
		return item;
	}
	
//	/**
//	 * 获取一个主函数符号表项的实例化对象
//	 * @return
//	 */
//	public static SymbolTableItem getMainProcItem(){
//		SymbolTableItem item=getProcItemNoType("main");
//		item.setType(MAIN_PROC);		
//		return item;
//	}


	/**
	 * 获取一个参数项
	 * @param dataType 参数的数据类型
	 * @param name 参数名
	 * @param index 第几个参数
	 * @return
	 */
	public static SymbolTableItem getPara(DataType dataType,String name,int index){
		SymbolTableItem item=getItemNoType(name);
		item.setSymbolType(SymbolType.PARA);
		item.setValue(index);
		item.setDataType(dataType);
		return item;
	}
	
	/**
	 * 获取一个字符串变量符号表项的实例化对象
	 * @param name 字符串变量的变量名
	 * @return
	 */
	public static SymbolTableItem getStringItem(String name){
		SymbolTableItem item=getVar(name);
		item.setDataType(DataType.STRING);		
		return item;
	}
	
	
	
	
	
	
	
	
	
	
	public SymbolType getSymbolType() {
		return type;
	}
	public void setSymbolType(SymbolType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	public ProcSymbolTable getSymbolTable() {
		return symbolTable;
	}
	public void setTempSymbolTable(ProcSymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	/**
	 * @return dataType
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * @param dataType要设置的 dataType
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public Variate getVariate() {
		return variate;
	}

	public void setVariate(Variate variate) {
		this.variate = variate;
	}
	
	
	
	
	
}
