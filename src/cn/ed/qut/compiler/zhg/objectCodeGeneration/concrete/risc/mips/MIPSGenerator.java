package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

/**
 * ���໹δ���
 * @author ���
 * @       TODO
 */
public class MIPSGenerator{

	
	private String fileName;
	private ArrayList<FourElement> fourElements;
	private File file;
	private ArrayList<String> assemblers;
	
	//���ɵ�Ŀ������ļ���׺��
	private static final String ASSEMBLER_EXTENSION_NAME=".asm";
	//�м�����ļ��ĺ�׺��
	private static final String INTERMEDIATECODE_EXTENSION_NAME="";
	
	public MIPSGenerator() {

	}
	/**
	 * ��ʼ��������������࣬���ļ���ȡ�м���룬�����м�����б�
	 * @param fileName �м������ļ����ļ���,������׺
	 * @throws Exception
	 */
public MIPSGenerator(String fileName)throws Exception{
	
	this.fileName=fileName;
	file =new File(fileName+INTERMEDIATECODE_EXTENSION_NAME);
	
	this.assemblers=new ArrayList<>();
	
	//���м������ļ��л�ԭ��Ԫʽ��ArrayList
	
	ObjectInputStream inputStream=null;
	try {
		inputStream=new ObjectInputStream(new FileInputStream(file));
		
		Object object=inputStream.readObject();
		if(object instanceof ArrayList)
			fourElements=(ArrayList<FourElement>)object;
		
	
	} catch (ClassNotFoundException | IOException e) {
		System.out.println("Ŀ�������������ʼ��ʧ��");
		e.printStackTrace();
	}
	finally {
		inputStream.close();
	}
}

/**
 * ������Ԫʽ����ָ���ļ�����������
 */
public MIPSGenerator(String fileName, ArrayList<FourElement> fourElements) {
		super();
		this.fileName = fileName;
		this.fourElements = fourElements;
	}
/**
 * ��Ԫʽ����
 *��+��OP1��op2��res�� res=OP1+OP2
 *��-��OP1��OP2��res��res=OP1-OP2
 *��*��OP1.OP2.res��res=OP1*OP2
 *��/��OP1��OP2��res��res=OP1/OP2
 *��%��op1��op2��res��res=op1 mod op2
 *��JA��a,b,label��if(a>b)goto label
 *��JB��a,b,label��if(a<b)goto label
 *��JE,a,b,label��if(a==b)goto...
 *��JNE,a,b,label��if(a!=b)goto...
 *��JAE,a,b,label��if(a>=b)goto...
 *��JBE,a,b,label��if(a<=b)goto...
 *��MINUS��op1��null��res��res=-op1
 *��GOTO��nul��,null��label��goto label
 *��ASSIGN��op1��null��res��res=op1
 *��PARA��op1��null��null�� op1��Ϊ��������
 *��RETURN��op1��null��null��op1��Ϊ����ֵ
 *��CALL��null��null��op1�����ù���op1
 *��SCANF��op1��null��null���ӿ���̨������д��op1
 *��PRINTF��op1��null��null�����op1������̨
 */
/**
 * Ŀ������������
 * @return ���ɵ�Ŀ������ļ�
 * @throws Exception
 */
public File generator() throws Exception{
	File file=new File(fileName+ASSEMBLER_EXTENSION_NAME);
	
	for(int i = 0; i < fourElements.size(); i++){
		FourElement temp=fourElements.get(i);	
		//����Ԫʽת��ΪMIPSָ��
		String tempAssembler=switchFourElementToMIPS(temp);
		//��ӽ�ָ���б�
		assemblers.add(tempAssembler);
	}
	//�����ɵ�MIPSָ��д���ļ�
	file.createNewFile();
	if(file.canWrite()){
		FileWriter fileWriter=new FileWriter(file);
		for (String assembler : assemblers) {
			fileWriter.write(assembler);
		}
		fileWriter.close();
	}else{
		throw new Exception("�ļ�д��ʧ��");
	}
	
	return file;
}

public static String switchFourElementToMIPS(FourElement element){
	StringBuilder tempAssembler=new StringBuilder();
	//��ȡȫ�ַ��ű�
	SymbolTable symbolTable=GlobalSymbolTable.getSymbolTable();
	if(element.getOp().equals("+")){
		//��ѯ+���������ӻ������ͱ�����
		
		//�ж���Ԫʽ�ĵ�һ���������ǳ������Ǳ���
		if(symbolTable.isInt( element.getArg1() ) ){
			if(symbolTable.isInt(element.getArg2())){
				
			}
		}
		
		try {
			//��������
			Integer.decode(element.getArg1());
		} catch (NumberFormatException e) {
			//����������
		}
		
		
		
		if(symbolTable.isConst(element.getArg1())||symbolTable.isConst(element.getArg2())){
			
		}
		tempAssembler.append("ADD ");
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else if(element.getOp().equals("")){
		
	}
	else{
		//TODO �����ų�
		return null;
	}
	
	return tempAssembler.toString();
}
public ArrayList<FourElement> getFourElements() {
	return fourElements;
}

public void setFourElements(ArrayList<FourElement> fourElements) {
	this.fourElements = fourElements;
}

}
