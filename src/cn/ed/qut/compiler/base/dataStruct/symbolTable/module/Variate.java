package cn.ed.qut.compiler.base.dataStruct.symbolTable.module;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
/**
 * �����࣬����������ı���(ȫ�ֱ������ֲ�����)����Ԫʽ���ɵ���ʱ����
 * @author ���
 * 
 */
public class Variate {
//	private String name;//������
	private boolean waitUse=false;
	private boolean active=false;
	
	/**
	 * ������ŵ�λ�ã��Ĵ����������ڴ棬�Ĵ����Ļ����ĸ��Ĵ���<br>
	 * ֵ��-1ʱ��ʾ������ڴ���
	 */
	private List<Integer> location=new ArrayList<>();//

//	private SymbolTable symbolTable;//�������ڵķ��ű�
	
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
	 * Ϊ������Ӵ��λ��
	 * @param register
	 */
	public void addLocation(Register register){
		location.add(register.getId());
	}
	


	/**
	 * ������������Ĵ���
	 * @param register ������ļĴ���
	 */
	public void allotVariate(Register register){
		location.add(register.getId());//���Ĵ����������λ���б�
		//������Ĵ洢λ���������Ĵ���
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
	 * ͨ���Ĵ����������Ĵ����б����Ĵ��λ���б��Ƴ�
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
	 * ��������Ϣȡ��
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
	 * ���Ƿ��Ծ��Ϣȡ��
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
