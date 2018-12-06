package cn.ed.qut.compiler.base.parsing;

import java.io.*;import java.util.ArrayList;
import cn.ed.qut.compiler.base.wordSegmenter.Word;
import cn.ed.qut.compiler.base.sourceCodeInput.Error;;

/**
 * 分析栈节点类
 *	String type;//节点类型
	String name;//节点名
	Object value;//节点值
 */
public class AnalyseNode {
	public final static String NONTERMINAL="非终结符";
	public final static String TERMINAL="终结符";
	public final static String ACTIONSIGN="动作符";
	public final static String END="结束符";
	static ArrayList<String>nonterminal=new ArrayList<String>();//非终结符集合
	static ArrayList<String>actionSign=new ArrayList<String>();//动作符集合
	static{
		//N:S,B,A,C,,X,R,Z,Z’,U,U’,E,E’,H,H’,G,M,D,L,L’,T,T’,F,O,P,Q
		nonterminal.add("S");
		nonterminal.add("A");
		nonterminal.add("B");
		nonterminal.add("C");
		nonterminal.add("D");
		nonterminal.add("E");
		nonterminal.add("F");
		nonterminal.add("G");
		nonterminal.add("H");
		nonterminal.add("L");
		nonterminal.add("M");
		nonterminal.add("O");
		nonterminal.add("P");
		nonterminal.add("Q");
		nonterminal.add("X");
		nonterminal.add("Y");
		nonterminal.add("Z");
		nonterminal.add("R");
		nonterminal.add("U");
		nonterminal.add("Z'");
		nonterminal.add("U'");
		nonterminal.add("E'");
		nonterminal.add("H'");
		nonterminal.add("L'");
		nonterminal.add("T");
		nonterminal.add("T'");
		actionSign.add("@ADD_SUB");
		actionSign.add("@ADD");
		actionSign.add("@SUB");
		actionSign.add("@DIV_MUL");
		actionSign.add("@DIV");
		actionSign.add("@MUL");
		actionSign.add("@SINGLE");
		actionSign.add("@SINGTLE_OP");
		actionSign.add("@ASS_R");
		actionSign.add("@ASS_Q");
		actionSign.add("@ASS_F");
		actionSign.add("@TRAN_LF");
		actionSign.add("@EQ");
	}
	
	String type;//节点类型
	String name;//节点名
	String value;//节点值
	
	public static boolean isNonterm(cn.ed.qut.compiler.base.parsing.AnalyseNode node){
		return nonterminal.contains(node.name);
	}
	public static boolean isTerm(cn.ed.qut.compiler.base.parsing.AnalyseNode node){
		return Word.isKey(node.name)||Word.isOperator(node.name)||Word.isBoundarySign(node.name)
		||node.name.equals("id")||node.name.equals("num")||node.name.equals("ch");
	}
	public static boolean isActionSign(cn.ed.qut.compiler.base.parsing.AnalyseNode node){
		return actionSign.contains(node.name);
	}
	public AnalyseNode(){
		
	}
public AnalyseNode(String type,String name,String value){
		this.type=type;
		this.name=name;
		this.value=value;
	}
}
