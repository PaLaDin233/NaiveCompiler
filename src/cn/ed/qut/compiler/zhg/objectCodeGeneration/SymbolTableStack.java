package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.Stack;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;

public class SymbolTableStack {
	private static Stack<SymbolTable> tableStack=new Stack<>();
	private SymbolTableStack(){
		//将全局符号表放入栈中
		tableStack.push(GlobalSymbolTable.getSymbolTable());
	}
	
	/**
	 * 获取栈顶符号表，不弹栈
	 * @return
	 */
	public static SymbolTable peek(){
		return tableStack.peek();
	}
	
	/**
	 * 将符号表压入栈
	 * @param symbolTable 需要压入的符号表
	 */
	public static void push(SymbolTable symbolTable){
		tableStack.push(symbolTable);
	}
	
	/**
	 * 从栈顶弹出一个符号表，当栈中只剩一个符号表时，不进行弹栈
	 * @return
	 */
	public static SymbolTable pop(){
		if(tableStack.peek()!=GlobalSymbolTable.getSymbolTable())
		return tableStack.pop();
		else return tableStack.peek();
	}
	/**
	 * 根据标识符名获取标识符在符号表中的符号表项
	 * @param name 标识符的名称
	 * @return
	 */
	public static SymbolTableItem getItem(String name){
		for(int i=tableStack.size()-1;i>=0;i--){//从栈顶向下找,当存在满足要求的标识符时，返回改符号表项，否则继续往上层符号表找
			SymbolTableItem tem=tableStack.get(i).getSymbolTableItem(name);
			if(tem!=null&&tem.getSymbolType()==SymbolType.VAR){
				return tableStack.get(i).getSymbolTableItem(name);
			}
		}
		return null;
	}
}
