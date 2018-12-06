package cn.ed.qut.compiler.base.sourceCodeInput;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import cn.ed.qut.compiler.base.objectCodeGeneration.Asm;
import cn.ed.qut.compiler.base.parsing.LexAnalyse;
import cn.ed.qut.compiler.base.parsing.Parser;
import cn.ed.qut.compiler.base.wordSegmenter.Word;

public class MainFrame extends JFrame {

	JTextArea sourseFile;//用来显示源文件的文本框
	String soursePath;// 源文件路径
	String LL1Path;
	String wordListPath;
	String fourElementPath;
	LexAnalyse lexAnalyse;

	Parser parser;
	public MainFrame() {
		this.init();
	}
	
	public void init() {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		setTitle("Naive!Compiler!");
		setSize(750, 480);
		super.setResizable(false);
		super.setLocation(screen.width / 2 - this.getWidth() / 2, screen.height
				/ 2 - this.getHeight() / 2);
		this.setContentPane(this.createContentPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel createContentPane() {
		creatMenu();
		JPanel p = new JPanel(new BorderLayout());
		p.add(BorderLayout.CENTER, createcCenterPane());
		// p.setBorder(new EmptyBorder(8,8,8,8));
		return p;
	}

	private Component createcCenterPane() {
		JPanel p = new JPanel(new BorderLayout());
		JLabel label = new JLabel("源文件如下：");
		sourseFile = new JTextArea();
		sourseFile.setText("");
		p.add(BorderLayout.NORTH, label);
		p.add(BorderLayout.CENTER, sourseFile);
		return p;
	}

	private void creatMenu(){
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("文件");
		JMenu editMenu=new JMenu("编辑");
		JMenu projectMenu=new JMenu("工程");
		JMenu viewMenu=new JMenu("视图");
		JMenu helpMenu=new JMenu("帮助");
		JMenu compileMenu=new JMenu("编译");
		JMenuItem ananlyseMenuItem=new JMenuItem("词法分析");
		JMenuItem parserMenuItem=new JMenuItem("语法分析");
		JMenuItem intermediatCodeMenuItem=new JMenuItem("中间代码生成");
		JMenuItem assembler8086MenuItem=new JMenuItem("8086汇编生成");
		JMenuItem assemblerMIPSMenuItem=new JMenuItem("MIPS汇编生成");
		
		JMenuItem jmimportItem1=new JMenuItem("创建新项目");
		JMenuItem jmimportItem2=new JMenuItem("文件另存为");
		JMenuItem jmimportItem3=new JMenuItem("创建新项目");
		JMenuItem jmimportItem4=new JMenuItem("文件另存为");
		JMenuItem jmimportItem5=new JMenuItem("创建新项目");
		JMenuItem jmimportItem6=new JMenuItem("使用帮助");
		JMenuItem jmimportItem=new JMenuItem("导入源文件");
		JMenuItem jmexitItem=new JMenuItem("退出程序");
		jmimportItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int ret = chooser.showOpenDialog(new JPanel());
				if (ret == JFileChooser.APPROVE_OPTION) {
					String text;
					try {
						soursePath =chooser.getSelectedFile().getPath();
						text = readFile(soursePath);
						sourseFile.setText(text);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		jmexitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		ananlyseMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					lexAnalyse=new LexAnalyse(sourseFile.getText());
					wordListPath = lexAnalyse.outputWordList();
					if(lexAnalyse.isFail()){
						int i=lexAnalyse.errorList.get(0).getLine();
						setErrorLine(i);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InfoFrame inf = new InfoFrame("词法分析", wordListPath);

				inf.setVisible(true);
			}
		});
		parserMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lexAnalyse=new LexAnalyse(sourseFile.getText());
				parser=new Parser(lexAnalyse);
				try {
					parser.grammerAnalyse();
					if(parser.graErrorFlag){
						   int i=parser.errorList.get(0).getLine();
						   setErrorLine(i);
						}
					LL1Path= parser.outputLL1();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InfoFrame inf = new InfoFrame("语法分析", LL1Path);
				inf.setVisible(true);
				
			}
		});
		intermediatCodeMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					lexAnalyse=new LexAnalyse(sourseFile.getText());
					parser=new Parser(lexAnalyse);
					parser.grammerAnalyse();
					fourElementPath=parser.outputFourElem();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InfoFrame inf = new InfoFrame("中间代码生成", fourElementPath);
				inf.setVisible(true);
			}
		});
		assembler8086MenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取标识符
				lexAnalyse = new LexAnalyse(sourseFile.getText());
				ArrayList<Word> wordList;
				wordList = lexAnalyse.getWordList();
				ArrayList<String> id = new ArrayList<String>();
				for (int i = 0; i < wordList.size(); i++) {
					if(wordList.get(i).getType().equals(Word.IDENTIFIER)){					
						if(!id.contains(wordList.get(i).getValue())){
							id.add(wordList.get(i).getValue());
							System.out.println(wordList.get(i).getValue());
						}
					}
				}


				parser = new Parser(lexAnalyse);
				parser.grammerAnalyse();
				Asm asm = new Asm(parser.fourElemList, id, parser.fourElemT);

				InfoFrame inf;
				try {
					inf = new InfoFrame("8086汇编代码", asm.getAsmFile());
					inf.setVisible(true);		
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		assemblerMIPSMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		fileMenu.add(jmimportItem);
		fileMenu.add(jmexitItem);
		editMenu.add(jmimportItem1);
		editMenu.add(jmimportItem2);
		viewMenu.add(jmimportItem3);
		viewMenu.add(jmimportItem4);
		projectMenu.add(jmimportItem5);
		helpMenu.add(jmimportItem6);
		
		compileMenu.add(ananlyseMenuItem);
		compileMenu.add(parserMenuItem);
		compileMenu.add(intermediatCodeMenuItem);
		compileMenu.add(assembler8086MenuItem);
		compileMenu.add(assemblerMIPSMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(projectMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		menuBar.add(compileMenu);
		this.setJMenuBar(menuBar);

	}
	public void setErrorLine(int i){
		try {
			sourseFile.requestFocus();
			int selectionStart = sourseFile.getLineStartOffset(i-1);
			int selectionEnd = sourseFile.getLineEndOffset(i-1);
			sourseFile.setSelectionStart(selectionStart);
			sourseFile.setSelectionEnd(selectionEnd);
			sourseFile.setSelectionColor(Color.red);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static String readFile(String fileName) throws IOException {
		StringBuilder sbr = new StringBuilder();
		String str;
		FileInputStream fis = new FileInputStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		InputStreamReader isr = new InputStreamReader(bis, "UTF-8");
		BufferedReader in = new BufferedReader(isr);
		while ((str = in.readLine()) != null) {
			sbr.append(str).append('\n');
		}
		in.close();
		return sbr.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mf = new MainFrame();
		//TinyCompiler tinyCompiler = new TinyCompiler();
		//mf.tinyCompiler = tinyCompiler;
		mf.setVisible(true);
	}
}

