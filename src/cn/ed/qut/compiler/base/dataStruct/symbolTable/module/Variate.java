package cn.ed.qut.compiler.base.dataStruct.symbolTable.module;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
/**
 * 变量类，包括程序定义的变量(全局变量，局部变量)，四元式生成的临时变量
 * @author 清居
 * 
 */
public class Variate {
//	private String name;//变量名
	private boolean waitUse=false;
	private boolean active=false;
	
	/**
	 * 变量存放的位置，寄存器或者是内存，寄存器的话是哪个寄存器<br>
	 * 值是-1时表示存放在内存中
	 */
	private List<Integer> location=new ArrayList<>();//

//	private SymbolTable symbolTable;//变量所在的符号表
	
	public Variate() {
	
	}
	/*public Variate(String name) {
		this.name = name;
	}
	
	public Variate(String name, SymbolTable symbolTable) {
		super();
		this.name = name;
		this.symbolTable = symbolTable;
	}*/
	/**
	 * 为变量添加存放位置
	 * @param register
	 */
	public void addLocation(Register register){
		location.add(register.getId());
	}
	


	/**
	 * 将变量分配给寄存器
	 * @param register 所分配的寄存器
	 */
	public void allotVariate(Register register){
		location.add(register.getId());//将寄存器加入变量位置列表
		//向变量的存储位置添加这个寄存器
		register.add(this);
		
	}
	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	
	/**
	 * 通过寄存器名，将寄存器中变量的存放位置列表移除
	 * @param regName
	 */
	public void removeFromAddress(int id){
		for (Integer i: location) {
			if(i.equals(id)){
				location.remove(i);
			}
		}
	}
	/**
	 * @return waitUse
	 */
	public boolean isWaitUse() {
		return waitUse;
	}

	/**
	 * 将待用信息取反
	 * 
	 */
	public void changeWaitUse() {
		waitUse = !waitUse;
	}

	/**
	 * @return active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * 将是否活跃信息取反
	 */
	public void changeActive() {
		active = !active;
	}
	
	@Override
	protected void finalize() throws Throwable {
		location.clear();
		location=null;
	}
}
