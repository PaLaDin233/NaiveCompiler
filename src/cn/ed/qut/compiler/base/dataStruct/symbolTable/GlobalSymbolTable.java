package cn.ed.qut.compiler.base.dataStruct.symbolTable;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

/**
 * ȫ�ַ��ű���<br>
 * 
 * @author ���
 * @version 0.1
 * @func
 * ʹ�õ���ģʽ��ͬһ��ʱ�����ֻ������һ���������
 */
public class GlobalSymbolTable extends HashSymbolTable{
	//volatile���ڶ��̱߳�̣�Ҳ����Ϊ��������̰߳�ȫ����Ȼ�����Ŀ���̲߳�û���õ���������һ�Ժ�����Ϊ���б�������
	private static volatile GlobalSymbolTable globalSymbolTable;
	private int procNum=0;
	private GlobalSymbolTable() {}
	/**
	 * �̰߳�ȫ
	 * 
	 * ��ȡ���ű�Ķ���
	 * @return �����Ψһ����
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
	 * ��ȡ����funcName��index�������ķ��ű���
	 * @param index ������index
	 * @param funName ������name
	 * @return
	 */
	public SymbolTableItem getPara(int index,String funName){
		//��ȡ������
		SymbolTableItem func=getSymbolTableItem(funName);
		if(func==null||func.getSymbolType()!=SymbolType.PROC)return null;

		//��ȡ������ķ��ű�,�ٻ�ȡ����
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
