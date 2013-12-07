package com.tlw.eg.java;
/**
 * @Author: 唐力伟 (tlw_ray@163.com)
 * @since:2009-2-17
 * @version:2009-2-17 Description:
 */
public class Switch {
	/*
	 * (当程序执行一条case语句后，因为例子中的case分支中没有break 和return语句，所以程序会执行紧接于其后的语句)
	 * default,case字句的作用只是一个标号.用来查找入口并开始执行
	 */
	public void switchTest1() {
		for (int i = 0; i < 2; i++)
			switch (i) {// 结果：0 1M 0 1
			case 0:
				for (int j = 0; j < 2; j++)
					switch (j) {
					case 0:
						System.out.println(j);
						break;
					case 1:
						System.out.println(j + "M");
						break;
					}
			case 1:
				System.out.println(i);
				break;
			}
	}
	/*
	 * 
	 * switch表达式的值决定选择哪个case分支，如果找不到相应的分支，就直接从"default" 开始输出。
	 */
	public void switchTest2() {
		int x = 0;
		switch (x) {// 结果：default 1 2
		default:
			System.out.println("default");
		case 1:
			System.out.println(1);
		case 2:
			System.out.println(2);
		}
		System.out.println("--------------");
		switch (x) {// 结果：0 1 2
		default:
			System.out.println("default");
		case 0:
			System.out.println(0);
		case 1:
			System.out.println(1);
		case 2:
			System.out.println(2);
		}
		System.out.println("--------------");
		switch (x) {// 结果：0 1 2 default
		case 0:
			System.out.println(0);
		case 1:
			System.out.println(1);
		case 2:
			System.out.println(2);
		default:
			System.out.println("default");
		}
	}
	/*
	 * switch表达式的必须是整数类型(char,byte,short,int或它们对应的包装器类或枚举类型)
	 * case字句的值必须是常量(如1;5+3;或final修饰的常量),不可为变量
	 * 所有case子句中的值必须是不同的
	 * continue只能用于循环体中
	 */
	public void switchTest3() {
		int x = 0;
		final int j = 1;
		switch (x) {
		case j:
			System.out.println("df");
			// continue;
			// case 1:
			// System.out.println("df");
		default:
			System.out.println("ffffff");
		}
	}
	public static void main(String[] args) {
		Switch temp = new Switch();
		//temp.switchTest1();
		temp.switchTest2();
		//temp.switchTest3();
	}
}