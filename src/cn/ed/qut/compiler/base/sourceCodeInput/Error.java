package cn.ed.qut.compiler.base.sourceCodeInput;

import cn.ed.qut.compiler.base.wordSegmenter.Word;

public class Error {
private int id ;//������ţ�
private String info;//������Ϣ��
private int line ;//����������
private Word word;//����ĵ���
public Error(){
	
}
//public Error(int id,String info,int line){
//	this.id=id;
//	this.info=info;
//	this.line=line;
//}
public Error(int id,String info,int line,Word word){
	this.setId(id);
	this.setInfo(info);
	this.setLine(line);
	this.setWord(word);
}
/**
 * @return info
 */
public String getInfo() {
	return info;
}
/**
 * @param info Ҫ���õ� info
 */
public void setInfo(String info) {
	this.info = info;
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
 * @return word
 */
public Word getWord() {
	return word;
}
/**
 * @param word Ҫ���õ� word
 */
public void setWord(Word word) {
	this.word = word;
}
}
