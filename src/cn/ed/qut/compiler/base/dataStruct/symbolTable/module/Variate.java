package cn.ed.qut.compiler.base.dataStruct.symbolTable.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
/**
 * 变量类，包括程序定义的变量(全局变量，局部变量)，四元式生成的临时变量
 * @author 清居
 * 
 */
public class Variate {

	private String name;//变量名
	private List<Integer> location=new ArrayList<>();//
	/**
	 * 变量待用信息与活跃信息链
	 */
	private Stack<Integer> waitUseInfo=new Stack<>();
	private Stack<Boolean> activeInfo=new Stack<>();
	
	/**
	 * 变量存放的位置，寄存器或者是内存，寄存器的话是哪个寄存器<br>
	 * 值是-1时表示存放在内存中
	 */
	

//	private SymbolTable symbolTable;//变量所在的符号表
	
	public Variate() {
	
	}
	public Variate(String name) {
		this.name = name;
	}
	/*
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
		getLocation().add(register.getId());
	}
	


	/**
	 * 将变量分配给寄存器
	 * @param register 所分配的寄存器
	 */
	public void allotVariate(Register register){
		addLocation(register);//将寄存器加入变量位置列表
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
		for (Integer i: getLocation()) {
			if(i.equals(id)){
				getLocation().remove(i);
			}
		}
	}
	


	
	@Override
	protected void finalize(){
		getLocation().clear();
		
		getWaitUseInfo().clear();
		
		getActiveInfo().clear();
	}
	/**
	 * @return waitUseInfo
	 */
	public Stack<Integer> getWaitUseInfo() {
		return waitUseInfo;
	}

	/**
	 * @return activeInfo
	 */
	public Stack<Boolean> getActiveInfo() {
		return activeInfo;
	}
	/**
	 * @return location
	 */
	public List<Integer> getLocation() {
		return location;
	}
	/**
	 * @param location 要设置的 location
	 */
	public void setLocation(List<Integer> location) {
		this.location = location;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
