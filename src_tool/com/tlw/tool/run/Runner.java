/*
 * Created on 2008-4-25
 *
 * TODO
 */
package com.tlw.tool.run;
public class Runner {
    public static void main(String[] args) throws Exception{
        //动态加载一个jar包，列举其中包含静态main函数的类
        //String jarFile="E:\\app.java\\OpenSource_UI\\jgoodies2.1\\validation-2.0.0\\build\\validateTutorial.jar";
        //JarEntry jar=new JarEntry(jarFile);
        //Attributes ats=jar.getAttributes();
        //ClassLoader.getSystemClassLoader()
        Package[] pks=Package.getPackages();
        for(Package pk:pks){
            System.out.println(pk.getName());
        }
    }
    class MyClassLoader extends ClassLoader{
      
    }
}
