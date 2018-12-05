package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.GlobalSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolType;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.BaseBlock;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.BaseBlockSymbolTable;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Code;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.ObjectCodeGenerater;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.RegisterAllocator;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.SymbolTableStack;

/**
 * 该类还未完成
 * @author 清居
 * @       TODO
 */
public class MIPSGenerator extends ObjectCodeGenerater{

	//生成的目标代码文件后缀名
	private static final String ASSEMBLER_EXTENSION_NAME=".asm";
	protected List<MIPSData> mipsDatas=new ArrayList<>();

	@Override
	protected void init() {
		this.setRegisterAllocator(new MIPSRegisterAllocator());

	}
	/**
	 * 构造指定文件名的MIPS生成器
	 */
	public MIPSGenerator(String fileName, ArrayList<FourElement> fourElements) {
		super(fourElements, fileName, "", ASSEMBLER_EXTENSION_NAME);
		mipsHead();
	}

	public MIPSGenerator(ArrayList<FourElement>elements){
		super(elements);
		mipsHead();
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



	

	@Override
	protected void baseBlockToObjectCode(BaseBlock baseBlock) {
		List<FourElement> arrayList=baseBlock.getElements();
		BaseBlockSymbolTable symbolTable=baseBlock.getBaseBlockSymbolTable();//当前基本块的符号表
		for (FourElement fourElement : arrayList) {
			//设置该条四元式对应的第一条目标代码编号
			fourElement.setObjectCodeId(codes.size());

			String op=fourElement.getOp();
			String arg1=fourElement.getArg1();
			String arg2=fourElement.getArg2();
			String result=fourElement.getResult();
			Register resultRegister=null;
			Register arg1Register=null;
			Register arg2Register=null;


			//为三个参数分配寄存器
			if(result!=null){
				resultRegister=getRegisterAllocator().getRegister(result,fourElement,resultRegister);
				
			}

			if(!symbolTable.exist(arg1)){//result不存在基本块符号表时，表示他是一个字面值，将字面值获取到寄存器$t0中,设置寄存器为
				arg1Register=getRegisterAllocator().getRegisterList().get(8);
			}else{
				arg1Register=getRegisterAllocator().getRegister(arg1, fourElement,resultRegister);
			}


			if(!symbolTable.exist(arg2)){//result不存在基本块符号表时，表示他是一个字面值，将字面值获取到寄存器$t1中,设置寄存器为t9
				arg2Register=getRegisterAllocator().getRegisterList().get(9);
			}
			else{
				arg2Register=getRegisterAllocator().getRegister(arg2, fourElement,resultRegister);
			}
			if(fourElement.getOp().equals("+")){
				if(isImmediateNum(arg1)
						||isImmediateNum(arg2)
						||symbolTable.isConst(fourElement.getArg1())||symbolTable.isConst(fourElement.getArg2())){//当两个操作数有一个是常数时，该操作是立即数加
					op="ADDI";
				}
				else{//否则是寄存器加
					op="ADD";
				}

				if(symbolTable.isConst(fourElement.getArg1())){
					arg1=(String)symbolTable.getSymbolTableItem(fourElement.getArg1()).getValue();
				}
				if(symbolTable.isConst(fourElement.getArg2())){
					arg2=(String)symbolTable.getSymbolTableItem(fourElement.getArg2()).getValue();
				}
				if(arg1Register!=null)arg1=arg1Register.getName();
				if(arg2Register!=null)arg2=arg2Register.getName();
				if(resultRegister!=null)result=resultRegister.getName();
				codes.add(new MIPSCode(op, arg1, arg2, result));

				if(SymbolTableStack.getItem(fourElement.getResult())!=null){
					codes.add(new MIPSCode("SW", fourElement.getResult(), null, result));
				}
			}
			else if(fourElement.getOp().equals("-")){
				if(isImmediateNum(arg1)
						||isImmediateNum(arg2)
						||symbolTable.isConst(fourElement.getArg1())||symbolTable.isConst(fourElement.getArg2())){//当两个操作数有一个是常数时，该操作是立即数加
					op="SUBI";
				}
				else{//否则是寄存器减
					op="SUB";
				}

				if(symbolTable.isConst(fourElement.getArg1())){
					arg1=(String)symbolTable.getSymbolTableItem(fourElement.getArg1()).getValue();
				}
				if(symbolTable.isConst(fourElement.getArg2())){
					arg2=(String)symbolTable.getSymbolTableItem(fourElement.getArg2()).getValue();
				}
				if(arg1Register!=null)arg1=arg1Register.getName();
				if(arg2Register!=null)arg2=arg2Register.getName();
				if(resultRegister!=null)result=resultRegister.getName();
				codes.add(new MIPSCode(op, arg1, arg2, result));

				if(SymbolTableStack.getItem(fourElement.getResult())!=null){
					codes.add(new MIPSCode("SW", fourElement.getArg1(), null, result));
				}
			}
			else if(fourElement.getOp().equals("*")
					||fourElement.getOp().equals("/")
					||fourElement.getOp().equals("%")){
				if(arg1Register==null){//将arg1取到寄存器中
					arg1Register=getRegisterAllocator().getRegisterList().get(8);
					objectCodeList.add(new MIPSCode("LI", arg1, null, arg1Register.getName()).toString());
				}
				if(arg2Register==null){
					arg2Register=getRegisterAllocator().getRegisterList().get(9);
					objectCodeList.add(new MIPSCode("LI", arg2, null, arg2Register.getName()).toString());
				}
				arg1=arg1Register.getName();
				arg2=arg2Register.getName();
				if(fourElement.getOp().equals("*")){
					op="MULT";					
				}
				else if(fourElement.getOp().equals("/")){
					op="DIV";
				}
				else if(fourElement.getOp().equals("%")){
					op="DIV";
				}
				//OP $arg1,$arg2
				codes.add(new MIPSCode(op,arg1,arg2,null));
				if(!fourElement.getOp().equals("%")){
					//当是乘除时，取出高位结果
					//mfhi $R
					codes.add(new MIPSCode("MFHI",null,null,resultRegister.getName()));
					if(!result.equals(resultRegister)){//当结果不是临时变量，将高位保存在内存中
						//SW $R result
						codes.add(new MIPSCode("SW",result,null,resultRegister.getName()));
					}
				}
				//将低位放入结果寄存器
				//mflo $R
				codes.add(new MIPSCode("MFLO",null,null,resultRegister.getName()));
			}
			else if(fourElement.getOp().equals("JA")||fourElement.getOp().equals("JB")){

				if(isImmediateNum(arg1)
						||isImmediateNum(arg2)
						||symbolTable.isConst(fourElement.getArg1())
						||symbolTable.isConst(fourElement.getArg2())){//当两个操作数有一个是常数时，该操作是立即数加
					op="SLTI";
					if(isImmediateNum(arg1)){
						arg2=arg2Register.getName();
					}
					if(isImmediateNum(arg2)){
						arg1=arg1Register.getName();
					}
				}
				else{//否则是寄存器加
					op="SLT";
					arg1=arg1Register.getName();
					arg2=arg2Register.getName();
				}
				//如果是常变量，获取常变量的值
				if(symbolTable.isConst(fourElement.getArg1())){
					arg2=(String)symbolTable.getSymbolTableItem(fourElement.getArg1()).getValue();
					arg1=fourElement.getArg2();
				}
				if(symbolTable.isConst(fourElement.getArg2())){
					arg1=fourElement.getArg1();
					arg2=(String)symbolTable.getSymbolTableItem(fourElement.getArg2()).getValue();
				}
				//OP $t8,$s1,$s2
				codes.add(new MIPSCode(op,arg1,arg2,"$t8"));


				if(fourElement.getOp().equals("JB")){//BNE $Zero,$t8,result
					op="BNE";
				}else{//BEQ $Zero,$t8,result
					op="BEQ";
				}
				arg1="$Zero";
				arg2="$t8";
				result="?"+result;
				codes.add(new MIPSCode(op,arg1,arg2,result));
			}

			else if(fourElement.getOp().equals("JE")
					||fourElement.getOp().equals("JNE")){
				arg1=arg1Register.getName();
				arg2=arg2Register.getName();
				if(fourElement.getOp().equals("JE")){
					op="BEQ";
				}
				else{
					op="BNE";
				}
				result="?"+result;
				codes.add(new MIPSCode(op,arg1,arg2,result));
			}
			else if(fourElement.getOp().equals("JAE")
					||fourElement.getOp().equals("JBE")){
				result="?"+result;

				//判断大于/小于是否成立 JA,JB
				if(isImmediateNum(arg1)
						||isImmediateNum(arg2)
						||symbolTable.isConst(fourElement.getArg1())
						||symbolTable.isConst(fourElement.getArg2())){//当两个操作数有一个是常数时，该操作是立即数加
					op="SLTI";	
				}
				else{//否则是寄存器比较
					op="SLT";
				}
				arg1=arg1Register.getName();
				arg2=arg2Register.getName();
				//如果是常变量，获取常变量的值
				if(symbolTable.isConst(fourElement.getArg1())){
					arg2=(String)symbolTable.getSymbolTableItem(fourElement.getArg1()).getValue();
					arg1=fourElement.getArg2();
				}
				if(symbolTable.isConst(fourElement.getArg2())){
					arg1=fourElement.getArg1();
					arg2=(String)symbolTable.getSymbolTableItem(fourElement.getArg2()).getValue();
				}
				//JAE/JBE $t8,$s1,$s2
				codes.add(new MIPSCode(op,"$t8",arg1,arg2));


				if(fourElement.getOp().equals("JBE")){//BNE $Zero,$t8,result
					codes.add(new MIPSCode("BNE","$Zero","$t8",result));
				}else{//BEQ $Zero,$t8,result
					codes.add(new MIPSCode("BEQ","$Zero","$t8",result));
				}

				//判断是否相等 JE
				op="BEQ";	
				codes.add(new MIPSCode(op,arg1,arg2,result));
			}
			else if(fourElement.getOp().equals("MINUS")){

			}
			else if(fourElement.getOp().equals("GOTO")){
				op="J";
				result="?"+result;
				arg1=null;
				arg2=null;
				codes.add(new MIPSCode(op,arg1,arg2,result));
			}
			else if(fourElement.getOp().equals("ASSIGN")){
				result=resultRegister.getName();
				arg2=null;
				if(isImmediateNum(arg1)){
					op="LI";
					codes.add(new MIPSCode(op,arg1,arg2,result));
				}
				else{
					
					result=arg1Register.getName();
				}
				
				codes.add(new MIPSCode("SW",fourElement.getResult(),arg2,result));
			}
			else if(fourElement.getOp().equals("PARA")){

			}
			else if(fourElement.getOp().equals("RETURN")){
				codes.add(new MIPSCode("JR",null,null,"$ra"));
			}
			else if(fourElement.getOp().equals("CALL")){
				op="GOTO";
				arg1=null;
				arg2=null;
				result=resultRegister.getName();
				codes.add(new MIPSCode(op,arg1,arg2,result));
			}
			else if(fourElement.getOp().equals("SCANF")){

			}
			else if(fourElement.getOp().equals("PRINTF")){

			}
			else{
				//TODO 其他未定义的
				System.out.println("未定义实现四元式："+fourElement.getOp());
			}

		}
		backFill();
		
	}



	private void backFill(){
		for (Code code:codes) {
			if(code.getRes()!=null&&code.getRes().startsWith("?")){//当是需要回填的代码时
				int fourElementId=Integer.parseInt(code.getRes().substring(1, code.getRes().length()));

				int objectCodeId=fourElements.get(fourElementId-1).getObjectCodeId();

				code.setRes(Integer.toString(objectCodeId));
			}
		}
	}
	
	/**
	 * 生成mips汇编的头部
	 */
	private void mipsHead(){
		int num=GlobalSymbolTable.getSymbolTable().getVarNum();
		if(num<=0)return ;
		objectCodeList.add(".data");
		List<String> list=GlobalSymbolTable.getSymbolTable().getStaticVarNameList();
		
		for (String string : list) {
			objectCodeList.add(string+": .word "+GlobalSymbolTable.getSymbolTable().getSymbolTableItem(string).getValue());
		}
		objectCodeList.add(".text");
	}






	private class MIPSRegisterAllocator extends RegisterAllocator {


		@Override
		protected void init() {
			for(int i=0;i<32;i++){
				registerList.add(new MIPSRegister(i));
			}

		}


		/**
		 * element:(i:A=B op C)
		 * GETREG(i:A=B op C)
		 */
		/*static private int id=0;
		@Override
		public String getRegister(FourElement element) {
			return "$s"+id++;
		}*/
		@Override
		public Register getRegister(FourElement element) {
			String arg1=element.getArg1();
			String arg2=element.getArg2();
			String res=element.getResult();
			Register register=null;
			Register registerB=null;
			Register registerC=null;
			BaseBlockSymbolTable symbolTable=baseBlocks.
					get(element.getCodeBlockNum()).getBaseBlockSymbolTable();

			SymbolTableItem itemB=SymbolTableStack.getItem(arg1);
			SymbolTableItem itemC=SymbolTableStack.getItem(arg2);
			SymbolTableItem itemA=SymbolTableStack.getItem(res);


			if(itemA==null){
				itemA=symbolTable.getSymbolTableItem(res);//获取符号表中的res项
			}
			if(itemB==null){
				itemB=symbolTable.getSymbolTableItem(arg1);
			}
			if(itemC==null){
				itemC=symbolTable.getSymbolTableItem(arg2);
			}
			if(itemA==null){
				//不对该四元式分配结果寄存器
				return null;
			}
			//如果已经在寄存器中，返回存在的寄存器
			for (Integer location : itemA.getVariate().getLocation()) {
				if(location>0){
					return registerList.get(location);
				}
			}
			//(1)如有尚未分配的寄存器，则从中选取一个Ri,为所需寄存器
			if(register==null){
				register=getRegister();
				if(register!=null){
					itemA.getVariate().allotVariate(register);
					return register;
				}
			}
			
			//TODO element:(i:A=B op C)
			//(2)如果B的现行值在寄存器R中，且该寄存器只包含B的值，
			//   或者B和A是同一标识符，或者B在四元式之后不再被引用，则选取Ri为所需寄存器，转（4）
			//判断B是否存在于符号表
			if(itemB!=null){//当B存在时
				Variate bInfo=itemB.getVariate();
				//获取存放B的寄存器
				for(Integer localtion: bInfo.getLocation()){
					if(localtion>0){
						registerB=registerList.get(localtion);
						if(registerB.getAllocatVar().size()==1){
							break;
						}
					}
				}
				//B的现行值在寄存器registerB中，该寄存器只包含B，或者B和A是同意标识符
				if(registerB!=null
						&&registerB.getAllocatVar().size()==1
						||itemB.equals(itemA)){
					//将设置寄存器和A的分配/位置列表
					itemA.getVariate().allotVariate(registerB);
					return registerB;
				}
			}

			if(itemC!=null){//当C存在时
				//当C是定义的变量

				Variate cInfo=itemC.getVariate();
				//TODO 
				for(Integer localtion: cInfo.getLocation()){
					if(localtion>0){
						registerC=registerList.get(localtion);
						if(registerC.getAllocatVar().size()==1){
							break;
						}
					}
				}
				if(registerC!=null
						&&registerC.getAllocatVar().size()==1
						||itemC.equals(itemA)){
					//B的现行值在寄存器registerB中，该寄存器只包含B
					//将设置寄存器和A的分配/位置列表
					itemA.getVariate().allotVariate(registerC);
					return registerC;
				}
			}
			

			//(3)从已分配的寄存器中选取一个Ri作为所需的寄存器R，选取原则为，占用Ri的变量的值也同时存放在主存中
			//	或者在基本块中要在最远的位置才会引用到。这样，对寄存器Ri所含变量做如下调整
			//		对RVALUE[R]（R寄存器分配的变量列表）中的每一个变量M,如果M不是A且AVALUE[M]
			//	(变量M所在的位置)，不含M。则
			//  1.生成目标代码ST Ri，M（将寄存器Ri的内容取到M）
			//  2.如果M是B，则令AVALUE[M]={M,Ri}否则令AVALUE[M]={M}
			//	3.删除RVALUE[Ri]中的M

			if(register==null)register=getRegisterIfNoFreeRegister(element);

			return register;
		}

		@Override
		public Register getRegister(String target, FourElement fourElement,Register resultR) {
			if(target.equals(fourElement.getResult()))return getRegister(fourElement);
			Register returnRegister=null;

			SymbolTableItem targetItem=SymbolTableStack.getItem(target);
			if(targetItem==null){
				targetItem=baseBlocks.get(fourElement.getCodeBlockNum())
						.getBaseBlockSymbolTable()
						.getSymbolTableItem(target);
			}
			if(isImmediateNum(target)){
				//target是数值时
				return null;
			}
			if(targetItem!=null){//B不是字面值常量，即，B是临时变量或者是定义的变量
				int localtion=-1;
				//获取B所在的位置列表
				List<Integer>locationList=targetItem.getVariate().getLocation();
				//从B所在位置列表获取存B的寄存器
				for (Integer registerId : locationList) {
					//如果B在寄存器，获取寄存器
					if(registerId>0){
						localtion=registerId;
						returnRegister=getRegisterAllocator().getRegisterList().get(localtion);
						if(returnRegister.equals(resultR)){//如果BR是R，删除AVALUE[B]中的R，即B将不存放在R
							locationList.remove(registerId);
						}
						break;
					}
				}
				//当B依然未分配寄存器时,为B分配一个空闲的寄存器
				//如有尚未分配的寄存器，则从中选取一个R,为所需寄存器
				if(returnRegister==null){
					returnRegister=getRegisterAllocator().getRegister();
					if(returnRegister!=null){
						codes.add(new MIPSCode("LW", target,null,returnRegister.getName()));
						returnRegister.allotVariate(targetItem.getVariate());
					}
				}
				//当B依然未分配寄存器时,为B分配一个不空闲的寄存器		
				if(returnRegister==null){
					returnRegister=getRegisterAllocator().getRegisterIfNoFreeRegister(fourElement);
					if(returnRegister!=null){
						codes.add(new MIPSCode("LW", target,null,returnRegister.getName()));
						returnRegister.allotVariate(targetItem.getVariate());
					}
				}


			}


			return returnRegister;
		}


		//获取一个空闲的寄存器
		@Override
		public Register getRegister() {
			for(int i=16;i<24;i++){
				Register register=registerList.get(i);
				if(register.getAllocatVar()==null||register.getAllocatVar().size()==0){
					return register;
				}
			}

			return null;
		}


		public Register getRegisterIfNoFreeRegister(FourElement fourElement){


			//(3)从已分配的寄存器中选取一个Ri作为所需的寄存器R，选取原则为，占用Ri的变量的值也同时存放在主存中
			//	或者在基本块中要在最远的位置才会引用到。这样，对寄存器Ri所含变量做如下调整
			//		对RVALUE[R]（R寄存器分配的变量列表）中的每一个变量M,如果M不是A且AVALUE[M]
			//	(变量M所在的位置)，不含M。则
			//  1.生成目标代码ST Ri，M（将寄存器Ri的内容取到M）
			//  2.如果M是B，则令AVALUE[M]={M,Ri}否则令AVALUE[M]={M}
			//	3.删除RVALUE[Ri]中的M

			for(Register reg:getRegisterAllocator().getRegisterList()){//遍历寄存器列表获取目标寄存器
				boolean flag=false;
				for (Variate variate : reg.getAllocatVar()) {//获取寄存器分配的变量
					flag=false;
					if(!variate.getLocation().contains(-1)){//当这个变量不存在内存中时,放弃使用该寄存器
						break;
					}
					flag=true;
				}
				if(flag){//选择该寄存器
					//对RVALUE[R]（R寄存器分配的变量列表）中的每一个变量M,如果M不是A且AVALUE[M](变量M所在的位置)，不含M。则

					for (Variate variate : reg.getAllocatVar()) {
						if(!variate.getLocation().contains(-1)
								&&!variate.getName().equals(fourElement.getResult())){
							//M不在内存中,同时M不是结果
							//  1.生成目标代码ST Ri，M（将寄存器Ri的内容取到M）
							codes.add(new MIPSCode("SW",variate.getName() , "$sp", reg.getName()));

							//  2.如果M是B，则令AVALUE[M]={M,Ri}否则令AVALUE[M]={M}
							variate.getLocation().clear();
							if(variate.getName().equals(fourElement.getArg1())
									||variate.getName().equals(fourElement.getArg2())){
								variate.getLocation().add(reg.getId());
							}
							variate.getLocation().add(-1);
							//	3.删除RVALUE[Ri]中的M
							reg.getAllocatVar().remove(variate);
						}
					}
					return reg;
				}

			}
			return getRegisterByName("$t3");
		}

		public Register getRegisterByName(String name){
			Register register=null;
			for (Register reg : registerList) {
				if(reg.getName().equals(name)){
					register=reg;
				}
			}
			return register;
		}
	}


}
