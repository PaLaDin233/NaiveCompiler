/**
 * ����ķ��ű�
 * @author ���
 *
 */
package cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct;

import java.util.Map;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.DataType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;


public abstract class SymbolTable {

	//protected Map<SymbolItemIndex,SymbolTableItem> sMap;

	/**
	 * sMap�������Ƿ��ŵ�����name
	 * 
	 */
	protected Map<String,SymbolTableItem> sMap;
	protected int intNum=0;//int����������
	protected int stringNum=0;//string����������


	public SymbolTable() {
		init();
	}
	public abstract void init();
	/**
	 * @return table
	 */

	/**
	 * �жϸ����������Ƿ������������ķ������ͣ�����������������������)
	 * @param symbolType
	 * @param name
	 * @return
	 */
	protected boolean isType(SymbolType symbolType,String name){
		SymbolTableItem item=getSymbolTableItem(name);	
		if(item==null){
			return false;
		}
		return item.getSymbolType()==symbolType;
	}

	/**
	 * �жϸ����ı�ʶ���Ƿ��Ǹ�������������
	 * @param dataType
	 * @param name
	 * @return
	 */
	protected boolean isType(DataType dataType,String name){
		SymbolTableItem item=getSymbolTableItem(name);	
		if(item==null){
			return false;
		}
		return item.getDataType()==dataType;
	}

	/**
	 * �жϸ������Ƿ���Ҫ������ͣ��ж�name�Ƿ���dataType���͵�symbolType��
	 * @param symbolType ��������
	 * @param dataType ��������
	 * @param name Ҫ�жϵ�����
	 */
	protected boolean isType(SymbolType symbolType,DataType dataType,String name){
		return isType(dataType, name)&&isType(dataType, name);
	}

	public boolean isProc(String name){
		return isType(SymbolType.PROC, name);
	}
	public boolean isVar(String name){
		return isType(SymbolType.VAR, name);
	}
	public boolean isConst(String name){
		return isType(SymbolType.CONST, name);
	}

	public boolean isInt(String name){
		return isType(SymbolType.VAR,DataType.INT, name);
	}
	public boolean isString(String name){
		return isType(SymbolType.VAR,DataType.STRING, name);
	}

	//******************����ȫ������չ�õ�*************
	public boolean isFloat(String name){
		return isType(DataType.FLOAT, name);
	}
	public boolean isDouble(String name){
		return isType(DataType.DOUBLE, name);
	}

	public boolean isShort(String name){
		return isType(DataType.SHORT, name);
	}
	public boolean isByte(String name){
		return isType(DataType.BYTE, name);
	}
	public boolean isLong(String name){
		return isType(DataType.LONG, name);
	}
	public boolean isStruct(String name){
		return isType(DataType.STRUCT, name);
	}
	//**********************************************




	/**
	 * ��ȡ���ű��е���
	 * @param name �������
	 * @return
	 */
	public SymbolTableItem getSymbolTableItem(String name){
		return sMap.get(name);
	}

	/*	public SymbolTableItem getProc(String name){
		SymbolTableItem item=null;

		item=smap.get(name);
		if(item==null){
			map=sMap.get(SymbolTableItem.VOID_PROC);
			if(map==null)return null;
			item=map.get(name);
		}
		return item;
	}*/

	/**
	 * ��ȡ�ñ������ͱ����ĸ���
	 * @return
	 */
	public int getIntNum(){
		return this.intNum;//��ȡ�������ͱ�����Map,����Map��size
	}
	public int getStringNum(){
		return this.stringNum;//��ȡ�ַ���������Map,����Map��size
	}
	public int getVarNum(){
		return getIntNum()+getStringNum();
	}


	/**
	 * ��ȡ�����оֲ������ĸ���
	 * @param funcName ������
	 * @return ���������;ֲ������ĸ���,���Ƿ����򷵻�-1
	 */
	public int getIntNum(String name){

		//��ȡ�÷�������
		SymbolTableItem item=getSymbolTableItem(name);
		if(item==null){//�÷��Ų��Ƿ���
			return -1;
		}
		//��ȡ����ָ��ķ��ű�
		SymbolTable table=item.getSymbolTable();
		//��ȡ���ű��еı�������
		return table.getIntNum();
	}





	/**
	 * ��һ�����ű��������ű�
	 * @param item Ҫ�������
	 * @return
	 */
	public boolean insert(SymbolTableItem item){
		//��ȡ���ű����Ƿ����
		if(!exist(item.getName())){
			//����ѹ��������;�����ͬ�Ĳ���
			if(item.getSymbolType()==SymbolType.VAR){//���������һ������ʱ
				if(item.getDataType()==DataType.INT){//����������ͱ���ʱ
					intNum++;
				}
			}
			if(item.getDataType()==DataType.STRING){
				stringNum++;
			}
			spacialInsert(item);
			sMap.put(item.getName(), item);
			return true;
		}
		return false;
	}
	
	/**
	 * �����������������ʱ���еĲ���
	 * @param item
	 * @return
	 */
	public abstract void spacialInsert(SymbolTableItem item);
	
	/**
	 * �ж�ָ�����ֵı�ʶ���������Ƿ��ڷ��ű��д���
	 * @param name ָ���ı�ʶ��
	 * @return
	 */
	public boolean exist(String name){		
		return sMap.get(name)!=null;
	}


}
