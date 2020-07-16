package com.frame;

import java.math.BigDecimal;

public class dd {

	public static void main(String[] args) {
		double a = 1.2345689522;
		System.out.println(a);
		BigDecimal b = new BigDecimal(a);  
		  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		  System.out.println(f1);
	}

}
