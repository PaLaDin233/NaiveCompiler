package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.DataType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.RegisterAllocator;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.SymbolTableStack;

/**
 * MIPS指令集下的寄存器分配器
 * @author 清居
 *
 */

public class MIPSRegisterAllocator extends RegisterAllocator {

	
	@Override
	protected void init() {
		for(int i=0;i<32;i++){
			registerList.add(new MIPSRegister(i));
		}
		
	}
	

	/**
	 * element:(i:A=B op C)
	 * GETREG(i:A=B op C)
	 */
	
	@Override
	public String getRegister(FourElement element) {
		String arg1=element.getArg1();
		String arg2=element.getArg2();
		String res=(String) element.getResult();
		String regName=null;
		
		SymbolTableItem b=SymbolTableStack.getItem(arg1);
		SymbolTableItem c=SymbolTableStack.getItem(arg2);
		SymbolTableItem a=SymbolTableStack.getItem(res);
		
		//TODO element:(i:A=B op C)
		//(1)如果B的现行值在寄存器R中，且该寄存器只包含B的值，
		//   或者B和A是同一标识符，或者B在四元式之后不再被引用，则选取Ri为所需寄存器，转（4）
			//判断B是否存在与符号表
			if(b!=null){//当B存在时
				//当B是变量
				if(b.getSymbolType()==SymbolType.VAR){
					Variate bInfo=b.getVariate();
					//TODO 
					
				}
			}
			
			
		
		//(2)如有尚未分配的寄存器，则从中选取一个Ri,为所需寄存器,转(4)
			regName=getRegister();
			if(regName!=null)return regName;
		//(3)从已分配的寄存器中选取一个Ri作为所需的寄存器R，选取原则为，占用Ri的变量的值也同时存放在主存中
		//	或者在基本块中要在最远的位置才会引用到。这样，对寄存器Ri所含变量做如下调整
		//		对RVALUE[R]（R寄存器分配的变量列表）中的每一个变量M,如果M不是A且AVALUE[M]
		//	(变量M所在的位置)，不含M。则
		//  1.生成目标代码ST Ri，M（将寄存器Ri的内容取到M）
		//  2.如果M是B，则令AVALUE[M]={M,Ri}否则令AVALUE[M]={M}
		//	3.删除RVALUE[Ri]中的M
		//(4)给出R，返回
		return regName;
	}
	
	@Override
	public String getRegister(String source, FourElement element) {
		//获取当前符号表
		SymbolTable symbolTable=SymbolTableStack.peek();
		//获取操作数1的存放位置
		//获取操作数2的存放位置
		
		return null;
	}


	//获取一个空闲的寄存器
	@Override
	public String getRegister() {
		for (Register register : registerList) {
			if(register.getAllocatVar()==null||register.getAllocatVar().size()==0){
				return register.getName();
			}
		}
		
		return null;
	}


	

	

	

}
