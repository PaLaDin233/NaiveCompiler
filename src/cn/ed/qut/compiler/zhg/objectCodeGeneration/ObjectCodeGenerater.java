package cn.ed.qut.compiler.zhg.objectCodeGeneration;
/**
 * 目标代码生成器的抽象类
 * @author 清居
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

public abstract class ObjectCodeGenerater {
	//中间代码文件的后缀名
	
	private String intermediateCodeExtensionName="";
	private String objectCodeExtensionName;

	protected List<String> objectCodeList;
	
	protected List<BaseBlock> baseBlocks;
	private String fileName;
	private File file;
	

	public ObjectCodeGenerater() {
	}
	
	//根据四元式列表生成基本块列表——基本块的划分
	public ObjectCodeGenerater(List<FourElement> elements) {
		baseBlocksInit(elements);
	}
	
	public ObjectCodeGenerater(List<FourElement> fourElements, String fileName,
			String intermediateCodeExtensionName,String objectCodeExtensionName) {
		this.fileName = fileName;
		this.intermediateCodeExtensionName = intermediateCodeExtensionName;
		this.setObjectCodeExtensionName(objectCodeExtensionName);
		baseBlocksInit(fourElements);
		
		
	}
	
	/**
	 * 获取指定文件名，中间语言文件后缀名，目标代码文件后缀名的目标代码生成器
	 * @param fileName
	 * @param intermediateCodeExtensionName
	 * @param objectCodeExtensionName
	 */
	public ObjectCodeGenerater(String fileName,String intermediateCodeExtensionName,String objectCodeExtensionName) {
		this.fileName = fileName;
		this.intermediateCodeExtensionName = intermediateCodeExtensionName;
		this.setObjectCodeExtensionName(objectCodeExtensionName);
		try {
			init(fileName);
		} catch (Exception e) {
			System.out.println("Failed!");
			e.printStackTrace();
		}
	}

	/**
	 * 初始化汇编语言生成类，从文件获取中间代码，生成中间代码列表
	 * @param fileName 中间语言文件的文件名,不带后缀
	 * @throws Exception
	 */
	public void init(String fileName)throws Exception{
		setFileName(fileName);
		file =new File(fileName+intermediateCodeExtensionName);
		this.baseBlocks=new ArrayList<>();
		this.objectCodeList=new ArrayList<>();

		//从中间语言文件中还原四元式的ArrayList
		ArrayList<FourElement> fourElements=null;
		ObjectInputStream inputStream=null;
		try {
			inputStream=new ObjectInputStream(new FileInputStream(file));

			Object object=inputStream.readObject();
			if(object instanceof ArrayList){
				if(fourElements==null) fourElements=new ArrayList<>();
				fourElements=(ArrayList<FourElement>)object;
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("目标代码生成器初始化失败");
			e.printStackTrace();
		}
		finally {
			inputStream.close();
		}
		if(fourElements!=null)baseBlocksInit(fourElements);
	}

	
	protected void baseBlocksInit(List<FourElement> elements){
		int id=-1;
		BaseBlock b=null;
		for (FourElement fourElement : elements) {
			//当四元式的初步基本块id与当前基本块号不相等时，生成新的基本块，将新的基本块加入基本块列表中
			if(fourElement.getId()!=id){
				id++;
				b=new BaseBlock(id);
				baseBlocks.add(b);
			}
			//向基本块添加四元式
			b.addFourElement(fourElement);
		}
		//对中间代码进行优化
		intermediateCodeOptimizing();
	}
	
	
	/**
	 * 中间代码优化
	 */
	private void intermediateCodeOptimizing(){
		//TODO 中间代码优化
	}
	
	
	/**
	 * 目标代码生成入口
	 * @return 生成的目标代码文件
	 * @throws Exception
	 */
	public File generator() throws Exception{
		for (BaseBlock baseBlock : baseBlocks) {
			baseBlockToObjectCode(baseBlock);
		}
		File file=new File(fileName+getExtensionName());
		//将生成的MIPS指令写入文件
		if(!file.exists())file.createNewFile();
		if(file.canWrite()){
			FileWriter fileWriter=new FileWriter(file);
			for (String objectCode : objectCodeList) {
				fileWriter.write(objectCode);
			}
			fileWriter.close();
		}else{
			throw new Exception("文件写入失败");
		}

		return file;
	}

	protected abstract void baseBlockToObjectCode(BaseBlock baseBlock);
	
	
	
	public List<String> getObjectCodeList() {
		return objectCodeList;
	}

	public void setObjectCodeList(ArrayList<String> objectCodeList) {
		this.objectCodeList = objectCodeList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getExtensionName() {
		return intermediateCodeExtensionName;
	}

	public void setExtensionName(String extensionName) {
		this.intermediateCodeExtensionName = extensionName;
	}

	/**
	 * @return objectCodeExtensionName
	 */
	public String getObjectCodeExtensionName() {
		return objectCodeExtensionName;
	}

	/**
	 * @param objectCodeExtensionName 要设置的 objectCodeExtensionName
	 */
	public void setObjectCodeExtensionName(String objectCodeExtensionName) {
		this.objectCodeExtensionName = objectCodeExtensionName;
	}

}
