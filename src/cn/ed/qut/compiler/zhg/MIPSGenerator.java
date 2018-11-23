package cn.ed.qut.compiler.zhg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
	private static final String INTERMEDIATECODE_EXTENSION_NAME="";
	
	public MIPSGenerator() {

	}
	/**
	 * 初始化汇编语言生成类，获取四元式列表
	 * @param fileName 中间语言文件的文件名,不带后缀
	 * @throws Exception
	 */
public MIPSGenerator(String fileName) throws Exception{
	
	this.fileName=fileName;
	file =new File(fileName+INTERMEDIATECODE_EXTENSION_NAME);
	
	this.assemblers=new ArrayList<>();
	
	//从中间语言文件中还原四元式的ArrayList
	
	ObjectInputStream inputStream=null;
	try {
		inputStream=new ObjectInputStream(new FileInputStream(file));
		
		Object object=inputStream.readObject();

		fourElements=(ArrayList<FourElement>)object;
	
	} catch (ClassNotFoundException | IOException e) {
		System.out.println("目标代码生成器初始化失败");
		e.printStackTrace();
	}
	finally {
		inputStream.close();
	}
}

public File generator() throws Exception{
	File file=new File(fileName+ASSEMBLER_EXTENSION_NAME);
	
	for(int i = 0; i < fourElements.size(); i++){
		FourElement temp=fourElements.get(i);
		//System.out.println(str[i]);
		//String temp[] = str[i].split(",");
		//System.out.println(temp.length);
		//for(int j = 0; j < temp.length; j++) System.out.println(temp[j]);
		//1(=,0,/,sum)
		//汇编代码字符串
		String tempAssembler="";
		if(temp.getOp().equals("=")){	
			tempAssembler = "MOV " + temp.getResult() + "," + temp.getArg1() + "\n";
		}
		else if(temp.getOp().equals("++")){
			tempAssembler = "INC "  + temp.getArg1() + "\n";
		}
		else if(temp.getOp().equals("+")){
			tempAssembler="MOV "+temp.getResult()+","+temp.getArg1()+"\n";
			assemblers.add(tempAssembler);
			tempAssembler ="ADD " + temp.getResult() + "," + temp.getArg2() + "\n";
		}
		else if(temp.getOp().equals("-")){
			tempAssembler="MOV "+temp.getResult()+","+temp.getArg1()+"\n";
			assemblers.add(tempAssembler);
			tempAssembler ="SUB " + temp.getResult() + "," + temp.getArg2() + "\n";
		}
		else if(temp.getOp().equals("*")){
			tempAssembler ="MUL " + temp.getResult() + "," + temp.getArg1() + "\n";
		}
		else if(temp.getOp().equals("/")){
			tempAssembler ="DIV "  + temp.getResult() + "," + temp.getArg1() + "\n";
		}
		else if(temp.getOp().equals("JR")){
			tempAssembler ="JMP " + temp.getResult()  + "\n";
		}
		else if(temp.getOp().equals("JF")){
			tempAssembler ="JZ " + temp.getResult()  + "\n";
		}
		else if(temp.getOp().equals(">")){
			tempAssembler ="JG " + temp.getResult()  + "\n";
		}
		else if(temp.getOp().equals("<")){
			tempAssembler ="JL " + temp.getResult()  + "\n";
		}
		else{
			//TODO 错误排除
			continue;
		}
		assemblers.add(tempAssembler);
	}
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
public ArrayList<FourElement> getFourElements() {
	return fourElements;
}

public void setFourElements(ArrayList<FourElement> fourElements) {
	this.fourElements = fourElements;
}

}
