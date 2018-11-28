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
	 * �Ĵ����ĳ�ʼ��,���ö��ھ���ָ��ļĴ��������������ֵȵ�
	 * ��Ҫ�ǳ�ʼ����̬��Ա����regNameList;
	 */
	public abstract void init();
	
	/**
	 * ��ոüĴ���
	 */
	public void clear(){
		for (Variate variate : allocatVar) {
			variate.removeFromAddress(id);
		}
		allocatVar.clear();
	}
	
	/**
	 * Ϊ�Ĵ����������variate
	 * @return
	 */
	public void allotVariate(Variate variate){
		//����������"�Ĵ�����������б�"
		add(variate);
		//������Ĵ洢λ���������Ĵ���
		variate.addLocation(this);
		
	}

	public void add(Variate variate){
		allocatVar.add(variate);//��������������б�	
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
	 * ��ȡ�Ĵ�������ı����б�
	 * ���ص�sizeΪ0ʱ��ʾû�з������
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
