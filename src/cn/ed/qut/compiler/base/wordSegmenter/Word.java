package cn.ed.qut.compiler.base.wordSegmenter;

import java.util.ArrayList;

/**
 * ������
 * 
 * @author Administrator 1��������� 2�����ʵ�ֵ 3���������� 4������������ 5�������Ƿ�Ϸ�
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
		Word.boundarySign.add("'");
		Word.boundarySign.add("\"");
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
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private String value;// ���ʵ�ֵ
	private String type;// ��������
	private String attribute;//���ʵ�����
	private int line;// ����������
	private boolean flag = true;//�����Ƿ�Ϸ�

	public Word() {

	}

	public Word(int id, String value, String type, int line) {
		this.id = id;
		this.value = value;
		this.type = type;
		this.line = line;
	}

	public static boolean isKey(String word) {
		return key.contains(word);
	}

	public static boolean isOperator(String word) {
		return operator.contains(word);
	}

	/**
	 * �ж��ǲ��ǽ��
	 * @param word
	 * @return
	 */
	public static boolean isBoundarySign(String word) {
		return boundarySign.contains(word);
	}

	/**
	 * �жϵ����Ƿ�Ϊ���������
	 * @param word
	 * @return
	 */
	public static boolean isArOP(String word) {
		if ((word.equals("+") || word.equals("-") || word.equals("*") || word
				.equals("/")))
			return true;
		else
			return false;
	}

	/**
	 *  �жϵ����Ƿ�Ϊ���������
	 * @param word
	 * @return
	 */
	public static boolean isBoolOP(String word) {
		if ((word.equals(">") || word.equals("<") || word.equals("==")
				|| word.equals("!=") || word.equals("!") || word.equals("&&") || word
				.equals("||")))
			return true;
		else
			return false;
	}
}
