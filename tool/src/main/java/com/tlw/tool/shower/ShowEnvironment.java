package com.tlw.tool.shower;

import java.util.Set;

public class ShowEnvironment {
    public static void main(String[] args) {
        Set<Object> set=System.getProperties().keySet();
        for(Object obj:set){
            Object val=System.getProperties().get(obj);
            System.out.println(obj+"::"+val);
        }
    }
}
