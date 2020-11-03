package com.tlw.eg.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月28日
 */
@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
@Inherited  
public @interface HelloWorld {  
    public String name()default "";  
}  

