package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;

/**
 * 全局符号表类<br>
 * 
 * @author 清居
 * @version 0.1
 * @func
 * 使用单例模式，同一个时间段内只允许有一个对象存在
 */
public class GlobalSymbolTable extends HashSymbolTable{
	//volatile用于多线程编程，也就是为了下面的线程安全，虽然这个项目多线程并没有用到，但是万一以后升级为并行编译了呢
	private static volatile GlobalSymbolTable globalSymbolTable;
	
	private GlobalSymbolTable() {}
	/**
	 * 线程安全
	 * 
	 * 获取符号表的对象
	 * @return 该类的唯一对象
	 */
	public static GlobalSymbolTable getSymbolTable(){
		if (globalSymbolTable==null) {
			synchronized (GlobalSymbolTable.class) {
				if (globalSymbolTable==null) {
					globalSymbolTable=new GlobalSymbolTable();
				}
			}	
		}
		return globalSymbolTable;
	}
	
}
