package com.frame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public  class Util {
	
	public static Javafile readJavafile(String fileName,Map<String,Integer> rmap) {
		Javafile java=new Javafile();
		int importcount=0;
		int line = 0;
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));//���ļ���������
			String tempString = null;

			while ((tempString = reader.readLine()) != null) {
				if(tempString.contains("import")){
					String str=tempString.replace("import", "").replace(";", "").trim();//replace�滻��import��Ϊ������trim() ��������ɾ���ַ�����ͷβ�ո�
					String impstr=str.substring(0,str.lastIndexOf("."));//substring() ����������ȡ�ַ����н�������ָ���±�֮����ַ���
					if(rmap.get(impstr)==null){
						rmap.put(impstr, 0);
					}else{
						rmap.put(impstr, rmap.get(impstr)+1);
					}
					importcount++;
				}
				if(tempString.contains("abstract")){
					java.setIfabs(true);
				}
				if(tempString.contains("package")){
					java.setPkgname(tempString.replace("package", "").replace(";", "").trim());
				}
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		java.setClazzname(file.getName().replace(".java", ""));
		java.setImportcount(importcount);
		java.setLinecount(line);
		return java;
	}
}
