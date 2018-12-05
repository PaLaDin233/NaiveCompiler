package cn.ed.qut.compiler.zhg.objectCodeGeneration;

public abstract class Code {
	protected String OP;
	protected String arg1;
	protected String arg2;
	protected String res;
	
	
	
	public Code(String oP, String arg1, String arg2, String res) {
		super();
		OP = oP;
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.res = res;
	}
	public String getOP() {
		return OP;
	}
	public void setOP(String oP) {
		OP = oP;
	}
	public String getArg1() {
		return arg1;
	}
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	public String getArg2() {
		return arg2;
	}
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	
	public abstract String toString();
}
