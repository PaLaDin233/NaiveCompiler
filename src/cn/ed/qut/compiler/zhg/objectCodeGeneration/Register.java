package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;

public abstract class Register {
	protected int id;
	
	protected List<Variate> allocatVar;
	
	public static List<String> regNameList;
	
	{
		init();
		allocatVar=new ArrayList<>();
	}
	
	public Register(int id) {
		this.id = id;
	}

	public Register(String name) {
		int id=regNameList.indexOf(name);
		if(id>=0) this.id = id;
		else id=-2;
		
	}
	
/*	public Register(int id, List<Variate> allocatVar) {
		super();
		this.id = id;
		this.allocatVar = allocatVar;
	}*/


	/**
	 * 寄存器的初始化,设置对于具体指令集的寄存器的数量，名字等等
	 * 主要是初始化静态成员变量regNameList;
	 */
	public abstract void init();
	
	/**
	 * 清空该寄存器
	 */
	public void clear(){
		for (Variate variate : allocatVar) {
			variate.removeFromAddress(id);
		}
		allocatVar.clear();
	}
	
	/**
	 * 为寄存器分配变量variate
	 * @return
	 */
	public void allotVariate(Variate variate){
		//将变量加入"寄存器分配变量列表"
		add(variate);
		//向变量的存储位置添加这个寄存器
		variate.addLocation(this);
		
	}

	public void add(Variate variate){
		allocatVar.add(variate);//将变量加入分配列表	
	}
	
	public int getId() {
		return id;
	}
	/*
	public void setId(int id) {
		this.id = id;
	}*/

	public String getName(int id){
		return regNameList.get(id);
	}
	/**
	 * 获取寄存器分配的变量列表
	 * 返回的size为0时表示没有分配变量
	 * @return
	 */
	public List<Variate> getAllocatVar() {
		return allocatVar;
	}

	public boolean isFree(){
		return allocatVar.size()==0;
	}
	
	/*public void setAllocatVar(List<Variate> allocatVar) {
		this.allocatVar = allocatVar;
	}*/
	
	
}
