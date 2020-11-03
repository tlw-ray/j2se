/*
 * Created on 2006-8-31
 */
package com.tlw.io;
import java.io.File;
import java.io.FileFilter;
public class FileFilterImage implements FileFilter {
    String[] filters={".jpg",".gif",".png"};
    public boolean accept(File pathname) {
        String path=pathname.getPath();
        String ext=path.substring(path.length()-4).toLowerCase();
        for(int i=0;i<filters.length;i++){
            if(ext.equals(filters[i])){
                return true;
            }
        }
        return false;
    }

}
