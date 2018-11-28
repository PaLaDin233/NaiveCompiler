package cn.ed.qut.compiler.base.parsing;

import java.io.*;import java.util.ArrayList;
import cn.ed.qut.compiler.base.wordSegmenter.Word;
import cn.ed.qut.compiler.base.sourceCodeInput.Error;;

/**
 * ����ջ�ڵ���
 *	String type;//�ڵ�����
	String name;//�ڵ���
	Object value;//�ڵ�ֵ
 */
public class AnalyseNode {
	public final static String NONTERMINAL="���ս��";
	public final static String TERMINAL="�ս��";
	public final static String ACTIONSIGN="������";
	public final static String END="������";
	static ArrayList<String>nonterminal=new ArrayList<String>();//���ս������
	static ArrayList<String>actionSign=new ArrayList<String>();//����������
	static{
		//N:S,B,A,C,,X,R,Z,Z��,U,U��,E,E��,H,H��,G,M,D,L,L��,T,T��,F,O,P,Q
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
	
	String type;//�ڵ�����
	String name;//�ڵ���
	String value;//�ڵ�ֵ
	
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

/**
 * �ʷ�������
 *
 * @author KB
 *
 */
public static class LexAnalyse {

	ArrayList<Word> wordList = new ArrayList<Word>();// ���ʱ�
	ArrayList<Error> errorList = new ArrayList<Error>();// ������Ϣ�б�
	int wordCount = 0;// ͳ�Ƶ��ʸ���
	int errorCount = 0;// ͳ�ƴ������
	boolean noteFlag = false;// ����ע�ͱ�־
	boolean lexErrorFlag = false;// �ʷ����������־

	/**
	 * �����ַ��ж�
	 *
	 * @param ch
	 * @return
	 */
	private static boolean isDigit(char ch) {
		boolean flag = false;
		if ('0' <= ch && ch <= '9')
			flag = true;
		return flag;
	}

	/**
	 * �жϵ����Ƿ�Ϊint����
	 *
	 * @param string
	 * @return
	 */
	private static boolean isDigit(String word) {
		int i;
		boolean flag = false;
		for (i = 0; i < word.length(); i++) {
			if (Character.isDigit(word.charAt(i))) {
				continue;
			} else {
				break;
			}
		}
		if (i == word.length()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * �жϵ����Ƿ�Ϊchar����
	 *
	 * @param word
	 * @return
	 */
	private static boolean isChar(String word) {
		boolean flag = false;
		int i = 0;
		char temp = word.charAt(i);
		if (temp == '\'') {
			for (i = 1; i < word.length(); i++) {
				temp = word.charAt(i);
				if (0 <= temp && temp <= 255)
					continue;
				else
					break;
			}
			if (i + 1 == word.length() && word.charAt(i) == '\'')
				flag = true;
		} else
			return flag;

		return flag;
	}

	/**
	 * �ж��ַ��Ƿ�Ϊ��ĸ
	 *
	 * @param ch
	 * @return
	 */
	private static boolean isLetter(char ch) {
		boolean flag = false;
		if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z'))
			flag = true;
		return flag;
	}

	/**
	 * �жϵ����Ƿ�Ϊ�Ϸ���ʶ��
	 *
	 * @param word
	 * @return
	 */
	private static boolean isID(String word) {
		boolean flag = false;
		int i = 0;
		if (Word.isKey(word))
			return flag;
		char temp = word.charAt(i);
		if (isLetter(temp) || temp == '_') {
			for (i = 1; i < word.length(); i++) {
				temp = word.charAt(i);
				if (isLetter(temp) || temp == '_' || isDigit(temp))
					continue;
				else
					break;
			}
			if (i >= word.length())
				flag = true;
		} else
			return flag;

		return flag;
	}

	/**
	 * �жϴʷ������Ƿ�ͨ��
	 *
	 */
	public boolean isFail() {
		return lexErrorFlag;
	}

	public void analyse(String str, int line) {
		int beginIndex;
		int endIndex;
		int index = 0;
		int length = str.length();
		Word word = null;
		Error error;
		// boolean flag=false;
		char temp;
		while (index < length) {
			temp = str.charAt(index);
			if (!noteFlag) {
				if (isLetter(temp) || temp == '_') {// �ж��ǲ��Ǳ�־��
					beginIndex = index;
					index++;
					// temp=str.charAt(index);
					while ((index < length)
							&& (!Word.isBoundarySign(str.substring(index,
									index + 1)))
							&& (!Word.isOperator(str
									.substring(index, index + 1)))
							&& (str.charAt(index) != ' ')
							&& (str.charAt(index) != '\t')
							&& (str.charAt(index) != '\r')
							&& (str.charAt(index) != '\n')) {
						index++;
						// temp=str.charAt(index);
					}
					endIndex = index;
					word = new Word();
					wordCount++;
					word.setId(wordCount);
					word.setLine(line);
					word.setValue(str.substring(beginIndex, endIndex));
					if (Word.isKey(word.getValue())) {
						word.setType(Word.KEY);
					} else if (isID(word.getValue())) {
						word.setType(Word.IDENTIFIER);
					} else {
						word.setType(Word.UNIDEF);
						word.setFlag(false);
						errorCount++;
						error = new Error(errorCount, "�Ƿ���ʶ��", word.getLine(), word);
						errorList.add(error);
						lexErrorFlag = true;
					}
					index--;

				} else if (isDigit(temp)) {// �ж��ǲ���int����

					beginIndex = index;
					index++;
					// temp=str.charAt(index);
					while ((index < length)
							&& (!Word.isBoundarySign(str.substring(index,
									index + 1)))
							&& (!Word.isOperator(str
									.substring(index, index + 1)))
							&& (str.charAt(index) != ' ')
							&& (str.charAt(index) != '\t')
							&& (str.charAt(index) != '\r')
							&& (str.charAt(index) != '\n')) {
						index++;
						// temp=str.charAt(index);
					}
					endIndex = index;
					word = new Word();
					wordCount++;
					word.setId(wordCount);
					word.setLine(line);
					word.setValue(str.substring(beginIndex, endIndex));
					if (isDigit(word.getValue())) {
						word.setType(Word.INT_CONST);
					} else {
						word.setType(Word.UNIDEF);
						word.setFlag(false);
						errorCount++;
						error = new Error(errorCount, "�Ƿ���ʶ��", word.getLine(), word);
						errorList.add(error);
						lexErrorFlag = true;
					}
					index--;
				} else if (String.valueOf(str.charAt(index)).equals("'")) {// �ַ�����
					// flag=true;
					beginIndex = index;
					index++;
					temp = str.charAt(index);
					while (index < length && (0 <= temp && temp <= 255)) {
						if (String.valueOf(str.charAt(index)).equals("'"))
							break;
						index++;
						// temp=str.charAt(index);
					}
					if (index < length) {
						endIndex = index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.CHAR_CONST);
						// flag=true;
						// word.flag=flag;
						index--;
					} else {
						endIndex = index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.UNIDEF);
						word.setFlag(false);
						errorCount++;
						error = new Error(errorCount, "�Ƿ���ʶ��", word.getLine(), word);
						errorList.add(error);
						lexErrorFlag = true;
						index--;
					}
				} else if (temp == '=') {
					beginIndex = index;
					index++;
					if (index < length && str.charAt(index) == '=') {
						endIndex = index + 1;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.OPERATOR);
					} else {
						// endIndex=index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
						index--;
					}
				} else if (temp == '!') {
					beginIndex = index;
					index++;
					if (index < length && str.charAt(index) == '=') {
						endIndex = index + 1;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.OPERATOR);
						index++;
					} else {
						// endIndex=index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
						index--;
					}
				} else if (temp == '&') {
					beginIndex = index;
					index++;
					if (index < length && str.charAt(index) == '&') {
						endIndex = index + 1;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.OPERATOR);
					} else {
						// endIndex=index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
						index--;
					}
				} else if (temp == '|') {
					beginIndex = index;
					index++;
					if (index < length && str.charAt(index) == '|') {
						endIndex = index + 1;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.OPERATOR);
					} else {
						// endIndex=index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
						index--;
					}
				} else if (temp == '+') {
					beginIndex = index;
					index++;
					if (index < length && str.charAt(index) == '+') {
						endIndex = index + 1;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.OPERATOR);

					} else {
						// endIndex=index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
						index--;
					}
				} else if (temp == '-') {
					beginIndex = index;
					index++;
					if (index < length && str.charAt(index) == '-') {
						endIndex = index + 1;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(beginIndex, endIndex));
						word.setType(Word.OPERATOR);
					} else {
						// endIndex=index;
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
						index--;
					}
				} else if (temp == '/') {
					index++;
					if (index < length && str.charAt(index) == '/')
						break;
					/*
					 * { index++; while(str.charAt(index)!='\n'){ index++; } }
					 */
					else if (index < length && str.charAt(index) == '*') {
						noteFlag = true;
					} else {
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(str.substring(index - 1, index));
						word.setType(Word.OPERATOR);
					}
					index--;
				} else {// ���Ǳ�ʶ�������ֳ������ַ�������

					switch (temp) {
					case ' ':
					case '\t':
					case '\r':
					case '\n':
						word = null;
						break;// ���˿հ��ַ�
					case '[':
					case ']':
					case '(':
					case ')':
					case '{':
					case '}':
					case ',':
					case '"':
					case '.':
					case ';':
						// case '+':
						// case '-':
					case '*':
						// case '/':
					case '%':
					case '>':
					case '<':
					case '?':
					case '#':
						word = new Word();
						word.setId(++wordCount);
						word.setLine(line);
						word.setValue(String.valueOf(temp));
						if (Word.isOperator(word.getValue()))
							word.setType(Word.OPERATOR);
						else if (Word.isBoundarySign(word.getValue()))
							word.setType(Word.BOUNDARYSIGN);
						else
							word.setType(Word.END);
						break;
					default:
						word = new Word();
						wordCount++;
						word.setId(wordCount);
						word.setLine(line);
						word.setValue(String.valueOf(temp));
						word.setType(Word.UNIDEF);
						word.setFlag(false);
						errorCount++;
						error = new Error(errorCount, "�Ƿ���ʶ��", word.getLine(), word);
						errorList.add(error);
						lexErrorFlag = true;
					}
				}
			} else {
				int i = str.indexOf("*/");
				if (i != -1) {
					noteFlag = false;
					index = i + 2;
					continue;
				} else
					break;
			}
			if (word == null) {
				index++;
				continue;
			}

			wordList.add(word);
			index++;
		}
	}

	public ArrayList<Word> lexAnalyse(String str) {
		String buffer[];
		buffer = str.split("\n");
		int line = 1;
		for (int i = 0; i < buffer.length; i++) {
			analyse(buffer[i].trim(), line);
			line++;
		}
		if (!wordList.get(wordList.size() - 1).getType().equals(Word.END)) {
			Word word = new Word(++wordCount, "#", Word.END, line++);
			wordList.add(word);
		}
		return wordList;
	}

	public ArrayList<Word> lexAnalyse1(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		BufferedInputStream bis = new BufferedInputStream(fis);
		InputStreamReader isr = new InputStreamReader(bis, "utf-8");
		BufferedReader inbr = new BufferedReader(isr);
		String str = "";
		int line = 1;
		while ((str = inbr.readLine()) != null) {
			// System.out.println(str);
			analyse(str.trim(), line);
			line++;
		}
		inbr.close();
		if (!wordList.get(wordList.size() - 1).getType().equals(Word.END)) {
			Word word = new Word(++wordCount, "#", Word.END, line++);
			wordList.add(word);
		}
		return wordList;
	}

	public String outputWordList() throws IOException {
		File file = new File("./output/");
		if (!file.exists()) {
			file.mkdirs();
			file.createNewFile();// �������ļ������ھʹ�����
		}
		String path = file.getAbsolutePath();
		FileOutputStream fos = new FileOutputStream(path + "/wordList.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		OutputStreamWriter osw1 = new OutputStreamWriter(bos, "utf-8");
		PrintWriter pw1 = new PrintWriter(osw1);
		pw1.println("�������\t���ʵ�ֵ\t\t��������\t\t���������� \t�����Ƿ�Ϸ�");
		Word word;
		for (int i = 0; i < wordList.size(); i++) {
			word = wordList.get(i);
			pw1.println(word.getId() + "\t" + word.getValue() + "\t\t" + word.getType() + "\t"
					+ "\t" + word.getLine() + "\t" + word.isFlag());
		}
		if (lexErrorFlag) {
			Error error;
			pw1.println("������Ϣ���£�");

			pw1.println("�������\t������Ϣ\t���������� \t���󵥴�");
			for (int i = 0; i < errorList.size(); i++) {
				error = errorList.get(i);
				pw1.println(error.getId() + "\t" + error.getInfo() + "\t\t" + error.getLine()
						+ "\t" + error.getWord().getValue());
			}
		} else {
			pw1.println("�ʷ�����ͨ����");
		}
		pw1.close();
		return path + "/wordList.txt";
	}

	public LexAnalyse() {

	}

	public LexAnalyse(String str) {
		lexAnalyse(str);
	}

	public static void main(String[] args) throws IOException {
		cn.ed.qut.compiler.base.parsing.LexAnalyse lex = new cn.ed.qut.compiler.base.parsing.LexAnalyse();
		lex.lexAnalyse1("D:\\My Documents\\Workspace\\tinyCompiler\\b.c");
		lex.outputWordList();
	}
}}
