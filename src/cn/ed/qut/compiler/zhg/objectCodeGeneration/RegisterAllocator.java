package cn.ed.qut.compiler.zhg.objectCodeGeneration;

import java.util.ArrayList;
import java.util.List;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;

/**
 * ����Ĵ���������
 * ���๦�ܣ��Ծ���ָ��µļĴ������з���
 * @author ���
 *
 */
public abstract class RegisterAllocator {
	protected List<Register> registerList=new ArrayList<>();//�Ĵ����б�
	
	
	public RegisterAllocator() {
		init();
	}
	
	/**
	 * �Է��������г�ʼ������ӼĴ���
	 */
	protected abstract void init();
	
	/**
	 * 
	 * @param source �������Դ
	 * @param element ��Ԫʽ
	 * @return ����ļĴ���
	 */
	public abstract String getRegister(String source,FourElement element);
	
	/**
	 * 
	 * @return δ��ʹ�õ�ͨ�üĴ����ļĴ�������,��MIPS�µ�$s0
	 */
	public abstract String getRegister(String source);

	public String getRegister(FourElement element) {
		// TODO �Զ����ɵķ������
		return null;
	}


	
	
}
