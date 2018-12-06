package cn.ed.qut.compiler.zhg.test;

import java.util.Stack;

public class TestDemo {
	public static void main(String[] args) {
		Stack<Integer> stack=new Stack<>();
		
		stack.push(0);
		stack.push(1);
		
		System.out.println(stack.get(0));
	}
}
