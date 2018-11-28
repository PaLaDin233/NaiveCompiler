package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;
/**
 * MIPS��ȫ�ֱ�����
 * @author minecraft
 * ��asm�ļ��еı�ʾ��ʽ
 * 
 * .data
 * 		var1:	.word	3	# ����һ�� word ���͵ı��� var1, ͬʱ���丳ֵΪ 3
 * 		array1:	.byte	'a','b'	# ����һ���洢2���ַ������� array1������ֵ 'a', 'b'
 * 		array2:	.space	40	# Ϊ���� array2 ���� 40�ֽڣ�bytes)δʹ�õ������ռ�
 */

public class MIPSData {
	private String name;//������
	private String type;//��������
	private String value;//����ֵ
	
	private static final String BYTE=".byte";
	private static final String SPACE=".space";
	private static final String WORD=".word";
	private static final String ASCII=".asciiz";
	private static final String[] LEGAL_DATA_TYPE={BYTE,SPACE,WORD,ASCII};
	public MIPSData() {
		
	}
	/**
	 * 
	 * @param name	������
	 * @param type	��������
	 * @param value	����ֵ
	 * @example
	 * new MIPSData("var1",".word","3");	#����һ�� word ���͵ı��� var1, ͬʱ���丳ֵΪ 3<br>
	 * new MIPSData("var2",".word","4,5");<br>
	 * new MIPSData("array1",".byte","\'a\',\'b\'");	# ����һ���洢2���ַ������� array1������ֵ 'a', 'b'<br>
	 * new MIPSData("array2",".space","40");	#Ϊ���� array2 ���� 40�ֽڣ�bytes)δʹ�õ������ռ�<br>
	 */
	public MIPSData(String name,String type,String value){
		setName(name);
		setType(type);
		setValue(value);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if(islegalArgument(type))
			this.type = type;
		else{
			throw new IllegalArgumentException();
		}
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * �жϲ����Ƿ���һ����������
	 * @param arg Ҫ�жϵ��ַ���
	 * @return	���Ѿ�������������ͷ����棬���򷵻ؼ�
	 */
	private boolean islegalArgument(String arg){
		arg=arg.trim();
		for (String string : LEGAL_DATA_TYPE) {
			if(arg.equals(string))return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			new MIPSData().setType(".ascii");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
