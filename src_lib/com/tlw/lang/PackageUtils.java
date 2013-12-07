/*
 * Created on 2006-9-7
 * 填补一些Package类功能;
 */
package com.tlw.lang;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tlw.io.ExampleFileFilter;

public class PackageUtils {

 private static boolean debug = true;
 //取得某个jar文件中某个包路径下的所有文件;
 //<String>
 public static List getClasseNamesInPackage (String jarName, String packageName){
	 //<String>
	 ArrayList classes = new ArrayList();
   packageName = packageName.replaceAll("\\." , "/");
   if (debug) System.out.println("Jar " + jarName + " looking for " + packageName);
   try{
     JarInputStream jarFile = new JarInputStream(new FileInputStream (jarName));
     JarEntry jarEntry;
     while(true) {
       jarEntry=jarFile.getNextJarEntry ();
       if(jarEntry == null){
         break;
       }
       if((jarEntry.getName ().startsWith (packageName)) &&
            (jarEntry.getName ().endsWith (".class")) ) {
         if (debug) System.out.println ("Found " + jarEntry.getName().replaceAll("/", "\\."));
         classes.add (jarEntry.getName().replaceAll("/", "\\."));
       }
     }
   }
   catch( Exception e){
     e.printStackTrace ();
   }
   return classes;
}
 //取得某个路径下的所有jar文件;
 public static File[] getAllJarFile(String directory){
     File dir=new File(directory);
     if(dir.isDirectory()){
         ExampleFileFilter eff=new ExampleFileFilter();
         eff.addExtension("jar");
         eff.setDirectoryEnable(false);
         File[] files=dir.listFiles(eff);
         return files;
     }else{
         System.err.println(directory+"is a invalid path!");
         return null;
     }
 }
//取得jar中所有完整名称符合regex的类名;
public static String[] getAllClassNameLike(File jarFile,String regex){
	//<String>
    ArrayList list=new ArrayList();
    try {
        Pattern pattern=Pattern.compile(regex);
        JarFile jf=new JarFile(jarFile);
        //<JarEntry>
        for(Enumeration ee=jf.entries();ee.hasMoreElements();){
            JarEntry je=(JarEntry)ee.nextElement();
            Matcher matcher=pattern.matcher(je.getName());
            if(matcher.matches()){
                String name=je.getName();
                name=name.replaceAll("\\/", ".");
                String ext=name.substring(name.length()-6);//6是".class"的长度;
                if(ext.equals(".class")){
                    name=name.substring(0, name.length()-6);
                }
                list.add(name);
//              Debug:
                System.out.println(name);
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    return (String[]) list.toArray(new String[list.size()]);
}
//在某个jar中筛选出所有某个类的可实例化的子类的名称;
public static String[] getAllSubClass(File jarFile,Class pClass){
	//<String>
    ArrayList al=new ArrayList();
    try {
        JarFile jf=new JarFile(jarFile);
        //<JarEntry>
        for(Enumeration ee=jf.entries();ee.hasMoreElements();){
            JarEntry je=(JarEntry)ee.nextElement();
            String clsname=je.getName();
            if(clsname.endsWith(".class") && clsname.indexOf('$')==-1){
                clsname=clsname.replaceAll("\\/", ".");
                clsname=clsname.substring(0,clsname.length()-6);
                if(isChildClass(clsname,pClass)){
                    System.out.println(clsname);
                    al.add(clsname);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return (String[]) al.toArray(new String[al.size()]);
}
//根据类名判断某个类是否是另一个类的子类
//之所以这样是因为当时用来判断可用的LookAndFeel一般类名中包含这个字段的,都是LookAndFeel类的子类)
public static boolean isChildClass(String classname,Class pClass){
    Method[] ms=pClass.getMethods();
    Class cClass;
    try {
        //不会去判断一个内部类是否能够实例化;
        if(classname.indexOf('$')!=-1)return false;
        //注意:下边这行有可能会发生:ExceptionInInitializerError错误.原因未知
        //加之此方法运行效率比较低所以应该尽量按照类名筛选类文件后再放入此类比较;
        cClass = Class.forName(classname);
        //判断父类中的方法是否在子类中都有,如果都有说明有继承关系;
        Method[] ms2=cClass.getMethods();
        for(int i=0;i<ms.length;i++){
            //设定一个标志为false;
            boolean sign=false;
            for(int j=0;j<ms2.length;j++){
                if(ms[i].getName()==ms2[j].getName()){
                    //如果在子类中找到了这个方法则标志为true;
                    sign=true;
                    break;
                }
            }
            //如果此方法最终在子类中没有找到,那么退出返回false,如果找到则继续找下个方法的;
            if(!sign) return false;
        }
        return true;
    } catch (ClassNotFoundException e) {
        System.err.println("读入类:"+classname+"失败!");
        return false;
    } catch(Exception ex){
        System.err.println("读入类:"+classname+"失败!");
        return false;
    }
}
//取得一个包中所有的main方法;<String> 
public static ArrayList getMainInJar(String jarFileName){
    /*设计:
           第一步:遍历Jar文件中每一个.class;
           第二步:试图读取这个类,并判断他是否包含一个public static void main(String[] args)函数;
    */
	//<String>
    ArrayList als=new ArrayList();
    try {
        JarFile jarFile=new JarFile(jarFileName);
        System.out.println(jarFile.getName());
        JarEntry jarEntry;
        System.out.println(jarFile.size());
        //<JarEntry>
        Enumeration enu=jarFile.entries();
        while(enu.hasMoreElements()){
            jarEntry=(JarEntry)enu.nextElement();
            if(jarEntry.isDirectory()){
                System.out.println("------------"+jarEntry.getName()+"-------------");
            }else{
                System.out.println(jarEntry.getName());
                try{
                    Class c=Class.forName(jarEntry.getName());
                    Method m=c.getMethod("main", new Class[]{String[].class});
                    if(m!=null){
                        als.add(jarEntry.getName());
                    }
                }catch(ClassNotFoundException ex){
                    //System.err.println(ex.getMessage());
                }catch(NoSuchMethodException ex){
                    //System.err.println(ex.getMessage());
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("-----------------finish---------------");
    for(int i=0;i<als.size();i++){
        System.out.println(als.get(i));
    }
    return als;
}
/*******************测试*************************/
static void testGetAllJarFile(){
        String path="E:/app.java/plaf/";
        File[] fs=PackageUtils.getAllJarFile(path);
        for(int i=0;i<fs.length;i++){
            System.out.println(fs[i].getAbsolutePath());
        }
    }
static void testGetClasseNamesInPackage(){
        //String path="E:/app.java/plaf/substance.jar";
        String path="E:/app.java/plaf/metouia.jar";
        List list =  PackageUtils.getClasseNamesInPackage(path,"org.jvnet.substance");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
static void testIsChildClass(){
    //应该回应是的例子;
    long a=System.currentTimeMillis();
    System.out.println(isChildClass("java.awt.Frame",java.awt.Component.class));
    //应该回应不是的例子;
    long b=System.currentTimeMillis();
    System.out.println(isChildClass("java.awt.Frame",java.awt.Color.class));
    //测试一个关心的例子
    long c=System.currentTimeMillis();
    System.out.println(isChildClass("org.jvnet.substance.SubstanceLookAndFeel",
            javax.swing.LookAndFeel.class));
    long d=System.currentTimeMillis();
    //测试一个关心的例子
    System.out.println(isChildClass("org.jvnet.substance.button.ClassicButtonShaper",
            javax.swing.LookAndFeel.class));
    long e=System.currentTimeMillis();
    System.out.println("第1条用时"+(b-a));
    System.out.println("第2条用时"+(c-b));
    System.out.println("第3条用时"+(d-c));
    System.out.println("第4条用时"+(e-d));
    //System.out.println(isChildClass("org.jvnet.substance.theme.ThemeInfo",org.jvnet.substance.theme.SubstanceTheme.class));
}
static void testGetAllSubClass(){
    String path="E:/app.java/plaf/substance.jar";
    //String path="E:/app.java/plaf/metouia.jar";
    String[] strs=getAllSubClass(new File(path),javax.swing.plaf.metal.MetalTheme.class);
    for(int i=0;i<strs.length;i++){
        System.out.println(strs[i]);
    }
}
static void testGetAllClassNameLike(){
    String path="E:/app.java/plaf/substance.jar";
    String[] strs=getAllClassNameLike(new File(path),"[a-zA-Z/]*/theme/[a-zA-Z]*.class");
    for(int i=0;i<strs.length;i++){
        System.out.println(strs[i]);
    }
}
private static void testGetMainInJar(String jarFileName){
	//<String>
    ArrayList als=getMainInJar(jarFileName);
    for(int i=0;i<als.size();i++){
        System.out.println(als.get(i).toString());
    }
}
public static void main (String[] args){
       //testGetAllJarFile();
       //testGetClasseNamesInPackage();
       //testIsChildClass();
       //testGetAllClassNameLike();
       //testGetAllSubClass();
        //getMainInJar("E:\\app.java\\OpenSource_Java\\javadoc2help\\lib\\Javadoc2Help.jar");
        /*
    String [] strs=getAllSubClass(new File("E:\\app.java\\OpenSource_Java\\javadoc2help\\lib\\Javadoc2Help.jar"),javax.swing.JFrame.class);
        for(int i=0;i<strs.length;i++){
            System.out.println(strs);
        }
        */
        String jarFile="E:\\app.java\\OpenSource_UI\\jgoodies2.1\\validation-2.0.0\\build\\validateTutorial.jar";
        testGetMainInJar(jarFile);
    }
}
