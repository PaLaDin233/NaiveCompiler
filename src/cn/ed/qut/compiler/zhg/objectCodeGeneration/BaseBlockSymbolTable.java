package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.HashSymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.SymbolTableItem;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

public class BaseBlockSymbolTable extends HashSymbolTable {

	private BaseBlock baseBlock;
	public BaseBlockSymbolTable(BaseBlock baseBlock) {
		this.baseBlock=baseBlock;
		//正则表达式的模式对象
		Pattern p = Pattern.compile("-?\\d*");

		List<FourElement> elements=baseBlock.getElements();
		//从基本块的出口开始
		for (int j=elements.size()-1;j>=0;j--) {
			FourElement fourElement=elements.get(j);

			//获取四元式的三个参数ABC（A=B OP C）
			String arg[] = new String[3];
			arg[0]=fourElement.getArg1();
			arg[1]=fourElement.getArg2();
			arg[2]=fourElement.getResult();

			for(int i=0;i<3;i++){
				if(arg[i]!=null){
					//使用正则表达式模式进行匹配，判断该arg是不是一个数值,如123,-123
					if(!p.matcher(arg[0]).matches()){
						SymbolTableItem item=SymbolTableStack.getItem(arg[i]);
						Variate variate;
						
						if(!exist(arg[i])){
							boolean isActive=true;//是否是基本块之后需要使用的变量
							//从符号表栈中获取变量，当不存在时，说明是临时变量,在该基本块之后不活跃
							
							if(item==null){
								item=SymbolTableItem.getVar(arg[i], null);
								isActive=false;
							}
							//将一项插入到基本块符号表中
							insert(item);
							//获取item的变量属性
							
							
							variate=new Variate();
							item.setVariate(variate);
							//将待用信息初始话为非待用
							variate.getWaitUseInfo().push(-1);
							//根据是否是基本块之后需要使用的变量初始化活跃信息
							variate.getActiveInfo().push(isActive);
						}
						item=getSymbolTableItem(arg[i]);
						variate=item.getVariate();
						//是四元式的result时，设置为非待用，非活跃
						if(i==2){
							variate.getWaitUseInfo().push(-1);
							variate.getActiveInfo().push(false);
						}//是arg1或arg2时
						else{
							variate.getActiveInfo().push(true);
							variate.getWaitUseInfo().push(j+1);
						}
					}
				}
			}
		}//end for (int j=elements.size()-1;j>=0;j++)
	}

	@Override
	public void spacialInsert(SymbolTableItem item) {}

	
	public void print(){
		for (FourElement element : baseBlock.getElements()) {
			String arg1=element.getArg1();
			String arg2=element.getArg2();
			String result=element.getResult();
			String op=element.getOp();
			
			StringBuilder builder=new StringBuilder();
			builder.append(result).append('(');
			nextElement(result);
			builder.append(info.getWaitUseInfo());
			builder.append(info.getActionInfo());
			builder.append(')');
			builder.append('=');
			builder.append(arg1).append('(');
			nextElement(arg1);
			builder.append(info.getWaitUseInfo());
			builder.append(info.getActionInfo());
			builder.append(')');
			builder.append(op);
			builder.append(arg2).append('(');
			nextElement(arg2);
			builder.append(info.getWaitUseInfo());
			builder.append(info.getActionInfo());
			builder.append(')');
			System.out.println(builder.toString());
		}
	}
	private Info info;
	public void nextElement(String itemName){
		SymbolTableItem item=getSymbolTableItem(itemName);
		Variate variate=item.getVariate();
		info=new Info();
		variate.getActiveInfo().pop();
		info.setActionInfo(variate.getActiveInfo().peek());
		variate.getWaitUseInfo().pop();
		info.setWaitUseInfo(variate.getWaitUseInfo().peek());
	}
	
	public Info getInfo(){
		return info;
	}
	
	private class Info{
		private Boolean actionInfo;
		private Integer waitUseInfo;
		/**
		 * @return actionInfo
		 */
		public Boolean getActionInfo() {
			return actionInfo;
		}
		/**
		 * @param actionInfo 要设置的 actionInfo
		 */
		private void setActionInfo(Boolean actionInfo) {
			this.actionInfo = actionInfo;
		}
		/**
		 * @return waitUseInfo
		 */
		public Integer getWaitUseInfo() {
			return waitUseInfo;
		}
		/**
		 * @param waitUseInfo 要设置的 waitUseInfo
		 */
		private void setWaitUseInfo(Integer waitUseInfo) {
			this.waitUseInfo = waitUseInfo;
		}
		
		
	}
}
