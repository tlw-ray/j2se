/*
 * Created on 2006-9-7
 * 通过文件名称匹配来筛选;
 * 一般用于软件内部.
 * 例如在多个LookAndFeel包中包含多个LookAndFeel主类和类名包含$的辅助类;
 * 使用这个filter即可查找出仅仅主类;
 */
package com.tlw.io;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexFileFilter implements FileFilter  {
    Pattern pattern;
    public RegexFileFilter(String regex){
        pattern=Pattern.compile(regex);
    }
    public boolean accept(File pathname) {
       Matcher m=pattern.matcher(FileUtils.getExtractFileName(pathname));
       return m.matches();
    }
    public static void main(String[] args){
        //Test!
        String str1="org.jvnet.substance.SubstanceLookAndFeel$1.class";
        String str2="org.jvnet.substance.SubstanceLookAndFeel$FocusKind.class";
        String str3="org.jvnet.substance.SubstanceLookAndFeel.class";
        RegexFileFilter r=new RegexFileFilter("[a-zA-Z.]*[LookAndFeel.class]");
        System.out.println(r.accept(new File(str1)));
        System.out.println(r.accept(new File(str2)));
        System.out.println(r.accept(new File(str3)));
    }
    /**
     * @return regex
     */
    public Pattern getPattern() {
        return pattern;
    }
    /**
     * @param regex the regex to set
     */
    public void setRegex(String regex) {
        pattern=Pattern.compile(regex);
    }
}
