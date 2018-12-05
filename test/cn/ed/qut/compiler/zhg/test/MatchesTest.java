package cn.ed.qut.compiler.zhg.test;

import java.util.Scanner;

public class MatchesTest {
	
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String input=scanner.nextLine();
		while(!input.equals("exit")){
			System.out.println(input.matches("-?\\d*\\.?\\d*"));
			input=scanner.nextLine();
		}
	}
	
}
