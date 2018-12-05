package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import cn.ed.qut.compiler.zhg.objectCodeGeneration.Code;

public class MIPSCode extends Code{
	public MIPSCode(String oP, String arg1, String arg2, String res) {
		super(oP,arg1, arg2, res);
	}
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder(OP);
		/**
		 * 乘除时
		 * MULT与DIV的格式
		 * MULT $s1,$s2->
		 * 		OP="MULT",arg1="$s1",arg2="$s2",res=null
		 */
		if(OP.toUpperCase().equals("MULT")
				||OP.toUpperCase().equals("DIV")){
			return builder.append(' ').append(arg1).append(", ").append(arg2).toString();
		}
		/**
		 * BEQ和BNE
		 */
		if(OP.toUpperCase().equals("BEQ")
				||OP.toUpperCase().equals("BNE")){
			return builder.append(' ')
					.append(arg1)
					.append(", ")
					.append(arg2)
					.append(", ")
					.append(res)
					.toString();
		}
		if(res!=null)builder.append(" ").append(res);
		if(arg1!=null)builder.append(", ").append(arg1);
		/**
		 * 存取时
		 * LW与SW的格式
		 * LW $s1,100($s2)->
		 * 		OP="LW",arg1="100",arg2="$s2",res="$s1"
		 */
		if(arg2!=null){
			if(OP.toUpperCase().equals("LW")||
					OP.toUpperCase().equals("SW")){
				return builder.append('(').append(arg2).append(')').toString();
			}
			builder.append(", ").append(arg2);
		}
		return builder.toString();
	}
	public static void main(String[] args) {
		MIPSCode code=new MIPSCode("LW", "100", "$sp", "$s1");
		System.out.println(code);
		code=new MIPSCode("MULT", "$s1", "$s2", null);
		System.out.println(code);
		code=new MIPSCode("BEQ","$s1","$s2","100");
		System.out.println(code);
	}
}
