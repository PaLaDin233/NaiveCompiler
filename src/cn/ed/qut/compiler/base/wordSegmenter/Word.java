package cn.ed.qut.compiler.base.wordSegmenter;

import java.util.ArrayList;
import cn.ed.qut.compiler.base.parsing.AnalyseNode;

/**
 * 单词类
 * 1、单词序号 2、单词的值 3、单词类型 4、单词所在行 5、单词是否合法
 */
public class Word {
	public final static String KEY = "关键字";
	public final static String OPERATOR = "运算符";
	public final static String INT_CONST = "整形常量";
	public final static String CHAR_CONST = "字符常量";
	public final static String BOOL_CONST = "布尔常量";
	public final static String IDENTIFIER = "标志符";
	public final static String BOUNDARYSIGN = "界符";
	public final static String END = "结束符";
	public final static String UNIDEF = "未知类型";
	public static ArrayList<String> key = new ArrayList<String>();// 关键字集合
	public static ArrayList<String> boundarySign = new ArrayList<String>();// 界符集合
	public static ArrayList<String> operator = new ArrayList<String>();// 运算符集合
	static {
		Word.operator.add("+");
		Word.operator.add("-");
		Word.operator.add("++");
		Word.operator.add("--");
		Word.operator.add("*");
		Word.operator.add("/");
		Word.operator.add(">");
		Word.operator.add("<");
		Word.operator.add(">=");
		Word.operator.add("<=");
		Word.operator.add("==");
		Word.operator.add("!=");
		Word.operator.add("=");
		Word.operator.add("&&");
		Word.operator.add("||");
		Word.operator.add("!");
		Word.operator.add(".");
		Word.operator.add("?");
		Word.operator.add("|");
		Word.operator.add("&");
		Word.boundarySign.add("(");
		Word.boundarySign.add(")");
		Word.boundarySign.add("{");
		Word.boundarySign.add("}");
		Word.boundarySign.add(";");
		Word.boundarySign.add(",");
		Word.key.add("void");
		Word.key.add("main");
		Word.key.add("int");
		Word.key.add("char");
		Word.key.add("if");
		Word.key.add("else");
		Word.key.add("while");
		Word.key.add("for");
		Word.key.add("printf");
		Word.key.add("scanf");
	}
	private int id;// 单词序号
	private String value;// 单词的值
	private String type;// 单词类型
	private int line;// 单词所在行
	private boolean flag = true;//单词是否合法

	public Word() {

	}

	public Word(int id, String value, String type, int line) {
		this.setId(id);
		this.setValue(value);
		this.setType(type);
		this.setLine(line);
	}

	public static boolean isKey(String word) {
		return key.contains(word);
	}

	public static boolean isOperator(String word) {
		return operator.contains(word);
	}

	public static boolean isBoundarySign(String word) {
		return boundarySign.contains(word);
	}

	public static boolean isArOP(String word) {// 判断单词是否为算术运算符
		for(int i=0;i<3;){}
		if ((word.equals("+") || word.equals("-") || word.equals("*") || word
				.equals("/")))
			return true;
		else
			return false;
	}

	public static boolean isBoolOP(String word) {// 判断单词是否为布尔运算符
		if ((word.equals(">") || word.equals("<") || word.equals("==")
				|| word.equals("!=") || word.equals("!") || word.equals("&&") || word
				.equals("||")))
			return true;
		else
			return false;
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
	 * @return line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @param line 要设置的 line
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value 要设置的 value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag 要设置的 flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	
	
}