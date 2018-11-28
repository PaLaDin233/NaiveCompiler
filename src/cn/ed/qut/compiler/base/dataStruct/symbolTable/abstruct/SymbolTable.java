/**
 * 抽象的符号表
 * @author 清居
 *
 */
package cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct;

import java.util.Map;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.DataType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;


public abstract class SymbolTable {

	//protected Map<SymbolItemIndex,SymbolTableItem> sMap;

	/**
	 * sMap的索引是符号的名称name
	 * 
	 */
	protected Map<String,SymbolTableItem> sMap;
	protected int intNum=0;//int变量的数量
	protected int stringNum=0;//string变量的数量


	public SymbolTable() {
		init();
	}
	public abstract void init();
	/**
	 * @return table
	 */

	/**
	 * 判断给定的名字是否是满足条件的符号类型（方法，常量，变量，参数)
	 * @param symbolType
	 * @param name
	 * @return
	 */
	protected boolean isType(SymbolType symbolType,String name){
		SymbolTableItem item=getSymbolTableItem(name);	
		if(item==null){
			return false;
		}
		return item.getSymbolType()==symbolType;
	}

	/**
	 * 判断给定的标识符是否是给定的数据类型
	 * @param dataType
	 * @param name
	 * @return
	 */
	protected boolean isType(DataType dataType,String name){
		SymbolTableItem item=getSymbolTableItem(name);	
		if(item==null){
			return false;
		}
		return item.getDataType()==dataType;
	}

	/**
	 * 判断该名字是否是要求的类型（判断name是否是dataType类型的symbolType）
	 * @param symbolType 符号类型
	 * @param dataType 数据类型
	 * @param name 要判断的名字
	 */
	protected boolean isType(SymbolType symbolType,DataType dataType,String name){
		return isType(dataType, name)&&isType(dataType, name);
	}

	public boolean isProc(String name){
		return isType(SymbolType.PROC, name);
	}
	public boolean isVar(String name){
		return isType(SymbolType.VAR, name);
	}
	public boolean isConst(String name){
		return isType(SymbolType.CONST, name);
	}

	public boolean isInt(String name){
		return isType(SymbolType.VAR,DataType.INT, name);
	}
	public boolean isString(String name){
		return isType(SymbolType.VAR,DataType.STRING, name);
	}

	//******************下面全部是扩展用的*************
	public boolean isFloat(String name){
		return isType(DataType.FLOAT, name);
	}
	public boolean isDouble(String name){
		return isType(DataType.DOUBLE, name);
	}

	public boolean isShort(String name){
		return isType(DataType.SHORT, name);
	}
	public boolean isByte(String name){
		return isType(DataType.BYTE, name);
	}
	public boolean isLong(String name){
		return isType(DataType.LONG, name);
	}
	public boolean isStruct(String name){
		return isType(DataType.STRUCT, name);
	}
	//**********************************************




	/**
	 * 获取符号表中的项
	 * @param name 项的名字
	 * @return
	 */
	public SymbolTableItem getSymbolTableItem(String name){
		return sMap.get(name);
	}

	/*	public SymbolTableItem getProc(String name){
		SymbolTableItem item=null;

		item=smap.get(name);
		if(item==null){
			map=sMap.get(SymbolTableItem.VOID_PROC);
			if(map==null)return null;
			item=map.get(name);
		}
		return item;
	}*/

	/**
	 * 获取该表中整型变量的个数
	 * @return
	 */
	public int getIntNum(){
		return this.intNum;//获取所有整型变量的Map,返回Map的size
	}
	public int getStringNum(){
		return this.stringNum;//获取字符串变量的Map,返回Map的size
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
		SymbolTableItem item=getSymbolTableItem(name);
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
	 * @param item 要插入的项
	 * @return
	 */
	public boolean insert(SymbolTableItem item){
		//获取符号表中是否存在
		if(!exist(item.getName())){
			//根据压入项的类型决定不同的操作
			if(item.getSymbolType()==SymbolType.VAR){//当插入的是一个变量时
				if(item.getDataType()==DataType.INT){//插入的是整型变量时
					intNum++;
				}
			}
			if(item.getDataType()==DataType.STRING){
				stringNum++;
			}
			spacialInsert(item);
			sMap.put(item.getName(), item);
			return true;
		}
		return false;
	}
	
	/**
	 * 子类插入子类特有项时进行的操作
	 * @param item
	 * @return
	 */
	public abstract void spacialInsert(SymbolTableItem item);
	
	/**
	 * 判断指定名字的标识符的名字是否在符号表中存在
	 * @param name 指定的标识符
	 * @return
	 */
	public boolean exist(String name){		
		return sMap.get(name)!=null;
	}


}
