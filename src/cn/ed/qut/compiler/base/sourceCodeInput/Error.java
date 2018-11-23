package cn.ed.qut.compiler.base.sourceCodeInput;

import cn.ed.qut.compiler.base.wordSegmenter.Word;

public class Error {
private int id ;//错误序号；
private String info;//错误信息；
private int line ;//错误所在行
private Word word;//错误的单词
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
 * @param info 要设置的 info
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
 * @return word
 */
public Word getWord() {
	return word;
}
/**
 * @param word 要设置的 word
 */
public void setWord(Word word) {
	this.word = word;
}
}
