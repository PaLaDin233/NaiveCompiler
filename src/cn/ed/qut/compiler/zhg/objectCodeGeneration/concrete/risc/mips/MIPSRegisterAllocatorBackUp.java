package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.DataType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.BaseBlock;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.RegisterAllocator;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.SymbolTableStack;

/**
 * MIPS指令集下的寄存器分配器
 * @author 清居
 *
 */

public class MIPSRegisterAllocatorBackUp extends RegisterAllocator {


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
	/*static private int id=0;
	@Override
	public String getRegister(FourElement element) {
		return "$s"+id++;
	}*/
	@Override
	public Register getRegister(FourElement element) {
		String arg1=element.getArg1();
		String arg2=element.getArg2();
		String res=(String) element.getResult();
		Register register=null;
		Register registerB=null;
		Register registerC=null;

		SymbolTableItem itemB=SymbolTableStack.getItem(arg1);
		SymbolTableItem itemC=SymbolTableStack.getItem(arg2);
		SymbolTableItem itemA=SymbolTableStack.getItem(res);

		//TODO element:(i:A=B op C)
		//(1)如果B的现行值在寄存器R中，且该寄存器只包含B的值，
		//   或者B和A是同一标识符，或者B在四元式之后不再被引用，则选取Ri为所需寄存器，转（4）
		//判断B是否存在于符号表
		if(itemB!=null){//当B存在时
			//当B是定义的变量
			if(itemB.getSymbolType()==SymbolType.VAR){
				Variate bInfo=itemB.getVariate();
				//TODO 
				for(Integer localtion: bInfo.getLocation()){
					if(localtion>0){
						registerB=registerList.get(localtion);
						if(registerB.getAllocatVar().size()==1){
							break;
						}
					}
				}
				if(registerB!=null
						&&registerB.getAllocatVar().size()==1
						||itemB.equals(itemA)){
					//B的现行值在寄存器registerB中，该寄存器只包含B
					return registerB;
				}
			}

		}



		//(2)如有尚未分配的寄存器，则从中选取一个Ri,为所需寄存器,转(4)
		register=getRegister();
		if(register!=null)return register;
		//(3)从已分配的寄存器中选取一个Ri作为所需的寄存器R，选取原则为，占用Ri的变量的值也同时存放在主存中
				//	或者在基本块中要在最远的位置才会引用到。这样，对寄存器Ri所含变量做如下调整
				//		对RVALUE[R]（R寄存器分配的变量列表）中的每一个变量M,如果M不是A且AVALUE[M]
				//	(变量M所在的位置)，不含M。则
				//  1.生成目标代码ST Ri，M（将寄存器Ri的内容取到M）
				//  2.如果M是B，则令AVALUE[M]={M,Ri}否则令AVALUE[M]={M}
				//	3.删除RVALUE[Ri]中的M
				for(Register reg:registerList){//遍历寄存器列表获取目标寄存器
					boolean flag=false;
					for (Variate variate : reg.getAllocatVar()) {//获取寄存器分配的变量
						flag=false;
						if(!variate.getLocation().contains(-1)){//当这个变量不存在内存中时,放弃使用该寄存器
							break;
						}
						flag=true;
					}
					if(flag){//选择该寄存器
						//对RVALUE[R]（R寄存器分配的变量列表）中的每一个变量M,如果M不是A且AVALUE[M](变量M所在的位置)，不含M。则
						//  1.生成目标代码ST Ri，M（将寄存器Ri的内容取到M）
						
						//  2.如果M是B，则令AVALUE[M]={M,Ri}否则令AVALUE[M]={M}
						
						//	3.删除RVALUE[Ri]中的M
						
					}
				}
		//(3)在目标代码生成器中实现


		//(4)给出R，返回
		return register==null?null:register;
	}

	@Override
	public Register getRegister(String target, FourElement element,Register returnR) {
		//获取当前符号表
		SymbolTable symbolTable=SymbolTableStack.peek();
		SymbolTableItem item=null;
		//获取目标变量
		item=SymbolTableStack.getItem(target);
		
		return null;
	}


	//获取一个空闲的寄存器
	@Override
	public Register getRegister() {
		for(int i=16;i<24;i++){
			Register register=registerList.get(i);
			if(register.getAllocatVar()==null||register.getAllocatVar().size()==0){
				return register;
			}
		}

		return null;
	}


	/**
	 * 为变量分配寄存器
	 * @param variate 带分配的变量
	 * @return 所分配的寄存器
	 */
	public Register allocatorRigister(Variate variate){
		Register register=null;



		return register;
	}


	@Override
	public Register getRegisterIfNoFreeRegister(FourElement fourElement) {
		// TODO 自动生成的方法存根
		return null;
	}





}
