package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

/**
 * �������ű�,�洢�����еı�ʶ��
 * @author ���
 *
 */
public class ProcSymbolTable extends HashSymbolTable{
	private int paraNum=0;
	/**
	 * �Ƿ��ǲ���
	 * @param name
	 * @return
	 */
	public boolean isPara(String name){
		return isType(SymbolType.PARA, name);
	}
	/**
	 * ��ȡ�÷��ű��в����ĸ���
	 * @return
	 */
	public int getParaNum(){
		return paraNum;
	}
	
	
	

	
	
	/**
	 * ��ȡ��index�������ķ��ű���
	 * @param index ������index
	 * @return ����������ű���
	 */
	public SymbolTableItem getPara(int index){
		//����ÿһ���������鿴����ֵ�Ƿ���index
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
