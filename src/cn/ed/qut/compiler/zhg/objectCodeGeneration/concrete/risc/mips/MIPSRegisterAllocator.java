package cn.ed.qut.compiler.zhg.objectCodeGeneration.concrete.risc.mips;

import java.util.ArrayList;

import cn.ed.qut.compiler.base.dataStruct.symbolTable.abstruct.SymbolTable;
import cn.ed.qut.compiler.base.dataStruct.symbolTable.module.Variate;
import cn.ed.qut.compiler.base.intermediateCodeGeneration.FourElement;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.Register;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.RegisterAllocator;
import cn.ed.qut.compiler.zhg.objectCodeGeneration.SymbolTableStack;

/**
 * MIPSָ��µļĴ���������
 * @author ���
 *
 */

public class MIPSRegisterAllocator extends RegisterAllocator {

	
	@Override
	protected void init() {
		for(int i=0;i<32;i++){
			registerList.add(new MIPSRegister(i));
		}
		
	}
	

	/**
	 * element:(i:A=B op C)
	 * GETREG(i:A=B op C)
	 */
	
	@Override
	public String getRegister(FourElement element) {
		String arg1=element.getArg1();
		String arg2=element.getArg2();
		String res=(String) element.getResult();
		
		//��ȡ��ǰ���ű�
		SymbolTable symbolTable=SymbolTableStack.peek();
		//TODO element:(i:A=B op C)
		//(1)���B������ֵ�ڼĴ���R�У��ҸüĴ���ֻ����B��ֵ��
		//   ����B��A��ͬһ��ʶ��������B����Ԫʽ֮���ٱ����ã���ѡȡRiΪ����Ĵ�����ת��4��
		
		
		//(2)������δ����ļĴ����������ѡȡһ��Ri,Ϊ����Ĵ���,ת(4)
		
		//(3)���ѷ���ļĴ�����ѡȡһ��Ri��Ϊ����ļĴ���R��ѡȡԭ��Ϊ��ռ��Ri�ı�����ֵҲͬʱ�����������
		//	�����ڻ�������Ҫ����Զ��λ�òŻ����õ����������ԼĴ���Ri�������������µ���
		//		��RVALUE[R]��R�Ĵ�������ı����б��е�ÿһ������M,���M����A��AVALUE[M]
		//	(����M���ڵ�λ��)������M����
		//  1.����Ŀ�����ST Ri��M�����Ĵ���Ri������ȡ��M��
		//  2.���M��B������AVALUE[M]={M,Ri}������AVALUE[M]={M}
		//	3.ɾ��RVALUE[Ri]�е�M
		//	4.����R������
		return null;
	}
	
	@Override
	public String getRegister(String source, FourElement element) {
		//��ȡ��ǰ���ű�
		SymbolTable symbolTable=SymbolTableStack.peek();
		//��ȡ������1�Ĵ��λ��
		//��ȡ������2�Ĵ��λ��
		
		return null;
	}


	//���ݸ����ı������һ���Ĵ���
	@Override
	public String getRegister(String source) {
		// TODO �Զ����ɵķ������
		return null;
	}


	

	

	

}
