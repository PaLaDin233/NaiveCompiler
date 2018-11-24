package cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct;

import java.util.HashMap;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.SymbolTableItem;

/**
 * 使用HashMap实现的符号表
 * @author 清居
 *
 */
public abstract class HashSymbolTable extends SymbolTable{

	@Override
	public boolean insert(SymbolTableItem item){
		//获取符号表中是否存在
		if(!exist(item)){
			if(sMap.get(item.getType())==null){//当这个类型是第一次出现时
				sMap.put(item.getType(),new HashMap<>());
			}
			sMap.get(item.getType()).put(item.getName(), item);
			return true;
		}
		return false;
	}
	@Override
	public void init() {
		sMap=new HashMap<>();
	}
}
