package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

/**
 * 方法符号表,存储方法中的标识符
 * @author 清居
 *
 */
public class ProcSymbolTable extends HashSymbolTable{
	private int paraNum=0;
	/**
	 * 是否是参数
	 * @param name
	 * @return
	 */
	public boolean isPara(String name){
		return isType(SymbolType.PARA, name);
	}
	/**
	 * 获取该符号表中参数的个数
	 * @return
	 */
	public int getParaNum(){
		return paraNum;
	}
	
	
	

	
	
	/**
	 * 获取第index个参数的符号表项
	 * @param index 参数的index
	 * @return 参数这个符号表项
	 */
	public SymbolTableItem getPara(int index){
		//遍历每一个参数，查看它的值是否是index
		for (String key: sMap.keySet()) {
			SymbolTableItem temp=sMap.get(key);
			if(temp.getSymbolType()==SymbolType.PARA){
				if(temp.getValue().equals(index)){
					return temp;
				}
			}	
		}
		return null;
	}
	
	public void spacialInsert(SymbolTableItem item) {
		if(item.getSymbolType()==SymbolType.PARA){
			paraNum++;
		}
	}

}
