package cn.ed.qut.compiler.zhg.intermediateCodeGeneration;

import java.io.Serializable;

/**
 * 四元式类定义
 */

public class FourElement implements Serializable{
    private int id;//四元式序号，为编程方便
    private String op;//操作符
    private String arg1;//第一个操作数
    private String arg2;//第二个操作数
    private Object result;//结果
    public FourElement(){

    }
    public FourElement(int id,String op,String arg1,String arg2,String result){
        this.setId(id);
        this.setOp(op);
        this.setArg1(arg1);
        this.setArg2(arg2);
        this.setResult(result);
    }
	/**
	 * @return result
	 */
	public Object getResult() {
		return result;
	}
	/**
	 * @param result 要设置的 result
	 */
	public void setResult(Object result) {
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
}
