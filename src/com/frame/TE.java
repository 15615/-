package com.frame;

public class TE {

	public static void main(String[] args) {
		String a = "1 3 5";
        String str = "3 1 4 3 3";//注意这里在最后多了一个|分隔符
        String[] strArr = str.split(" ");//这里将第二个参数设置为-1
        System.out.println(strArr.length); //这里输出4
        for (int i = 0; i < strArr.length; ++i){
        	System.out.println(strArr[i]);//这里输出a b c 空字符串

	}

}
}