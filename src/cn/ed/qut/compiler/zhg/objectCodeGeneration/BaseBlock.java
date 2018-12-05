package cn.ed.qut.compiler.zhg.objectCodeGeneration;
/**
 * 基本块
 * @author 清居
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

public class BaseBlock {
	private int id;//基本块的编号
	
	private List<FourElement> elements;//基本块的四元式列表
	
	private BaseBlockSymbolTable baseBlockSymbolTable;

	public BaseBlock() {
	}
	
	public BaseBlock(int id) {
		super();
		this.id = id;
	}



	public void addFourElement(FourElement element){
		if(elements==null)elements=new ArrayList<>();
		element.setCodeBlockNum(id);
		this.elements.add(element);
	}
	
	public List<FourElement> getElements() {
		return elements;
	}

	@Override
	public String toString() {
		Iterator<FourElement> iterator=elements.iterator();
		StringBuilder builder=new StringBuilder();
		builder.append("BaseBlock[\n");
		while(iterator.hasNext()){
			builder.append(iterator.next());
			builder.append("\n");
		}
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return baseBlockSymbolTable
	 */
	public BaseBlockSymbolTable getBaseBlockSymbolTable() {
		return baseBlockSymbolTable;
	}

	/**
	 * @param baseBlockSymbolTable 要设置的 baseBlockSymbolTable
	 */
	public void setBaseBlockSymbolTable() {
		this.baseBlockSymbolTable = new BaseBlockSymbolTable(this);
	}
	
	
}
