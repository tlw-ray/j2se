package com.tlw.image;

public class JPEGHelper {
    public static boolean isJPEG(byte[] byts){
        //JPEG图像开始SOI(Start of Image)标记0xFFD8
        //JPEG图像结束标记为(0xFFD9)
        return (byts[0]==0xFF && byts[1]==0xD8);
    }
}
