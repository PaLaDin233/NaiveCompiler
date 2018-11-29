package cn.ed.qut.compiler.zhg.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class FileReadUtil {
	private FileReadUtil() {
	}
	
	public static void sysoFile(String fileName){
		List<String>list=FileReadUtil.readFile("a.c");
		
		for (String string : list) {
			System.out.println(string);
		}
	}
	public static List<String> readFile(String fileName){
		File file=new File(fileName);
		if(!file.exists())return null;
		List<String> res=new ArrayList<>();
		BufferedReader bufferedReader;
		try {
			bufferedReader=
					new BufferedReader(
							new InputStreamReader(
									new FileInputStream(
											file)));
			String str=bufferedReader.readLine();
			while(str!=null){
				res.add(str);
				str=bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
		return res;
	}
}
