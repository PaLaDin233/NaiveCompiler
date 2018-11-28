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
 * 该类还未完成
 * @author 清居
 * @       TODO
 */
public class MIPSGenerator{

	
	private String fileName;
	private ArrayList<FourElement> fourElements;
	private File file;
	private ArrayList<String> assemblers;
	
	//生成的目标代码文件后缀名
	private static final String ASSEMBLER_EXTENSION_NAME=".asm";
	//中间代码文件的后缀名
	private static final String INTERMEDIATECODE_EXTENSION_NAME="";
	
	public MIPSGenerator() {

	}
	/**
	 * 初始化汇编语言生成类，从文件获取中间代码，生成中间代码列表
	 * @param fileName 中间语言文件的文件名,不带后缀
	 * @throws Exception
	 */
public MIPSGenerator(String fileName)throws Exception{
	
	this.fileName=fileName;
	file =new File(fileName+INTERMEDIATECODE_EXTENSION_NAME);
	
	this.assemblers=new ArrayList<>();
	
	//从中间语言文件中还原四元式的ArrayList
	
	ObjectInputStream inputStream=null;
	try {
		inputStream=new ObjectInputStream(new FileInputStream(file));
		
		Object object=inputStream.readObject();
		if(object instanceof ArrayList)
			fourElements=(ArrayList<FourElement>)object;
		
	
	} catch (ClassNotFoundException | IOException e) {
		System.out.println("目标代码生成器初始化失败");
		e.printStackTrace();
	}
	finally {
		inputStream.close();
	}
}

/**
 * 根据四元式生成指定文件名的生成器
 */
public MIPSGenerator(String fileName, ArrayList<FourElement> fourElements) {
		super();
		this.fileName = fileName;
		this.fourElements = fourElements;
	}
/**
 * 四元式定义
 *（+，OP1，op2，res） res=OP1+OP2
 *（-，OP1，OP2，res）res=OP1-OP2
 *（*，OP1.OP2.res）res=OP1*OP2
 *（/，OP1，OP2，res）res=OP1/OP2
 *（%，op1，op2，res）res=op1 mod op2
 *（JA，a,b,label）if(a>b)goto label
 *（JB，a,b,label）if(a<b)goto label
 *（JE,a,b,label）if(a==b)goto...
 *（JNE,a,b,label）if(a!=b)goto...
 *（JAE,a,b,label）if(a>=b)goto...
 *（JBE,a,b,label）if(a<=b)goto...
 *（MINUS，op1，null，res）res=-op1
 *（GOTO，nul，,null，label）goto label
 *（ASSIGN，op1，null，res）res=op1
 *（PARA，op1，null，null） op1作为参数传递
 *（RETURN，op1，null，null）op1作为返回值
 *（CALL，null，null，op1）调用过程op1
 *（SCANF，op1，null，null）从控制台读入数写到op1
 *（PRINTF，op1，null，null）输出op1到控制台
 */
/**
 * 目标代码生成入口
 * @return 生成的目标代码文件
 * @throws Exception
 */
public File generator() throws Exception{
	File file=new File(fileName+ASSEMBLER_EXTENSION_NAME);
	
	for(int i = 0; i < fourElements.size(); i++){
		FourElement temp=fourElements.get(i);	
		//将四元式转换为MIPS指令
		String tempAssembler=switchFourElementToMIPS(temp);
		//添加进指令列表
		assemblers.add(tempAssembler);
	}
	//将生成的MIPS指令写入文件
	file.createNewFile();
	if(file.canWrite()){
		FileWriter fileWriter=new FileWriter(file);
		for (String assembler : assemblers) {
			fileWriter.write(assembler);
		}
		fileWriter.close();
	}else{
		throw new Exception("文件写入失败");
	}
	
	return file;
}

public static String switchFourElementToMIPS(FourElement element){
	StringBuilder tempAssembler=new StringBuilder();
	//获取全局符号表
	SymbolTable symbolTable=GlobalSymbolTable.getSymbolTable();
	if(element.getOp().equals("+")){
		//查询+是立即数加还是整型变量加
		
		//判断四元式的第一个操作数是常数还是变量
		if(symbolTable.isInt( element.getArg1() ) ){
			if(symbolTable.isInt(element.getArg2())){
				
			}
		}
		
		try {
			//立即数加
			Integer.decode(element.getArg1());
		} catch (NumberFormatException e) {
			//整数变量加
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
		//TODO 错误排除
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
