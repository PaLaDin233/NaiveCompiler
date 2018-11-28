package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;

public class MIPSRegister extends Register{

	public MIPSRegister(int id) {
		super(id);
		// TODO 自动生成的构造函数存根
	}
	
	public MIPSRegister(String name) {
		super(name);
		// TODO 自动生成的构造函数存根
	}


	public void init(){
		regNameList=new ArrayList<>();
		regNameList.add("$Zero");
		regNameList.add("$at");
		regNameList.add("$v0");
		regNameList.add("$v1");
		for(int i=0;i<4;i++){
			regNameList.add("$a"+i);
		}
		for(int i=0;i<8;i++){
			regNameList.add("$t"+i);
		}
		for(int i=0;i<8;i++){
			regNameList.add("$s"+i);
		}
		regNameList.add("$t8");
		regNameList.add("$t9");
		regNameList.add("$k0");
		regNameList.add("$k1");
		regNameList.add("$gp");
		regNameList.add("$sp");
		regNameList.add("$fp");
		regNameList.add("$ra");
		
	}

	
}
