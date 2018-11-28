package cn.ed.qut.compiler.base.dataStruct.symbolTable.module;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.ProcSymbolTable;

public class SymbolTableItem {
	
	
	
	
	private SymbolType type;//�������ͣ�������ȡֵ������������������,����
	private String name;//����
	private Object value;//������ֵ,�����ǲ�����index
	private DataType dataType;//�α����������������ͣ���������ֵ���ͣ���int,char,string,short....
	private ProcSymbolTable symbolTable;//����Ƿ�����������Ӧ�ľֲ����ű�
	private Variate variate;//����Ǳ������ͱ�����صı�����Ϣ

	private SymbolTableItem(){}
	
	/**
	 * ������ʹ�øù��췽��
	 * @param type
	 * @param name
	 * @param value
	 * @param dataType
	 */
	public SymbolTableItem(SymbolType type, String name, int value, DataType dataType) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.dataType = dataType;
	}


	//����ģʽ��Ц
	
	
	
	/**
	 * ��ȡһ����ʼ�ķ��ű��ֻΪ��������
	 * ����һ���涨name�Ĳ������͵ķ��ű���
	 * @param name ��������
	 * @return
	 */
	private static SymbolTableItem getItemNoType(String name){
		SymbolTableItem item=new SymbolTableItem();
		item.setName(name);
		return item;
	}
	
	
	
	/**
	 * ��ȡһ����ʼ���ķ��������ֵ���ͻ�δ����
	 * Ϊ���������þֲ����ű�
	 * @param name ������
	 * @return
	 */
	private static SymbolTableItem getProcItemNoReturnType(String name){
		SymbolTableItem item=getItemNoType(name);
		//������������Ƿ���
		item.setSymbolType(SymbolType.PROC);
		//Ϊ�������þֲ����ű�
		item.setTempSymbolTable(new ProcSymbolTable() );
		return item;
	}
	
	
	/**
	 * ��ȡһ���涨����ֵ�ķ������ű���ʵ��������<br>
	 * @param name ������
	 * @param dataType ���ص���������
	 * @return ���ɵķ��ű���
	 */
	public static SymbolTableItem getProcItemWithDataType(String name,DataType dataType){
		SymbolTableItem item=getProcItemNoReturnType(name);
		item.setDataType(dataType);
		return item;
	}
	
	
	/**
	 * ��ȡһ�������͵ı���
	 * @param name ������
	 * @return
	 */
	private static SymbolTableItem getVar(String name){
		SymbolTableItem item=getItemNoType(name);
		item.setSymbolType(SymbolType.VAR);
		item.setVariate(new Variate());
		return item;
	}
	
	/**
	 * ��ȡһ��ָ���������͵ı���
	 * @param name
	 * @return
	 */
	public static SymbolTableItem getVar(String name,DataType dataType){
		SymbolTableItem item=getVar(name);
		item.setDataType(dataType);
		return item;
	}
	
	/**
	 * ��ȡһ�����ͱ������ű����ʵ��������
	 * @param name ������
	 * @return
	 */
	public static SymbolTableItem getIntVar(String name){
		return getVar(name, DataType.INT);
	}
	
	/**
	 * ��ȡһ���������ű����ʵ��������
	 * @param name ������
	 * @param value ����ֵ
	 * @return
	 */
	public static SymbolTableItem getConst(String name,int value){
		SymbolTableItem item=getItemNoType(name);
		item.setSymbolType(SymbolType.CONST);		
		return item;
	}
	
//	/**
//	 * ��ȡһ�����������ű����ʵ��������
//	 * @return
//	 */
//	public static SymbolTableItem getMainProcItem(){
//		SymbolTableItem item=getProcItemNoType("main");
//		item.setType(MAIN_PROC);		
//		return item;
//	}


	/**
	 * ��ȡһ��������
	 * @param dataType ��������������
	 * @param name ������
	 * @param index �ڼ�������
	 * @return
	 */
	public static SymbolTableItem getPara(DataType dataType,String name,int index){
		SymbolTableItem item=getItemNoType(name);
		item.setSymbolType(SymbolType.PARA);
		item.setValue(index);
		item.setDataType(dataType);
		return item;
	}
	
	/**
	 * ��ȡһ���ַ����������ű����ʵ��������
	 * @param name �ַ��������ı�����
	 * @return
	 */
	public static SymbolTableItem getStringItem(String name){
		SymbolTableItem item=getVar(name);
		item.setDataType(DataType.STRING);		
		return item;
	}
	
	
	
	
	
	
	
	
	
	
	public SymbolType getSymbolType() {
		return type;
	}
	public void setSymbolType(SymbolType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	public ProcSymbolTable getSymbolTable() {
		return symbolTable;
	}
	public void setTempSymbolTable(ProcSymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	/**
	 * @return dataType
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * @param dataTypeҪ���õ� dataType
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public Variate getVariate() {
		return variate;
	}

	public void setVariate(Variate variate) {
		this.variate = variate;
	}
	
	
	
	
	
}
