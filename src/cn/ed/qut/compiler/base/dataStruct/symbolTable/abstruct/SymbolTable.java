/**
 * 抽象的符号表
 * @author 清居
 *
 */
package cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct;

import java.util.Map;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.SymbolTableItem;


public abstract class SymbolTable {
	
	//protected Map<SymbolItemIndex,SymbolTableItem> sMap;
	
	/**
	 * sMap的索引是符号表项的type
	 * 获得的值是一个以符号表项name为索引的Map
	 */
	protected Map<Integer,Map<String,SymbolTableItem>> sMap;

	public SymbolTable() {
		init();
	}
	public abstract void init();
	/**
	 * @return table
	 */
	
	/**
	 * 判断该名字是否是要求的类型
	 * @param 要判断的名字
	 * @param 类型的值，见类型常量定义表
	 */
	public boolean isType(int type,String name){
		SymbolTableItem item=getSymbolTableItem(type, name);
		
		if(item!=null){
			return true;
		}
		
		return false;
	}
	
	public boolean isFunction(String name){
		return isType(SymbolTableItem.PROC, name)||isType(SymbolTableItem.VOID_PROC, name);
	}
	
	public boolean isInt(String name){
		return isType(SymbolTableItem.INT, name);
	}
	public boolean isString(String name){
		return isType(SymbolTableItem.STRING, name);
	}
	
	//******************下面全部是扩展用的*************
	public boolean isFloat(String name){
		return isType(SymbolTableItem.FLOAT, name);
	}
	public boolean isDouble(String name){
		return isType(SymbolTableItem.DOUBLE, name);
	}

	public boolean isShort(String name){
		return isType(SymbolTableItem.SHORT, name);
	}
	public boolean isByte(String name){
		return isType(SymbolTableItem.BYTE, name);
	}
	public boolean isLong(String name){
		return isType(SymbolTableItem.LONG, name);
	}
	public boolean isStruct(String name){
		return isType(SymbolTableItem.STRUCT, name);
	}
	//**********************************************
	
	public boolean isVar(String name){
		return 
				isInt(name)||isString(name)||
				isByte(name)||isDouble(name)||
				isFloat(name)||isShort(name)||
				isLong(name)||isStruct(name);
	}
	
	public boolean isConst(String name){
		return isType(SymbolTableItem.CONST, name);
	}
	
	public boolean isMainProc(String name){
		return isType(SymbolTableItem.MAIN_PROC, name);
	}
	
	
	/**
	 * 获取符号表中的项
	 * @param name 项的名字
	 * @param type 项的类型
	 * @return
	 */
	public SymbolTableItem getSymbolTableItem(int type,String name){
		SymbolTableItem item=null;
		Map<String,SymbolTableItem> map=sMap.get(type);
		item=map.get(name);
		
		return item;
	}
	
	public SymbolTableItem getProc(String name){
		SymbolTableItem item=null;
		Map<String,SymbolTableItem> map=sMap.get(SymbolTableItem.PROC);
		item=map.get(name);
		if(item==null){
			map=sMap.get(SymbolTableItem.VOID_PROC);
			item=map.get(name);
		}
		return item;
	}
	
	/**
	 * 获取该表中整型变量的个数
	 * @return
	 */
	public int getIntNum(){
		return sMap.get(SymbolTableItem.INT).size();//获取所有整型变量的Map,返回Map的size
	}
	public int getStringNum(){
		return sMap.get(SymbolTableItem.STRING).size();//获取字符串变量的Map,返回Map的size
	}
	public int getVarNum(){
		return getIntNum()+getStringNum();
	}
	
	
	/**
	 * 获取方法中局部变量的个数
	 * @param funcName 方法名
	 * @return 方法中整型局部变量的个数,不是方法则返回-1
	 */
	public int getIntNum(String name){
		
		//获取该方法的项
		SymbolTableItem item=getProc(name);
		if(item==null){//该符号不是方法
			return -1;
		}
		//获取方法指向的符号表
		SymbolTable table=item.getSymbolTable();
		//获取符号表中的变量个数
		return table.getIntNum();
	}
	

	
	
	
	/**
	 * 将一个符号表项插入符号表
	 * @param item
	 * @return
	 */
	public abstract boolean insert(SymbolTableItem item);
	
	public boolean exist(SymbolTableItem item){
		if(sMap.get(item.getType())==null)return false;
		
		return sMap.get(item.getType()).get(item.getName())!=null;
	}
	
	
}
