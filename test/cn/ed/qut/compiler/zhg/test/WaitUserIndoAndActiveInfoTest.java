package cn.ed.qut.compiler.zhg.test;

import java.util.ArrayList;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.BaseBlock;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.BaseBlockSymbolTable;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.ObjectCodeGenerater;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips.MIPSGenerator;
import cn.ed.qut.compiler.zhg.tools.FileReadUtil;

public class WaitUserIndoAndActiveInfoTest {
	public static void main(String[] args) throws Exception {
		/*SymbolTable symbolTable=GlobalSymbolTable.getSymbolTable();
		
		symbolTable.insert(SymbolTableItem.getIntVar("a"));
		
		System.out.println(symbolTable.exist("a"));
		System.out.println(symbolTable.exist(null));*/
		FourElement element1=new FourElement(1,"-","A","B","T1");
		FourElement element2=new FourElement(2,"-","A","C","T2");
		FourElement element3=new FourElement(3,"+","T1","T2","T3");
		FourElement element4=new FourElement(4,"+","T3","T2","D");
		ArrayList<FourElement>arrayList=new ArrayList<>();
		arrayList.add(element1);
		arrayList.add(element2);
		arrayList.add(element3);
		arrayList.add(element4);
		
		GlobalSymbolTable.getSymbolTable().insert(SymbolTableItem.getIntVar("A"));
		GlobalSymbolTable.getSymbolTable().insert(SymbolTableItem.getIntVar("B"));
		GlobalSymbolTable.getSymbolTable().insert(SymbolTableItem.getIntVar("C"));
		GlobalSymbolTable.getSymbolTable().insert(SymbolTableItem.getIntVar("D"));
		
	
		

		/*baseBlockSymbolTable=new BaseBlockSymbolTable(baseBlock);
		
		baseBlockSymbolTable.print();*/
		
		ObjectCodeGenerater codeGenerater=new MIPSGenerator(arrayList);

		for (FourElement fourElement : arrayList) {
			System.out.println(fourElement);
		}
		codeGenerater.generator();
		
		FileReadUtil.sysoFile("output/target.txt");
		
		
	}
}
