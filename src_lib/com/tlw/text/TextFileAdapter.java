package com.tlw.text;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tlw.eg.byt.ByteHelper;


/*******************************
 * Author:唐力伟 E-Mail:tlw_ray@163.com Date:2008-10-12
 * Description:用于打开文本文件，能够自识别文件编码方式,能够另存为新的编码方式 每个TextFile对应一个文本文件。
 * TextFile类能够打开(GBK,UTF-8,UTF-16BE,UTF-16LE)方式编码的仅包含中文，西文内容的文本文件。
 * TextFile能够根据文件的BOM或者内容判断文件的编码方式 TextFile能够转换被正常打开文件的编码方式。
 ********************************/
public class TextFileAdapter {
	private File file;
	private String fileContent = "";
	private byte[] fileContentByte;
	private String fileEncoder = "";
	public static String[] supportEncode = new String[] { "GBK", "UTF-8",
			"UTF-16BE", "UTF-16LE" };
	public static final String ENCODE_UNKNOWN = "unknown";//由于文件为空，所以没法判断文件的编码方式
	public static final long FILE_MAX_SIZE = 1024 * 1024 * 10;// 10M
	Logger log = Logger.getLogger(this.getClass().getName());
	{
		log.setLevel(Level.FINEST);
	}
	public TextFileAdapter(File textFile) {
		file = textFile;
		parseFile();
	}

	public TextFileAdapter(String fileFullPath) {
		file = new File(fileFullPath);
		parseFile();
	}

	private void parseFile() {
		// 检查文件大小,如果文件过大不便加载入内存则不做任何处理
		long fileSize = file.length();
		if (fileSize > FILE_MAX_SIZE) {
			fileEncoder = ENCODE_UNKNOWN;
			return;
		} else {
			// 文件不算太大则进行处理	
			parseBOM();
			parseContent();
		}

	}

	/**
	 * 分析当前文本文件中都包含哪些字符区域的字符
	 * 
	 * @param 文本文件内容
	 * @return 内容所涉及的Unicode编码区域
	 */
	//<UnicodeBlock>
	private Set unicodeBlocks(String str) {
		//<UnicodeBlock>
		Set unicodeBlocks = new HashSet();
		char[] chars=str.toCharArray();
		for (int i=0;i<chars.length;i++) {
			char ch =chars[i];
			try {
				UnicodeBlock bu = Character.UnicodeBlock.of(ch);
				unicodeBlocks.add(bu);
			} catch (Exception ex) {
			}
		}
		return unicodeBlocks;
	}
//<UnicodeBlock>
	private static Set cnUnicodeBlock = new HashSet();
	static {
		// 中文java代码文件中可能遇到的unicode编码区域
		cnUnicodeBlock.add(UnicodeBlock.BASIC_LATIN);
		cnUnicodeBlock.add(UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
		cnUnicodeBlock.add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
		cnUnicodeBlock.add(UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
		cnUnicodeBlock.add(UnicodeBlock.SPECIALS);
		cnUnicodeBlock.add(UnicodeBlock.GENERAL_PUNCTUATION);
		// 中文html文件中可能遇到的unicode编码区域
		cnUnicodeBlock.add(UnicodeBlock.ENCLOSED_ALPHANUMERICS);
		cnUnicodeBlock.add(UnicodeBlock.GEOMETRIC_SHAPES);
	}
	/*
	 * BOM (Byte Order Mark) 开头字节 Charset/encoding EF BB BF UTF-8 FE FF
	 * UTF-16/UCS-2, big endian FF FE UTF-16/UCS-2, little endian FF FE 00 00
	 * UTF-32/UCS-4, little endian. 00 00 FE FF UTF-32/UCS-4, big-endian.
	 */
	public static final byte[] BOM_UTF8 = new byte[] { (byte) 0xEF,
			(byte) 0xBB, (byte) 0xBF };
	public static final byte[] BOM_UTF16_BE = new byte[] { (byte) 0xFE,
			(byte) 0xFF };
	public static final byte[] BOM_UTF16_LE = new byte[] { (byte) 0xFF,
			(byte) 0xFE };
	/**
	 * 解析文本文件BOM
	 */
	private void parseBOM() {
		InputStream is = null;
		BufferedInputStream bis=null;
		int bomSize=0;
		try {
			is = new FileInputStream(file);
			bis=new BufferedInputStream(is);
			int i0 = bis.read();
			int i1 = bis.read();
			int i2 = bis.read();
			if (i0 == -1) {
				// 文件为空的情况
				fileEncoder = ENCODE_UNKNOWN;
				return;
			}
			if (i1 == -1) {
				// 文件仅有一个字节的情况
				return;
			}
			byte b0 = ByteHelper.unsign2byte(i0);
			byte b1 = ByteHelper.unsign2byte(i1);
			byte b2 = ByteHelper.unsign2byte(i2);
			
			if (BOM_UTF16_LE[0] == b0 && BOM_UTF16_LE[1] == b1) {
				fileEncoder = "UTF-16LE";
				bomSize=2;
			} else if (BOM_UTF16_BE[0] == b0 && BOM_UTF16_BE[1] == b1) {
				fileEncoder = "UTF-16BE";
				bomSize=2;
			} else if (BOM_UTF8[0] == b0 && BOM_UTF8[1] == b1 && BOM_UTF8[2] == b2) {
				fileEncoder = "UTF-8";
				bomSize=3;
			}
			//加载无BOM头的文件内容
			int fileContentByteSize=(int)file.length()-bomSize;
			fileContentByte=new byte[fileContentByteSize];
			if(bomSize==2){
				fileContentByte[0]=b2;
			}if(bomSize==0){
				fileContentByte[0]=b0;
				fileContentByte[1]=b1;
				fileContentByte[2]=b2;
			}
			bis.read(fileContentByte,3-bomSize,fileContentByteSize-3+bomSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				log.log(Level.WARNING, ex.getMessage());
				ex.printStackTrace();// 开发时
			}
		}
	}

	/**
	 * 解析文本文件内容
	 */
	private void parseContent() {
		if (fileEncoder.equals(ENCODE_UNKNOWN))
			return;// 如果文件为空，那么不执行解析。
		if (fileEncoder.equals("")) {
			// 文件不具有BOM,那么用各种编码方式加载文件并尝试解析其内容。
			String content = "";
			for (int i=0;i<supportEncode.length;i++) {
				String encodeName=supportEncode[i];
				boolean allInSafeBlock = true;//标志量，标识被打开的文件以正确的方式被解码，即只含有Unicode区段中合适的部分
				try {
					content = new String(fileContentByte,encodeName);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				// 分析文本文件中都包含哪些字符区域的字符
				//<UnicodeBlock>
				Set blocks = unicodeBlocks(content);
				// 当发现非正常字符区域时进行报告
				Iterator it=blocks.iterator();
				while(it.hasNext()){
					UnicodeBlock ub =(UnicodeBlock) it.next();
					if (!cnUnicodeBlock.contains(ub)) {
						allInSafeBlock=false;
						String unSafeUnicodeBlockName;
						if(ub==null){
							unSafeUnicodeBlockName="Null";
						}else{
							unSafeUnicodeBlockName=ub.toString();
						}
						log.log(Level.FINE, encodeName + "解析发现非正常字符区域:"+ unSafeUnicodeBlockName);
					}
				}
				//如果没有任何字符超出常规UnicodeBlock那么认为找到了正确的编码方式
				if (allInSafeBlock){
					fileEncoder = encodeName;
					fileContent = content;
					log.log(Level.FINEST, "解析出被打开文件编码格式:" + encodeName);
					return;
				}else{
					fileEncoder = "";
					fileContent = "";
				}
			}
			//如果代码能够执行到这里，那么说明没有解析出正确的格式
			log.log(Level.WARNING, file.getAbsolutePath() + "没有解析出正确的格式.");
		}
	}
	/**
	 * 保存文本文件，以指定的编码方式。保存的文件是否带有BOM根据打开时是否带有BOM决定。
	 * 
	 * @param encode
	 *            指定的编码方式
	 */
	public void saveFile(String dimEncoder) {
		dimEncoder = dimEncoder.toUpperCase();
		// 如果要转换编码与源文件编码相同，则不做任何操作。
		if (dimEncoder.equals(fileEncoder))
			return;
		if(isSupportedEncode(dimEncoder)){
			FileOutputStream fos = null;
			OutputStreamWriter osw = null;
			BufferedWriter bw = null;
			try {
				fos = new FileOutputStream(file);
				osw = new OutputStreamWriter(fos, dimEncoder);
				bw = new BufferedWriter(osw);
				// 写入BOM头
				byte[] bom = null;
				if (dimEncoder.equals("UTF-8"))
					bom = BOM_UTF8;
				if(dimEncoder.equals("UTF-16BE"))
					bom=BOM_UTF16_BE;
				if(dimEncoder.equals("UTF-16LE"))
					bom=BOM_UTF16_LE;
				if (bom != null)
					fos.write(bom);
				//写入文件内容
				String content = getFileContent();
				bw.write(content);
				bw.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (osw != null)
						osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (fos != null)
						fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static boolean isSupportedEncode(String encodeName){
		for(int i=0;i<supportEncode.length;i++){
			String encode=supportEncode[i];
			if(encodeName.toUpperCase().equals(encode))return true;
		}
		return false;
	}
	public File getFile() {
		return file;
	}
	public String getFileContent() {
		if(fileContent.equals("") && isSupportedEncode(fileEncoder)) {
			//如果文件内容尚不存在，但文件编码方式已知并且被支持则解析文件内容
			try {
				fileContent = new String(fileContentByte, fileEncoder);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//当返回为空时可能:
		//1.未能正确解析出输入文件的编码方式，因而文件内容为空.(源文件编码方式设置为'unknown')
		//2.文件本身为空，有BOM(已知源文件编码格式)
		//3.文件本身为空，无BOM(未知源文件编码格式)
		return fileContent;
	}
	public String getFileEncoder() {
		return fileEncoder;
	}
}
