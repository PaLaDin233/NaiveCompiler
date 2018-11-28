package cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct;

import java.util.HashMap;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.DataType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

/**
 * 使用HashMap实现的符号表
 * @author 清居
 *
 */
public abstract class HashSymbolTable extends SymbolTable{

	@Override
	public void init() {
		sMap=new HashMap<>();
	}
	
	
	
}
