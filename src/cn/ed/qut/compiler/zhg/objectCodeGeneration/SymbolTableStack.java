package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.Stack;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;

public class SymbolTableStack {
	private static Stack<SymbolTable> tableStack=new Stack<>();
	private SymbolTableStack(){
		//将全局符号表放入栈中
		tableStack.push(GlobalSymbolTable.getSymbolTable());
	}
	
	public static SymbolTable peek(){
		return tableStack.peek();
	}
	
	public static void push(SymbolTable symbolTable){
		tableStack.push(symbolTable);
	}
	public static SymbolTable pop(){
		if(tableStack.peek()!=GlobalSymbolTable.getSymbolTable())
		return tableStack.pop();
		else return tableStack.peek();
	}
}
