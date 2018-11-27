package cn.ed.qut.compiler.zhg.test;

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.RegisterAllocator;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips.MIPSRegisterAllocator;

public class test {
	
	
	public static void main(String[] args) {
//		String unicode="abcde爱我而已无语";
//		
//		byte[] unB=unicode.getBytes();
//		
//		System.out.println("unB.length:"+unB.length);
//		
//		System.out.println("unicode.length():"+unicode.length());
//		
//		String f=new String(unB,0,unB.length-1);
//		System.out.println(f);
		
//		String string="asd";
//		String string2=string;
//		
//		string="dsa";
//		System.out.println(string);
//		System.out.println(string2);
		S s=new S();
		s.v=1;
		S s2=s;
		System.out.println(s.v);
		System.out.println(s2.v);
		s.v=2;
		System.out.println(s.v);
		System.out.println(s2.v);
		String unicode="abcde爱我而已无语";
//		
		for(int i=0;i<1000;i++){
			for(int j=0;j<100000;j++){
				S s3=new S();
				s3.v=i*j+j;
			}
		}
		byte[] unB=unicode.getBytes();
		
		System.out.println("unB.length:"+unB.length);
		
		System.out.println("unicode.length():"+unicode.length());
		
		String f=new String(unB,0,unB.length-1);
		System.out.println(f);
		
		String string="asd";
		String string2=string;
		
		string="dsa";
		System.out.println(string);
		System.out.println(string2);
	}
	
}

class S{
	static int v;
	@Override
	protected void finalize() throws Throwable {
		// TODO 自动生成的方法存根
		System.out.println("fi"+v);
		super.finalize();
	}
}
