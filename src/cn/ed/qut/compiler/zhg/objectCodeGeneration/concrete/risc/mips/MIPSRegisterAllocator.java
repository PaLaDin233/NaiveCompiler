package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;

import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.RegisterAllocator;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.Variate;

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
	
	@Override
	public String getRegister(String source, FourElement element) {
		
		return null;
	}

	@Override
	public String getRegister(String source) {
		for (int i=16;i<24;i++) {	
			if(registerList.get(i).isFree()){
				registerList.get(i).add(new Variate(source));
				return MIPSRegister.regNameList.get(i);
			}
		}
		return null;
	}

	

}
