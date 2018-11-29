package cn.ed.qut.compiler.base.objectCodeGeneration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

/**
 * 汇编代码生成器
 */
public class huibian extends JFrame {

	private static final long serialVersionUID = 8766059377195109228L;
	private static String title;
	private static String fileName;
	
	private static TextArea text;
	
	public huibian() {
		init();
		
	}
public huibian(String title,String fileName){
	this.title=title;
	this.fileName=fileName;
	init();
	this.setTitle(title);
	try {
		String str[] =readFile(fileName).split("\n");
		for(int i = 2; i < str.length; i++)
		{
			//System.out.println(str[i]);
			String temp[] = str[i].split(",");
			//System.out.println(temp.length);
			//for(int j = 0; j < temp.length; j++) System.out.println(temp[j]);
			if(temp[0].charAt(temp[0].length() -1) == '='){
				//System.out.println(i);
				
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " MOV " + temp[3].substring(0,temp[3].length() - 1) + "," + temp[1] + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '+' && temp[0].charAt(temp[0].length() -2) == '+'){
				String temp1 = temp[0].substring(0,temp[0].length() - 3) + " INC "  + temp[1] + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '+'){
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " ADD " + temp[3].substring(0,temp[3].length() - 1) + "," + temp[1] + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '-'){
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " SUB " + temp[3].substring(0,temp[3].length() - 1) + "," + temp[1] + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '*'){
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " MUL " + temp[3].substring(0,temp[3].length() - 1) + "," + temp[1] + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '/'){
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " DIV "  + temp[3].substring(0,temp[3].length() - 1) + "," + temp[1] + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == 'J' && temp[0].charAt(temp[0].length() -2) == 'R'){
				String temp1 = temp[0].substring(0,temp[0].length() - 3) + " JMP " + temp[3].substring(0,temp[3].length() - 1)  + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == 'J' && temp[0].charAt(temp[0].length() -2) == 'F'){
				String temp1 = temp[0].substring(0,temp[0].length() - 3) + " JZ " + temp[3].substring(0,temp[3].length() - 1)  + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '>'){
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " JG " + temp[3].substring(0,temp[3].length() - 1)  + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
			else if(temp[0].charAt(temp[0].length() -1) == '<'){
				String temp1 = temp[0].substring(0,temp[0].length() - 2) + " JL " + temp[3].substring(0,temp[3].length() - 1)  + "\n";
				//System.out.println(temp1);
				text.append(temp1);
			}
		}
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}	
}
	private void init() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		setSize(500, 400);
		super.setLocation(screen.width / 2 - this.getWidth() / 2, screen.height
				/ 2 - this.getHeight() / 2);
		setContentPane(createContentPane());
	}

	private Container createContentPane() {
		JPanel pane = new JPanel(new BorderLayout());
		text = new TextArea();
		//msg.setBackground(Color.green);
		text.setForeground(Color.BLUE);
		pane.add(BorderLayout.CENTER, text);
		return pane;
	}
	private String readFile(String filename) 
	throws IOException{
		StringBuilder sbr = new StringBuilder();
		String str;
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			InputStreamReader isr = new InputStreamReader(bis, "UTF-8");
			BufferedReader in=new BufferedReader(isr);
			while((str=in.readLine())!=null){
				sbr.append(str).append('\n');
			}
			in.close();
			//text.setText(sbr.toString());
		return sbr.toString();
	}
	public static String getTitl() {
		return title;
	}

	public static void setTitl(String title) {
		huibian.title = title;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		huibian.fileName = fileName;
	}

	public static TextArea getText() {
		return text;
	}

	public static void setText(TextArea jText) {
		huibian.text = jText;
	}


}
