package cn.ed.qut.compiler.zhg.intermediateCodeGeneration;

import java.io.Serializable;

/**
 * ��Ԫʽ�ඨ��
 */

public class FourElement implements Serializable{
    private int id;//��Ԫʽ��ţ�Ϊ��̷���
    private String op;//������
    private String arg1;//��һ��������
    private String arg2;//�ڶ���������
    private Object result;//���
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
	 * @param result Ҫ���õ� result
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
	 * @param id Ҫ���õ� id
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
	 * @param op Ҫ���õ� op
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
	 * @param arg1 Ҫ���õ� arg1
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
	 * @param arg2 Ҫ���õ� arg2
	 */
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
}
