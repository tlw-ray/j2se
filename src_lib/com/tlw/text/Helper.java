package com.tlw.text;

import java.io.UnsupportedEncodingException;

public class Helper {
	public static void main(String[] args){
		String str="你好";
		System.out.println(Helper.getBeginCharacter(str));
		System.out.println(Integer.toHexString((int)str.charAt(0)));
		System.out.println("\u554a");
		System.out.println(Integer.toHexString(getCharCode(str)));
	}
    public static int getCharCode(String s) {
        if (s == null || s.equals(""))
            return -1;
		try {
			byte[] b = s.getBytes("GBK");
	        int value = 0;
	        for (int i = 0; i < b.length && i <= 2; i++)
	            value = value * 100 + b[i];
	        return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return -1;
    }

    public static int chineseCompareTo(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int n = Math.min(len1, len2);
        for (int i = 0; i < n; i++) {
            int s1_code = getCharCode(s1.charAt(i) + "");
            int s2_code = getCharCode(s2.charAt(i) + "");
            if (s1_code * s2_code < 0)
                return Math.min(s1_code, s2_code);
            if (s1_code != s2_code)
                return s1_code - s2_code;
        }
        return len1 - len2;
    }

    public static String getBeginCharacter(String res) {
        if(res==null)return "";
        String lowcaseStr = res.toLowerCase();
        String result = "";
        for (int i = 0; i < lowcaseStr.length(); i++) {
            String current = lowcaseStr.substring(i, i + 1);
            if (chineseCompareTo(current, "\u554A") < 0)
                result = result + current;
            else
            if (chineseCompareTo(current, "\u554A") >= 0 &&
                chineseCompareTo(current, "\u5EA7") <= 0)
                if (chineseCompareTo(current, "\u531D") >= 0)
                    result = result + "z";
                else
                if (chineseCompareTo(current, "\u538B") >= 0)
                    result = result + "y";
                else
                if (chineseCompareTo(current, "\u6614") >= 0)
                    result = result + "x";
                else
                if (chineseCompareTo(current, "\u6316") >= 0)
                    result = result + "w";
                else
                if (chineseCompareTo(current, "\u584C") >= 0)
                    result = result + "t";
                else
                if (chineseCompareTo(current, "\u6492") >= 0)
                    result = result + "s";
                else
                if (chineseCompareTo(current, "\u7136") >= 0)
                    result = result + "r";
                else
                if (chineseCompareTo(current, "\u671F") >= 0)
                    result = result + "q";
                else
                if (chineseCompareTo(current, "\u556A") >= 0)
                    result = result + "p";
                else
                if (chineseCompareTo(current, "\u54E6") >= 0)
                    result = result + "o";
                else
                if (chineseCompareTo(current, "\u62FF") >= 0)
                    result = result + "n";
                else
                if (chineseCompareTo(current, "\u5988") >= 0)
                    result = result + "m";
                else
                if (chineseCompareTo(current, "\u5783") >= 0)
                    result = result + "l";
                else
                if (chineseCompareTo(current, "\u5580") >= 0)
                    result = result + "k";
                else
                if (chineseCompareTo(current, "\u51FB") > 0)
                    result = result + "j";
                else
                if (chineseCompareTo(current, "\u54C8") >= 0)
                    result = result + "h";
                else
                if (chineseCompareTo(current, "\u5676") >= 0)
                    result = result + "g";
                else
                if (chineseCompareTo(current, "\u53D1") >= 0)
                    result = result + "f";
                else
                if (chineseCompareTo(current, "\u86FE") >= 0)
                    result = result + "e";
                else
                if (chineseCompareTo(current, "\u642D") >= 0)
                    result = result + "d";
                else
                if (chineseCompareTo(current, "\u64E6") >= 0)
                    result = result + "c";
                else
                if (chineseCompareTo(current, "\u82AD") >= 0)
                    result = result + "b";
                else
                if (chineseCompareTo(current, "\u554A") >= 0)
                    result = result + "a";
        }

        return result;
    }

    public static String getFirstStr(String str) {
        char a = str.charAt(0);
        char aa[] = {a};
        String sss = new String(aa);
        if (Character.isDigit(aa[0]))
            sss = "data";
        else
        if (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z')
            sss = "character";
        else
            sss = getBeginCharacter(sss);
        return sss;
    }
}
