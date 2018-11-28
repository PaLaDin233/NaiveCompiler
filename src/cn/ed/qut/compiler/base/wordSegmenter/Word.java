package cn.ed.qut.compiler.base.wordSegmenter;

import java.util.ArrayList;
import cn.ed.qut.compiler.base.parsing.AnalyseNode;

/**
 * ������
 * 1��������� 2�����ʵ�ֵ 3���������� 4������������ 5�������Ƿ�Ϸ�
 */
public class Word {
	public final static String KEY = "�ؼ���";
	public final static String OPERATOR = "�����";
	public final static String INT_CONST = "���γ���";
	public final static String CHAR_CONST = "�ַ�����";
	public final static String BOOL_CONST = "��������";
	public final static String IDENTIFIER = "��־��";
	public final static String BOUNDARYSIGN = "���";
	public final static String END = "������";
	public final static String UNIDEF = "δ֪����";
	public static ArrayList<String> key = new ArrayList<String>();// �ؼ��ּ���
	public static ArrayList<String> boundarySign = new ArrayList<String>();// �������
	public static ArrayList<String> operator = new ArrayList<String>();// ���������
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
	private int id;// �������
	private String value;// ���ʵ�ֵ
	private String type;// ��������
	private int line;// ����������
	private boolean flag = true;//�����Ƿ�Ϸ�

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

	public static boolean isArOP(String word) {// �жϵ����Ƿ�Ϊ���������
		for(int i=0;i<3;){}
		if ((word.equals("+") || word.equals("-") || word.equals("*") || word
				.equals("/")))
			return true;
		else
			return false;
	}

	public static boolean isBoolOP(String word) {// �жϵ����Ƿ�Ϊ���������
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
	 * @param id Ҫ���õ� id
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
	 * @param line Ҫ���õ� line
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
	 * @param value Ҫ���õ� value
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
	 * @param type Ҫ���õ� type
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
	 * @param flag Ҫ���õ� flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	
	
}