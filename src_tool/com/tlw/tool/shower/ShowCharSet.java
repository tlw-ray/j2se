package com.tlw.tool.shower;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

public class ShowCharSet {
    public static void main(String[] args) {
        Map<String,Charset> map=Charset.availableCharsets();
        for(String name:map.keySet()){
            System.out.println("---------"+name);
            Charset c=map.get(name);
            Set<String> aliases=c.aliases();
            for(String alias:aliases){
                System.out.println(alias);
            }
        }
    }
}
