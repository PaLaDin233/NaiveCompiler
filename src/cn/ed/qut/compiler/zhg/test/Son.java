package cn.ed.qut.compiler.zhg.test;

import java.util.Scanner;

public class Son extends Father{
	private String name="Son";
	public Son() {
		setName(new Scanner(System.in).next());
	}
}
