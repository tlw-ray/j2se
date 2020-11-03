package com.tlw.eg.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月28日
 */
@Documented  
@Target({ElementType.TYPE,ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Yts {  
   public enum YtsType{util,entity,service,model}  
     
   public YtsType classType() default YtsType.util;
   
} 

