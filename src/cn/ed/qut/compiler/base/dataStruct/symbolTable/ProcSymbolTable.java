package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import java.util.Map;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;

/**
 * 方法符号表,存储方法中的标识符
 * @author 清居
 *
 */
public class ProcSymbolTable extends HashSymbolTable{
	/**
	 * 是否是参数
	 * @param name
	 * @return
	 */
	public boolean isPara(String name){
		return isType(SymbolTableItem.PARA, name);
	}
	/**
	 * 获取该符号表中参数的个数
	 * @return
	 */
	public int getParaNum(){
		return sMap.get(SymbolTableItem.PARA).size();//获取所有变量的Map,返回Map的size
	}
	
	
	
	/**
	 * 根据函数名获取函数的参数个数
	 * @param name
	 * @return 如果不是方法，返回-1
	 */
	public int getParaNum(String name){
		//获取name对应的方法
		ProcSymbolTable item=(ProcSymbolTable) getProc(name).getSymbolTable();
		//存在方法则返回方法对应的符号表中参数的个数
		if(item!=null&&item instanceof ProcSymbolTable){
			return ((ProcSymbolTable)item).getParaNum();
		}
		return -1;	
	}
	
	
	/**
	 * 获取第index个参数的符号表项
	 * @param index 参数的index
	 * @return 参数这个符号表项
	 */
	public SymbolTableItem getPara(int index){
		//获取所有参数的Map
		Map<String,SymbolTableItem>map=sMap.get(SymbolTableItem.PARA);
		//遍历每一个参数，查看它的值是否是index
		SymbolTableItem item=null;
		for (String key: map.keySet()) {
			if(map.get(key).getValue()==index){
				item=map.get(key);
				break;
			}
			
		}
		return item;
	}
	
	/**
	 * 获取方法funcName第index个参数的符号表项
	 * @param index 参数的index
	 * @param funName 方法的name
	 * @return
	 */
	public SymbolTableItem getPara(int index,String funName){
		//获取方法项
		SymbolTableItem func=getProc(funName);
		if(func==null)return null;

		//获取方法项的符号表,再获取参数
		return func.getSymbolTable().getPara(index);

	}

}
