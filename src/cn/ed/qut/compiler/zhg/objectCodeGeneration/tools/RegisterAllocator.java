package cn.ed.qut.compiler.zhg.objectCodeGeneration.tools;

import java.util.List;
import java.util.Map;

/**
 * 分配寄存器抽象类
 * 子类功能：对具体指令集下的寄存器进行分配
 * @author 清居
 *
 */
public abstract class RegisterAllocator {
	protected List<String> registerList;//寄存器列表
	
	
	/**
	 * 
	 * @param source 分配的来源
	 * @return 分配的寄存器
	 */
	public abstract String getRegister(String source);
	
	/**
	 * 
	 * @return 未被使用的寄存器
	 */
	public abstract String getRegister();


	/**
	 * 
	 * @return 寄存器的列表
	 */
	public List<String> getRegisterList() {
		return registerList;
	}

	/**
	 * 设置寄存器列表
	 * @param registerList 寄存器名列表
	 */
	public void setRegisterList(List<String> registerList) {
		this.registerList = registerList;
	}
	
	
	
}
