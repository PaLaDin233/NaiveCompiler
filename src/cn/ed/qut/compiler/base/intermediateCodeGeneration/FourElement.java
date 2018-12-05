package cn.ed.qut.compiler.base.intermediateCodeGeneration;

import java.io.Serializable;

/**
 * 四元式类定义
 */

public class FourElement implements Serializable{
    private int id;//四元式序号，为编程方便
    private String op;//操作符
    private String arg1;//第一个操作数
    private String arg2;//第二个操作数
    private String result;//结果
    private int codeBlockNum;//四元式所在代码块号
    private int objectCodeId;//四元式对应生成的第一条目标代码号
    
    public FourElement(){

    }
    public FourElement(int id,String op,String arg1,String arg2,String result){
        this.setId(id);
        this.setOp(op);
        this.setArg1(arg1);
        this.setArg2(arg2);
        this.setResult(result);
    }
    public FourElement(int id,String op,String arg1,String arg2,String result,int codeBlockNum){
        this.setId(id);
        this.setOp(op);
        this.setArg1(arg1);
        this.setArg2(arg2);
        this.setResult(result);
        this.setCodeBlockNum(codeBlockNum);
    }
	/**
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result 要设置的 result
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return op
	 */
	public String getOp() {
		return op;
	}
	/**
	 * @param op 要设置的 op
	 */
	public void setOp(String op) {
		this.op = op;
	}
	/**
	 * @return arg1
	 */
	public String getArg1() {
		return arg1;
	}
	/**
	 * @param arg1 要设置的 arg1
	 */
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	/**
	 * @return arg2
	 */
	public String getArg2() {
		return arg2;
	}
	/**
	 * @param arg2 要设置的 arg2
	 */
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	/**
	 * @return codeBlockNum
	 */
	public int getCodeBlockNum() {
		return codeBlockNum;
	}
	/**
	 * @param codeBlockNum 要设置的 codeBlockNum
	 */
	public void setCodeBlockNum(int codeBlockNum) {
		this.codeBlockNum = codeBlockNum;
	}
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append(result).append("=").append(arg1).append(op).append(arg2);
		return builder.toString();
	}
	/**
	 * @return objectCodeId
	 */
	public int getObjectCodeId() {
		return objectCodeId;
	}
	/**
	 * @param objectCodeId 要设置的 objectCodeId
	 */
	public void setObjectCodeId(int objectCodeId) {
		this.objectCodeId = objectCodeId;
	}
	
	
}
