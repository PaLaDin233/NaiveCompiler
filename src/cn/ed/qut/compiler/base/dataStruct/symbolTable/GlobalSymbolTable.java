package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

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
	private int procNum=0;
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
	/**
	 * 获取方法funcName第index个参数的符号表项
	 * @param index 参数的index
	 * @param funName 方法的name
	 * @return
	 */
	public SymbolTableItem getPara(int index,String funName){
		//获取方法项
		SymbolTableItem func=getSymbolTableItem(funName);
		if(func==null||func.getSymbolType()!=SymbolType.PROC)return null;

		//获取方法项的符号表,再获取参数
		return func.getSymbolTable().getPara(index);

	}
	@Override
	public void spacialInsert(SymbolTableItem item) {
		if(item.getSymbolType()==SymbolType.PROC){
			procNum++;
		}
	}
	/**
	 * @return procNum
	 */
	public int getProcNum() {
		return procNum;
	}

	
}
