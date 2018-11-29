package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.BaseBlock;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.ObjectCodeGenerater;

/**
 * 该类还未完成
 * @author 清居
 * @       TODO
 */
public class MIPSGenerator extends ObjectCodeGenerater{


	

	
	
	//生成的目标代码文件后缀名
	private static final String ASSEMBLER_EXTENSION_NAME=".asm";
	

	public MIPSGenerator() {

	}
	
	/**
	 * 构造指定文件名的MIPS生成器
	 */
	public MIPSGenerator(String fileName, ArrayList<FourElement> fourElements) {
		super(fourElements, fileName, "", ASSEMBLER_EXTENSION_NAME);
	}
	
	public MIPSGenerator(ArrayList<FourElement>elements){
		super(elements);
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

	@Override
	protected void baseBlockToObjectCode(BaseBlock baseBlock) {
		// TODO 自动生成的方法存根
		
	}


}
