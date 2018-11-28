package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.ArrayList;
import java.util.List;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

/**
 * 分配寄存器抽象类
 * 子类功能：对具体指令集下的寄存器进行分配
 * @author 清居
 *
 */
public abstract class RegisterAllocator {
	protected List<Register> registerList=new ArrayList<>();//寄存器列表
	
	
	public RegisterAllocator() {
		init();
	}
	
	/**
	 * 对分配器进行初始化，添加寄存器
	 */
	protected abstract void init();
	
	/**
	 * 
	 * @param source 分配的来源
	 * @param element 四元式
	 * @return 分配的寄存器
	 */
	public abstract String getRegister(String source,FourElement element);
	
	/**
	 * 
	 * @return 未被使用的通用寄存器的寄存器名称,如MIPS下的$s0
	 */
	public abstract String getRegister(String source);

	public String getRegister(FourElement element) {
		// TODO 自动生成的方法存根
		return null;
	}


	
	
}
