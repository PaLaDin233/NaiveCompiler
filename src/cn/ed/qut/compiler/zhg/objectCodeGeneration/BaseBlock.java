package cn.ed.qut.compiler.zhg.objectCodeGeneration;
/**
 * 基本块
 * @author 清居
 *
 */

import java.util.ArrayList;
import java.util.List;

import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

public class BaseBlock {
	private int id;//基本块的编号
	
	private List<FourElement> elements;//基本块的四元式列表
	
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
}
