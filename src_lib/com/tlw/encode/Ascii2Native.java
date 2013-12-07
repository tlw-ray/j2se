package com.tlw.encode;


/**
 * Ascii2Native - \\uxxxx convert Native code
 * 
 * @version 1.1
 * @Author so-miya@mma.gr.jp
 */
public class Ascii2Native {
	/**
	 * Main
	 */
	public static void main(String[] args) {
		String s1=native2ascii("唐力伟");
		String s2=ascii2native(s1);
		System.out.println(s1+" <=> "+s2);
		s1=native2ascii("麦杰_文档");
		s2=ascii2native(s1);
		System.out.println(s1+" <=> "+s2);
	}
	public static String native2ascii(String str){  
        String tmp;  
        StringBuffer sb = new StringBuffer(1000);  
        char c;  
        int i, j;  
        sb.setLength(0);  
        for (i = 0; i < str.length(); i++) {  
            c = str.charAt(i);  
            if (c > 255) {  
                sb.append("\\u");  
                j = (c >>> 8);  
                tmp = Integer.toHexString(j);  
                if (tmp.length() == 1)  
                    sb.append("0");  
                sb.append(tmp);  
                j = (c & 0xFF);  
                tmp = Integer.toHexString(j);  
                if (tmp.length() == 1)  
                    sb.append("0");  
                sb.append(tmp);  
            } else {  
                sb.append(c);  
            }  
  
        }  
        return (new String(sb));  
	}
	/**
	 * core routine
	 */
	public static String ascii2native(String str) {
		String hex = "0123456789ABCDEF";
		StringBuffer buf = new StringBuffer();
		int ptn = 0;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '\\' && i + 1 <= str.length() && str.charAt(i + 1) == '\\') {
				buf.append("\\\\");
				i += 1;
			} else if (c == '\\' && i + 6 <= str.length()
					&& str.charAt(i + 1) == 'u') {
				String sub = str.substring(i + 2, i + 6).toUpperCase();
				int i0 = hex.indexOf(sub.charAt(0));
				int i1 = hex.indexOf(sub.charAt(1));
				int i2 = hex.indexOf(sub.charAt(2));
				int i3 = hex.indexOf(sub.charAt(3));

				if (i0 < 0 || i1 < 0 || i2 < 0 || i3 < 0) {
					buf.append("\\u");
					i += 1;
				} else {
					byte[] data = new byte[2];
					data[0] = i2b(i1 + i0 * 16);
					data[1] = i2b(i3 + i2 * 16);
					try {
						buf.append(new String(data, "UTF-16BE").toString());
					} catch (Exception ex) {
						buf.append("\\u" + sub);
					}
					i += 5;
				}
			} else {
				buf.append(c);
			}
		}

		return buf.toString();
	}

	/**
	 * binary to unsigned integer
	 * <P>
	 * 
	 * @param b
	 *            binary
	 * @return unsined integer
	 */
	private static int b2i(byte b) {
		return (int) ((b < 0) ? 256 + b : b);
	}

	/**
	 * unsigned integer to binary
	 * <P>
	 * 
	 * @param i
	 *            unsigned integer
	 * @return binary
	 */
	private static byte i2b(int i) {
		return (byte) ((i > 127) ? i - 256 : i);
	}
}