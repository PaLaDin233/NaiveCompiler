package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.Stack;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

public class SymbolTableStack {
	private static Stack<SymbolTable> tableStack=new Stack<>();
	private SymbolTableStack(){
		//��ȫ�ַ��ű����ջ��
		tableStack.push(GlobalSymbolTable.getSymbolTable());
	}
	
	/**
	 * ��ȡջ�����ű�����ջ
	 * @return
	 */
	public static SymbolTable peek(){
		return tableStack.peek();
	}
	
	/**
	 * �����ű�ѹ��ջ
	 * @param symbolTable ��Ҫѹ��ķ��ű�
	 */
	public static void push(SymbolTable symbolTable){
		tableStack.push(symbolTable);
	}
	
	/**
	 * ��ջ������һ�����ű���ջ��ֻʣһ�����ű�ʱ�������е�ջ
	 * @return
	 */
	public static SymbolTable pop(){
		if(tableStack.peek()!=GlobalSymbolTable.getSymbolTable())
		return tableStack.pop();
		else return tableStack.peek();
	}
	/**
	 * ���ݱ�ʶ������ȡ��ʶ���ڷ��ű��еķ��ű���
	 * @param name ��ʶ��������
	 * @return
	 */
	public static SymbolTableItem getItem(String name){
		for(int i=tableStack.size()-1;i>=0;i--){//��ջ��������,����������Ҫ��ı�ʶ��ʱ�����ظķ��ű������������ϲ���ű���
			SymbolTableItem tem=tableStack.get(i).getSymbolTableItem(name);
			if(tem!=null&&tem.getSymbolType()==SymbolType.VAR){
				return tableStack.get(i).getSymbolTableItem(name);
			}
		}
		return null;
	}
}
